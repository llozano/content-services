package com.interview.contentservices.giphy.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"gifId"})
@Getter
@Setter
@ToString
public class Gif implements Serializable, Comparable<Gif> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9083320655913802661L;

	private String gifId;
	
	private String url;

	@JsonProperty("id")
	public void setGifId(String gifId) {
		this.gifId = gifId;
	}
	
	@JsonProperty("gif_id")
	public String getGifId() {
		return gifId;
	}

	@Override
	public int compareTo(Gif o) {
		return (o != null) ? gifId.compareTo(o.gifId) : 0;
	}
}