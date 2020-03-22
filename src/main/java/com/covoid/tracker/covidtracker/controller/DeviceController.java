package com.covoid.tracker.covidtracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covoid.tracker.covidtracker.dao.DeviceHistoryRepository;
import com.covoid.tracker.covidtracker.dao.DeviceProximityRepository;
import com.covoid.tracker.covidtracker.dao.DeviceRepository;
import com.covoid.tracker.covidtracker.entity.Device;
import com.covoid.tracker.covidtracker.entity.DeviceHistory;
import com.covoid.tracker.covidtracker.entity.DeviceHistory.EventType;
import com.covoid.tracker.covidtracker.entity.DeviceProximity;

@RestController
@RequestMapping("/device")
public class DeviceController {

	// TODO FIXME write service classes; currently all logic is hosted into controller.
	// TODO FIXME Use JAX-RS annotations instead of Spring annotations

	// TODO - Logging :)

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private DeviceHistoryRepository deviceHistoryRepository;

	@Autowired
	private DeviceProximityRepository devicePromixityRepo;

	/**
	 * On registering the device a unique id is generated. This id is then used to generate soft access tokens for the device.
	 * 
	 * @param device
	 * @return
	 */
	@PostMapping("/register")
	public Device registerDevice(@RequestBody Device device) {
		device = deviceRepository.save(device);
		deviceHistoryRepository.save(new DeviceHistory(device.getMacId(), EventType.REGISTER));
		return device;
	}

	// TODO FIXME to be deleted this later we should not be exposing GET API
	@GetMapping("/{id}")
	public Device getDeviceDetails(@PathVariable String id) {
		return deviceRepository.findById(id).orElse(new Device());
	}

	// TODO FIXME to be deleted this later we should not be exposing GET API
	@GetMapping("/{id}/history")
	public List<DeviceHistory> getDeviceHistoryDetails(@PathVariable String id) {

		Device device = getDeviceDetails(id);
		if (device.getId() != null && !device.getId().isEmpty()) {
			return deviceHistoryRepository.findByMacId(device.getMacId()).orElse(new ArrayList<DeviceHistory>());
		}

		return new ArrayList<DeviceHistory>();
	}

	// TODO - FIXME - I assume this should be called when we un-install the app
	// In this scenario i think we need to delete the device data and device proximity data but keep the history information for tracking
	@DeleteMapping("/{id}")
	public void deRegisterDevice(@PathVariable String id) {

		Device device = getDeviceDetails(id);
		if (device.getId() != null && !device.getId().isEmpty()) {
			deviceHistoryRepository.save(new DeviceHistory(device.getMacId(), EventType.DE_REGISTER));
			devicePromixityRepo.deleteByMacId(device.getMacId());
			deviceRepository.deleteById(id);
		}

	}

	@PostMapping("/{id}/deviceData")
	public List<DeviceProximity> pushDeviceProximityData(@RequestBody List<DeviceProximity> deviceProximityDetails) {

		if (deviceProximityDetails != null && deviceProximityDetails.size() > 0) {

			deviceProximityDetails = devicePromixityRepo.saveAll(deviceProximityDetails);
			deviceHistoryRepository.save(new DeviceHistory(deviceProximityDetails.get(0).getMacId(), EventType.UPLOAD,
					"Records uploaded : " + deviceProximityDetails.size()));
			return deviceProximityDetails;
		}
		
		return new ArrayList<DeviceProximity>();

	}

	// TODO FIXME to be deleted this later we should not be exposing GET API
	// This is direct dump of all devices in proximity
	@GetMapping("/{id}/deviceData")
	public List<DeviceProximity> getDeviceProximityDetails(@PathVariable String id) {

		Device device = getDeviceDetails(id);
		if (device.getId() != null && !device.getId().isEmpty()) {
			return devicePromixityRepo.findByMacId(device.getMacId()).orElse(new ArrayList<DeviceProximity>());
		}

		return new ArrayList<DeviceProximity>();
	}

	@DeleteMapping("/{id}/deviceData")
	public void deleteDeviceProximityData(@PathVariable String id) {

		Device device = getDeviceDetails(id);
		if (device.getId() != null && !device.getId().isEmpty()) {
			devicePromixityRepo.deleteByMacId(device.getMacId());
			deviceHistoryRepository.save(new DeviceHistory(device.getMacId(), EventType.CLEAR_PROXIMITY_DATA));
		}

	}
	
	// Logic to get impacted records
	// Input Source Mac id, timestmap.
	// Get all proximity data based on source mac id and timestmap based on following conditions
	// Filter this data by strength; get all strength value < -40 OR
	// Also filter where strength value between 40 - 60 and single duration time > 30 minutes OR
	// Also filter where strength value between 40 - 60 and SUM duration time > 2 hours OR
	// Also filter where strength value between 40 - 60 and number of occurrences >=5 and each single duration minimum of 3 minutes OR
	// Also filter where strength value between 60 - 80 and single duration time > 60 minutes OR
	// Also filter where strength value between 60 - 80 and SUM duration time > 4 hours OR
	// Also filter where strength value between 60 - 80 and number of occurrences >=10 and each single duration minimum of 3 minutes OR

}
