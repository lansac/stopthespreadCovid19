package com.covoid.tracker.covidtracker.dto;

import lombok.Data;

@Data
public class DeviceDTO
{
  private String id;

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

  public DeviceDTO(String macId, String notificationId, String phoneNumber)
  {
    this.macId = macId;
    this.notificationId = notificationId;
    this.phoneNumber = phoneNumber;
  }

}
