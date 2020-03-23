package com.covoid.tracker.covidtracker.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class DeviceHistory
{

  // TODO - For now having two separate documents; the device and the device history.
  // We can evaluate if we can sue DB ref (@DBRef); not in favor of embedding the documents.
  // TODO - All dates are by default in UTC in Mongo DB. The device might be in different time zones
  // TODO - Does MongoDB log all audit trails if so then this class or Mongo document is redundant.

  public enum EventType
  {
    REGISTER, UPLOAD, DE_REGISTER, QUARENTINED, SUSPECTED, CURED, NOTIFY, CLEAR_PROXIMITY_DATA
  };

  @Id
  private String id;

  @Indexed(unique = false)
  private String deviceId;
  private String eventType;
  private String eventDescription;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date eventTimestamp;
  

  public DeviceHistory()
  {

  }

  public DeviceHistory(String deviceId, EventType eventType,String... description)
  {
    this.deviceId = deviceId;
    this.eventType = eventType.toString();
    this.eventTimestamp = new Date();
    if (description != null && description.length > 0)
    {
      this.eventDescription = description[0];
    }
  }

}
