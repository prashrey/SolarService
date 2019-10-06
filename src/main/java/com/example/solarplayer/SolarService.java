package com.example.solarplayer;

import com.example.solarplayer.services.impl.AlbumImpl;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/* @author Asif.Khan */

@Controller
@SpringBootApplication
public class SolarService {

	private static final Logger log = LoggerFactory.getLogger(SolarService.class);

	@Autowired
	private AlbumImpl album;

	@RequestMapping("/")
	@ResponseBody
	String root() {
		return "Don't try to act smart.";
	}

	@RequestMapping(value ="/search/album/{name}", method = {RequestMethod.GET})
	@ResponseBody
	String search(@PathVariable("name")String name) {
		return album.search(name);
	}

	@RequestMapping("/home")
	@ResponseBody
	String home() {
		return "Welcome!";
	}

	public static void main(String[] args) {
		SpringApplication.run(SolarService.class, args);
	}
}
