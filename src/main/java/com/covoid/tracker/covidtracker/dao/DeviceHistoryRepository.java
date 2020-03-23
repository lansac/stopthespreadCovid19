package com.covoid.tracker.covidtracker.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.covoid.tracker.covidtracker.entity.DeviceHistory;

public interface DeviceHistoryRepository extends MongoRepository<DeviceHistory, String> {

	public Optional<List<DeviceHistory>> findByDeviceId(String macId);

}
