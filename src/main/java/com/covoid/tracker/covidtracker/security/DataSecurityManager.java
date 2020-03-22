package com.covoid.tracker.covidtracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covoid.tracker.covidtracker.exceptions.SecurityException;
import com.covoid.tracker.covidtracker.secrets.SecretAccessException;
import com.covoid.tracker.covidtracker.secrets.SecretKeyManager;
import com.covoid.tracker.covidtracker.security.util.EncryptDecryptUtil;

/**
 * Provides API for data security
 *
 */
@Service
public class DataSecurityManager
{
  @Autowired
  private SecretKeyManager secretKeyManager;

  private String keyForSecret = "encrypt-key";

  /**
   * This method encrypts the data passed as text by using the SALT key
   * 
   * @param plainText
   * @return encryptedKey
   * @throws SecretAccessException
   * @throws SecurityException
   */
  public String encryptData(String plainText, String salt) throws SecurityException, SecretAccessException
  {
    String encryptedText = EncryptDecryptUtil.encrypt(plainText, secretKeyManager.getSecretValue(keyForSecret), salt);
    return encryptedText;
  }

  /**
   * This method decrypts the data using the specific version if the key
   * 
   * @param encryptedText
   * @param version
   * @return
   * @throws SecretAccessException
   * @throws SecurityException
   */
  public String decryptData(String encryptedText, String version, String salt)
      throws SecurityException, SecretAccessException
  {
    String plainText = EncryptDecryptUtil.decrypt(encryptedText,
        secretKeyManager.getSecretValue(keyForSecret, version), salt);
    return plainText;
  }

  /**
   * This method decrypts the data using the latest version of the Key
   * 
   * @param encryptedText
   * @return plainText
   * @throws SecretAccessException
   * @throws SecurityException
   */
  public String decryptData(String encryptedText, String salt) throws SecurityException, SecretAccessException
  {
    return decryptData(encryptedText, "latest", salt);
  }

}
