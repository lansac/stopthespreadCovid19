package com.covoid.tracker.covidtracker.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.covoid.tracker.covidtracker.entity.DeviceProximity;

public interface DeviceProximityRepository extends MongoRepository<DeviceProximity, String> {

	public Optional<List<DeviceProximity>> findByDeviceId(String deviceId);
	
	public long deleteByDeviceId(String deviceId);

}
