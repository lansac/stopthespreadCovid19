package com.covoid.tracker.covidtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// public class CovidTrackerApplication implements CommandLineRunner {
public class CovidTrackerApplication  {

//	@Autowired
//	private DeviceRepository deviceRepository;

	public static void main(String[] args) {
		SpringApplication.run(CovidTrackerApplication.class, args);
	}

	// Sample code to confirm we can access Mongo DB through Spring Data Mongo Repository.
	
	// @Override
	// public void run(String... args) throws Exception {
	//
	// // Delete all the data
	// deviceRepository.deleteAll();
	//
	// // Insert one device record
	// deviceRepository.save(new Device("mac1", "not1"));
	//
	// // Get all device records
	// for (Device device : deviceRepository.findAll()) {
	// System.out
	// .println("Data stored in MongoDB is " + device.getId() + "\t" + device.getMacId() + "\t" + device.getNotificationId());
	// }
	//
	// }

}
