package com.interview.contentservices.giphy.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.interview.contentservices.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class SearchResponseTest {

	
	@Test
	void fromJsonNode() throws Exception {
		String giphyResponse = TestUtils.readResource("giphy-response.json");
		String expectedJson = TestUtils.readResource("search-response-ronaldinho.json");
		final JsonNode jsonNode = TestUtils.objectMapper.readTree(giphyResponse);
		
		final SearchResponse searchResponse = SearchResponse.fromJsonNode(jsonNode, "ronaldinho");
		String serializedResponse = TestUtils.objectMapper
				.writerWithDefaultPrettyPrinter()
				.writeValueAsString(searchResponse);
		
		assertThat(serializedResponse).isEqualTo(expectedJson);
	}
}
