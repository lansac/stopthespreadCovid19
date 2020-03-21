package com.covoid.tracker.covidtracker.entity;

import org.springframework.data.annotation.Id;

public class Device {

	@Id
	public String id;

	public String macId;
	public String notificationId;

	public Device() {
	}

	public Device(String macId, String notificationId) {
		this.macId = macId;
		this.notificationId = notificationId;
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

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

}
