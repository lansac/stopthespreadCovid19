package com.covoid.tracker.covidtracker.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DeviceProximityDTO
{
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
  /**
   * Represents the blue tooth signal strength. The lesser value the close proximity.
   */
  private int bluetoothStrength;

  /**
   * Represents the Geo-Location; will be populated when location tracking is enabled. Represents the latitude and
   * longitude
   */
  private double[] geoPosition;

  /**
   * Represents the distance from the source to the target.
   */
  private int geoDistance;
  


}
