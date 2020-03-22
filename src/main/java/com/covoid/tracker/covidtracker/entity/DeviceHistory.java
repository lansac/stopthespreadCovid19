package com.covoid.tracker.covidtracker.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

public class DeviceHistory {

	// TODO - For now having two separate documents; the device and the device history.
	// We can evaluate if we can sue DB ref (@DBRef); not in favor of embedding the documents.
	// TODO - All dates are by default in UTC in Mongo DB. The device might be in different time zones
	// TODO - Does MongoDB log all audit trails if so then this class or Mongo document is redundant.

	public enum EventType {
		REGISTER, UPLOAD, DE_REGISTER, QUARENTINED, SUSPECTED, CURED, NOTIFY, CLEAR_PROXIMITY_DATA
	};

	@Id
	private String id;

	@Indexed(unique = false)
	private String macId;
	private String eventType;
	private String eventDescription;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date eventTimestamp;

	public DeviceHistory() {

	}

	public DeviceHistory(String macId, EventType eventType, String... description) {
		this.macId = macId;
		this.eventType = eventType.toString();
		this.eventTimestamp = new Date();
		if (description != null && description.length > 0) {
			this.eventDescription = description[0];
		}

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

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

}
