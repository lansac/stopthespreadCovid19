package com.covoid.tracker.covidtracker.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

import com.covoid.tracker.covidtracker.dto.DeviceProximityDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeviceProximity
{

  @Id
  private String id;

  @Indexed(unique = false)
  private String deviceId;

  @Indexed(unique = false)
  private String proximityDeviceMacId;

  @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
  private Date startTime;

  @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
  private Date endTime;

  private String originalProximityDeviceMacId;

  // TODO - Can we have duration here; which is computed field of start time - end time? Or we can get this from the
  // query.

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
  
  private String keyVersion;

  public DeviceProximity(DeviceProximityDTO deviceProximityDto, String deviceId, String encrytedProximityDeviceMacId,
    boolean isStoreOriginalMacId, String keyVersion)
  {
    this.deviceId = deviceId;
    this.startTime = deviceProximityDto.getStartTime();
    this.endTime = deviceProximityDto.getEndTime();
    if (isStoreOriginalMacId)
    {
      this.originalProximityDeviceMacId = deviceProximityDto.getProximityDeviceMacId();
    }
    this.proximityDeviceMacId = encrytedProximityDeviceMacId;
    this.bluetoothStrength = deviceProximityDto.getBluetoothStrength();
    this.geoPosition = deviceProximityDto.getGeoPosition();
    this.geoDistance = deviceProximityDto.getGeoDistance();
    this.keyVersion = keyVersion;
  }

}
