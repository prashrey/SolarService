package com.example.solarplayer;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/* @author Asif.Khan */

@Controller
@SpringBootApplication
public class DemoApplication {

	private static String API_KEY = "403bec2396b250dd857980698d2070b8";
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	String GET_URL = "http://ws.audioscrobbler.com/2.0/?method=album.search&album=Believe&api_key=403bec2396b250dd857980698d2070b8&format=json";

	@RequestMapping("/")
	@ResponseBody
	String root() {
		return "Don't try to act smart.";
	}

	@RequestMapping("/search")
	@ResponseBody
	 String search() {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(GET_URL);
			HttpResponse response = client.execute(request);
			log.info("Sending 'GET' request to URL : " + GET_URL);
			log.info("Response Code : " + response.getStatusLine().getStatusCode());
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		} catch (Exception e) {
			log.info("Exception Caused: " + e);
		}
		return null;
	}

	@RequestMapping("/home")
	@ResponseBody
	String home() {
		return "Welcome!";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
