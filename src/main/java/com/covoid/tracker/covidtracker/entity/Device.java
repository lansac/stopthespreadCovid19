package com.covoid.tracker.covidtracker.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Device
{

  @Id
  private String id;

  @Indexed(unique = false)
  private String macId;
  private String notificationId;

  private String phoneNumber;

  private String salt;

  private String keyVersion;

  private String originalMacId;

  private String originalPhoneNumber;

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

  public Device(String macId, String notificationId, String phoneNumber, String keyVersion, String originalMacId,
    String originalPhoneNumber)
  {
    this.macId = macId;
    this.notificationId = notificationId;
    this.phoneNumber = phoneNumber;
    this.originalMacId= originalMacId;
    this.originalPhoneNumber = originalPhoneNumber;
  }

  public void markAsSuspect()
  {
    this.isSuspected = true;
  }

  public void markAsInfected()
  {
    this.isInfected = true;
  }

  public void markAsNotified()
  {
    this.isNotified = true;
  }

}
