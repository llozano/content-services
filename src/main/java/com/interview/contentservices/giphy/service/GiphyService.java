package com.interview.contentservices.giphy.service;

import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.interview.contentservices.giphy.model.GiphySearviceResponse;
import com.interview.contentservices.giphy.model.SearchResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GiphyService {

	@Autowired
	@Qualifier("giphyRestTemplate")
	private RestTemplate restTemplate;
	
	@Value("${giphy.api.url}")
	private String giphyApiUrl;
	
	@Autowired
	@Qualifier("threadPollTaskExcutor")
	private Executor executor;
	
	/**
	 * Searches for giphy by multiple terms
	 * @param searchTerms
	 * 		List of search terms
	 * @return
	 * 		Search response
	 */
	public GiphySearviceResponse search(final List<String> searchTerms) {
		
		log.info("searchTerms: {}", searchTerms);
		
		// Return empty results if there aren't any search terms
		if (searchTerms == null || searchTerms.isEmpty()) {
			return GiphySearviceResponse.builder()
					.data(Collections.emptyList())
					.build();
		}
		
		// Map terms to keep distinct values
		final Map<String, String> uniqueTerms = searchTerms.stream()
				.filter(searchTerm -> searchTerm != null && searchTerm.trim().length() > 0)
				.collect(
					LinkedHashMap::new,                           
					(map, item) -> map.put(item.trim(), item.trim()),
					Map::putAll
				);
		
		// Return empty results if Map is empty
		if (uniqueTerms.isEmpty()) {
			return GiphySearviceResponse.builder()
					.data(Collections.emptyList())
					.build();
		}
		
		// Create futures
		List<CompletableFuture<SearchResponse>> futures = uniqueTerms.entrySet().stream()
				.map(entry -> entry.getValue())
				.filter(searchTerm -> searchTerm != null && searchTerm.trim().length() > 0)
                .map(searchTerm -> executeSearch(searchTerm))
                .collect(Collectors.toList());

		// Collect search results
		List<SearchResponse> result = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
		
		
		return GiphySearviceResponse.builder()
				.data(result)
				.build();
	}
	

	/**
	 * Wraps each http request in a CompletableFuture
	 * @param q
	 * 		searchTerm to pass to the api
	 * @return
	 * 		CompletableFuture to be resolved
	 */
	private CompletableFuture<SearchResponse> executeSearch(String q) {

        String searchTerm = new String(q);
        return CompletableFuture.supplyAsync(() -> {
        	try {
        		final URI url = UriComponentsBuilder.fromUriString(giphyApiUrl)  
            		    .queryParam("q", q)
            		    .build().encode().toUri();
        		
        		log.info("url: {}", url);
        		final JsonNode responseObj = restTemplate.getForObject(url, JsonNode.class);
				return SearchResponse.fromJsonNode(responseObj, searchTerm);
			} catch (JsonProcessingException e) {
				log.error("Error parsing Json Object", e);
				throw new RuntimeException(e.getMessage());
			} catch (RestClientException rex) {
				log.error("Error HTTP client", rex);
				throw new RuntimeException(rex.getMessage());
			} catch (Exception ex) {
				log.error("Error", ex);
				throw new RuntimeException(ex.getMessage());
			}
        }, executor)
		.handle((result, ex) -> {
	        if (null != ex) {
	            return SearchResponse.buildEmpty(searchTerm);
	        } else {
	            return result;
	        }
        });
	}
}
