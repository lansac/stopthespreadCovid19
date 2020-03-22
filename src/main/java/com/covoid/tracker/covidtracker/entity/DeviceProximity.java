package com.covoid.tracker.covidtracker.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

public class DeviceProximity {

	@Id
	private String id;

	@Indexed(unique = false)
	private String macId;

	@Indexed(unique = false)
	private String proximityDeviceMacId;

	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date startTime;

	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	private Date endTime;
	
	// TODO - Can we have duration here; which is computed field of start time - end time? Or we can get this from the query.

	/**
	 * Represents the blue tooth signal strength. The lesser value the close proximity.
	 */
	private int bluetoothStrength;

	/**
	 * Represents the Geo-Location; will be populated when location tracking is enabled. Represents the latitude and longitude
	 */
	private double[] geoPosition;

	/**
	 * Represents the distance from the source to the target.
	 */
	private int geoDistance;

	public double[] getGeoPosition() {
		return geoPosition;
	}

	public void setGeoPosition(double[] geoPosition) {
		this.geoPosition = geoPosition;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getProximityDeviceMacId() {
		return proximityDeviceMacId;
	}

	public void setProximityDeviceMacId(String proximityDeviceMacId) {
		this.proximityDeviceMacId = proximityDeviceMacId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getBluetoothStrength() {
		return bluetoothStrength;
	}

	public void setBluetoothStrength(int bluetoothStrength) {
		this.bluetoothStrength = bluetoothStrength;
	}

	public int getGeoDistance() {
		return geoDistance;
	}

	public void setGeoDistance(int geoDistance) {
		this.geoDistance = geoDistance;
	}

}
