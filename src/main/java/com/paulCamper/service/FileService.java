package com.paulCamper.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulCamper.model.SensorData;
import com.paulCamper.model.SensorDataOutput;

public class FileService {

	public boolean processFile(String path) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		List<SensorData> sensorsData = new ArrayList<SensorData>();

		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
			inputStream = new FileInputStream(path);
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				SensorData sensorData = mapper.readValue(line, SensorData.class);
				sensorsData.add(sensorData);

			}
			// note that Scanner suppresses exceptions
			if (sc.ioException() != null) {
				throw sc.ioException();
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (sc != null) {
				sc.close();
			}
		}

		Map<String, Map<String, List<SensorData>>> sensorsDataMap = grouping(sensorsData);
		createReport(sensorsDataMap, path);
		return true;
	}

	public Map<String, Map<String, List<SensorData>>> grouping(List<SensorData> sensorsData) {

		Map<String, Map<String, List<SensorData>>> sensorsDataMap = sensorsData.stream()
				.collect(Collectors.groupingBy(SensorData::getDate, Collectors.groupingBy(SensorData::getInput)));

		return sensorsDataMap;
	}

	public boolean createReport(Map<String, Map<String, List<SensorData>>> sensorsDataMap, String path) {

		PrintWriter printWriter = null;
		ObjectMapper Obj = new ObjectMapper();

		try {
			printWriter = getPrintWriter(path);

			for (Entry<String, Map<String, List<SensorData>>> entry : sensorsDataMap.entrySet()) {

				Map<String, List<SensorData>> groupedByInputMap = entry.getValue();

				for (Entry<String, List<SensorData>> entryForInput : groupedByInputMap.entrySet()) {

					// Step #4. Perform write operation.

					String jsonStr = Obj.writeValueAsString(new SensorDataOutput(entry.getKey(), entryForInput.getKey(),
							getmedian(entryForInput.getValue())));

					printWriter.println(jsonStr);

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			printWriter.close();
		}

		return true;
	}

	public PrintWriter getPrintWriter(String path) throws IOException {

		// Step #1. Create a file.
		File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
		File fileWithRelativePath = new File(tempDirectory, "smallFile_Output.jsonl");
		// Step #2. Create a file writer object with above file.
		FileWriter fileWriter = new FileWriter(fileWithRelativePath, true);
		System.out.println("File is created in directory" + fileWithRelativePath.getAbsolutePath());
		// Step #3. Create a file object with above file writer.
		PrintWriter writer = new PrintWriter(fileWriter);
		return writer;
	}

	double getmedian(List<SensorData> sensorData) {

		DoubleStream sortedSensorData = sensorData.stream().mapToDouble(SensorData::getValue).sorted();
		double median = sensorData.size() % 2 == 0
				? sortedSensorData.skip(sensorData.size() / 2 - 1).limit(2).average().getAsDouble()
				: sortedSensorData.skip(sensorData.size() / 2).findFirst().getAsDouble();

		return median;
	}

}
