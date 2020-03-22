package com.covoid.tracker.covidtracker.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class Device {

	@Id
	private String id;

	@Indexed(unique = false)
	private String macId;
	private String notificationId;

	private String phoneNumber;
	
	// TODO - Instead of these lousy boleans can we have one status field here?

	/**
	 * Marked to true when a person is infected and then false when a person has recovered; gives the current status only.
	 */
	private boolean isInfected = false;

	/**
	 * Marked to true when a person is quarantined or has suspect symptoms and false when person is out of quarantine
	 */
	private boolean isSuspected = false;

	/**
	 * Marked to true if the person has been notified; the flag will reset once the notification is no longer valid.
	 */
	private boolean isNotified = false;

	public Device() {
	}

	public Device(String macId, String notificationId, String phoneNumber) {
		this.macId = macId;
		this.notificationId = notificationId;
		this.phoneNumber = phoneNumber;
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

	public boolean isInfected() {
		return isInfected;
	}

	public void setInfected(boolean isInfected) {
		this.isInfected = isInfected;
	}

	public boolean isSuspected() {
		return isSuspected;
	}

	public void setSuspected(boolean isSuspected) {
		this.isSuspected = isSuspected;
	}

	public boolean isNotified() {
		return isNotified;
	}

	public void setNotified(boolean isNotified) {
		this.isNotified = isNotified;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
