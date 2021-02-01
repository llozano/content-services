package com.interview.contentservices.giphy.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class SearchResponse implements Serializable {
	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 4457951546193106764L;
	
	
	private String searchTerm;
	
	private Set<Gif> gifs;
	
	/**
	 * Builder override
	 * @author leonardolozano
	 *
	 */
	public static class SearchResponseBuilder {
		public SearchResponseBuilder gifts(Gif[] gifts) {
			
			this.gifs = new LinkedHashSet<Gif>();
			
			// SPEC: Always return exactly 3 results or 0 results if there are less than 3 available per search term
			if (gifts != null && gifts.length >= 3) {
				this.gifs.addAll(
						Arrays.asList(gifts).subList(0, 3)
						);
				
			}
			
			return this;
		}
	}
	
	/**
	 * Builds a SearchResponse from json
	 * @param jsonNode
	 * 		JsonNode to be parsed
	 * @param withSearchTerm
	 * 		Term value
	 * @return
	 * 		An instance of SearchResponse with values from JsonNode
	 * @throws JsonProcessingException
	 * 		Any Json Processing Exception
	 */
	public static SearchResponse fromJsonNode(JsonNode jsonNode, String withSearchTerm) throws JsonProcessingException {
		final ObjectMapper objectMapper = new ObjectMapper();

		JsonNode dataNode = jsonNode.at("/data");
		
		final Gif[] gifts = objectMapper.treeToValue(dataNode, Gif[].class);
		
		
		return SearchResponse.builder()
				.gifts(gifts)
				.searchTerm(withSearchTerm)
				.build();
	}
	
	public static SearchResponse buildEmpty(String withSearchTerm) {
		return SearchResponse.builder()
				.gifts(null)
				.searchTerm(withSearchTerm)
				.build();
	}
}
