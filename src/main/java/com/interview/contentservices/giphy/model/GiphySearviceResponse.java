package com.interview.contentservices.giphy.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
public class GiphySearviceResponse implements Serializable {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -7308204404461876976L;
	
	private List<SearchResponse> data;
}
