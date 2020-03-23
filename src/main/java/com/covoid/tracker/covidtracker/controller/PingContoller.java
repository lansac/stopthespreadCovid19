package com.covoid.tracker.covidtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.covoid.tracker.covidtracker.secrets.SecretAccessException;
import com.covoid.tracker.covidtracker.secrets.SecretKeyManager;

/**
 * This is very basic health check sample Ping API to be invoked to check if the app is deployed and running. TODO - Use
 * Spring Boot Actuator Health Check in future.
 * 
 * @author Sriram_H
 *
 */
@RestController
public class PingContoller
{
  @Autowired
  private SecretKeyManager securityKeyManager;

  @GetMapping("/ping")
  public String greeting()
  {
    return "Hello World";
  }

  @GetMapping("/")
  public String home()
  {
    return greeting();
  }

}
