package com.covoid.tracker.covidtracker.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.covoid.tracker.covidtracker.entity.Device;

public interface DeviceRepository extends MongoRepository<Device, String> {
	
	public Optional<Device> findByMacId(String macId);

}
