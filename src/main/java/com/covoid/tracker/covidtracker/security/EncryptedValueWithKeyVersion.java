package com.covoid.tracker.covidtracker.security;

import lombok.Value;

/**
 * When a value is encrypted then the Key version is maintained so that
 * keys can be rotated for security and the same version is used to decrypt as well
 * @author jm733d
 *
 */
@Value
public class EncryptedValueWithKeyVersion
{
  
  private String encryptedText;
  private String version;

}
