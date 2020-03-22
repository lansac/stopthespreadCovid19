package com.covoid.tracker.covidtracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.covoid.tracker.covidtracker.secrets.SecurityKeyManager;

@SpringBootApplication
@Configuration
// public class CovidTrackerApplication implements CommandLineRunner {
public class CovidTrackerApplication
{

  // @Autowired
  // private DeviceRepository deviceRepository;

  public static void main(String[] args)
  {
    SpringApplication.run(CovidTrackerApplication.class, args);
  }
  


}
