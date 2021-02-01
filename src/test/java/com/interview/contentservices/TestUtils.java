package com.interview.contentservices;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
	
	
	public static ObjectMapper objectMapper;
	
	static {
		objectMapper = new ObjectMapper();
	}
	
	/**
	 * Read resource from folder.
	 * @param resource
	 * @return
	 * @throws Exception
	 */
	public static String readResource(String resource) throws Exception {
		ClassLoader classLoader = TestUtils.class.getClassLoader();
		try (InputStream inputStream = classLoader.getResourceAsStream(resource)) {
			return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
		} catch (Exception ex) {
			throw ex;
		}
		
	}
	
}
