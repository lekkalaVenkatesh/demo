package com.paulCamper.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.paulCamper.service.FileService;

public class PipeLineTest {

	private static FileService fileservice;

	@BeforeAll
	public static void initialize() {
		fileservice = new FileService();
	}

	@Test
	public void processFile() throws IOException {

		Instant start = Instant.now();
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("dain-challenge-data.jsonl").getFile());
		assertTrue(fileservice.processFile(file.getAbsolutePath()));
		Instant end = Instant.now();
		System.out.println("Time took in Seconds to Execte in Seconds: " + Duration.between(start, end).getSeconds());

	}

}
