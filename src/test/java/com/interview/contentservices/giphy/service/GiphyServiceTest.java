package com.interview.contentservices.giphy.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.interview.contentservices.giphy.model.GiphySearviceResponse;



@RunWith(MockitoJUnitRunner.class)
public class GiphyServiceTest {
	
	@Mock
	@Qualifier("giphyRestTemplate")
	private RestTemplate restTemplate;
	
	@InjectMocks
	private GiphyService giphyService;
	
	@Qualifier("threadPollTaskExcutor")
	private Executor executor = Executors.newFixedThreadPool(2);
	
	
	@BeforeEach
	public void setup() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		ReflectionTestUtils.setField(giphyService, "giphyApiUrl", "http://fake.io");
		ReflectionTestUtils.setField(giphyService, "executor", executor);
	}
	
	

	/**
	 * Sorry for not providing more Unit test
	 * @throws Exception
	 */
	@Test
	void search() throws Exception {
		List<String> searchTerms = Lists.list("ronaldinho", "dinho");
		GiphySearviceResponse response = giphyService.search(searchTerms);
		
		assertThat(response).isNotNull();
	}
}
