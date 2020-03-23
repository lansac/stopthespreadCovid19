package com.covoid.tracker.covidtracker.security.util;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.covoid.tracker.covidtracker.exceptions.SecurityException;

public class EncryptDecryptUtilTest
{

  @Test
  public void testEncryptAndDecrypt() throws SecurityException
  {
    String encryptedText = EncryptDecryptUtil.encrypt("blueToothId", "Secret", "Salt");
    String plainText = EncryptDecryptUtil.decrypt(encryptedText, "Secret", "Salt");
    assertEquals("blueToothId", plainText);
  }
  
  @Test(expected=SecurityException.class)
  public void testEncryptAndDecryptWithWrongSalt() throws SecurityException
  {
    String encryptedText = EncryptDecryptUtil.encrypt("blueToothId", "Secret", "Salt");
    String plainText = EncryptDecryptUtil.decrypt(encryptedText, "Secret", "Malt");
  }
  
  @Test(expected=SecurityException.class)
  public void testEncryptAndDecryptWithWrongSecret() throws SecurityException
  {
    String encryptedText = EncryptDecryptUtil.encrypt("blueToothId", "Secret", "Salt");
    String plainText = EncryptDecryptUtil.decrypt(encryptedText, "Secret1", "Salt");
  }

}
