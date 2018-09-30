package com.example.solarplayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/* @author Asif.Khan */

@Controller
@SpringBootApplication
public class DemoApplication {

	@RequestMapping("/search")
	@ResponseBody
	String home(){
		return "Whoa, what are you doing here! Back off!!!!";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
