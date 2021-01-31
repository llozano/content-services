package com.interview.contentservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interview.contentservices.giphy.model.GiphySearviceResponse;
import com.interview.contentservices.giphy.service.GiphyService;

@RestController
@RequestMapping("/{version}/giphy")
public class GiphySearchController {

	@Autowired
	private GiphyService giphyService;

	@GetMapping(path = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GiphySearviceResponse> search(
            @PathVariable final String version, 
            @RequestParam(name = "searchTerm", required = false) List<String> searchTerms
            ) {
		final GiphySearviceResponse response = giphyService.search(searchTerms);
		
		return ResponseEntity.ok(response);
	}
}
