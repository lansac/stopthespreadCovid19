package com.covoid.tracker.covidtracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is very basic health check sample Ping API to be invoked to check if the app is deployed and running. 
 * TODO - Use Spring Boot Actuator Health Check in future.
 * 
 * @author Sriram_H
 *
 */
@RestController
public class PingContoller {

	@GetMapping("/ping")
	public String greeting() {
		return "Hello World";
	}

}
