package com.covoid.tracker.covidtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covoid.tracker.covidtracker.dao.DeviceRepository;
import com.covoid.tracker.covidtracker.entity.Device;

@RestController
@RequestMapping("/device")
public class DeviceController {

	// TODO FIXME write service classes; currently all logic is hosted into controller.
	// TODO FIXME Use JAX-RS annotations instead of Spring annotations

	@Autowired
	private DeviceRepository deviceRepository;

	@PostMapping("/register")
	Device registerDevice(@RequestBody Device device) {
		return deviceRepository.save(device);
	}

	// TODO FIXME to be deleted this later we should not be exposing GET API
	@GetMapping("/{id}")
	Device one(@PathVariable String id) {

		return deviceRepository.findById(id).orElse(new Device());
	}

	@DeleteMapping("/{id}")
	void deleteEmployee(@PathVariable String id) {
		deviceRepository.deleteById(id);
	}

}
