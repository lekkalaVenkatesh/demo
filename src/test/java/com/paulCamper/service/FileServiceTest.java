package com.paulCamper.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.paulCamper.model.SensorData;

public class FileServiceTest {

	static FileService fileService = null;

	@BeforeAll
	public static void initialze() {
		fileService = new FileService();
	}

	@Test
	void testGetMedian() {
		List<SensorData> sensorDataList = new ArrayList<SensorData>();
		sensorDataList.add(new SensorData());
		assertEquals(0.0, fileService.getmedian(sensorDataList));

	}

}
