package com.yurii.springbootmap;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Covid19Confirmed {

	public static final String COVID_CONFIRMED_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";

	public List<Point> getCovidData() throws IOException, InterruptedException {
		List<Point> points = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		String values = restTemplate.getForObject(COVID_CONFIRMED_URL, String.class);

		StringReader stringReader = new StringReader(values);
		CSVParser parse = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader);

		for (CSVRecord strings : parse) {
			double lat = Double.parseDouble(strings.get("Lat"));
			double lon = Double.parseDouble(strings.get("Long"));
			String text = strings.get("3/15/20");
			points.add(new Point(lat, lon, text));
		}
		return points;
	}
}
