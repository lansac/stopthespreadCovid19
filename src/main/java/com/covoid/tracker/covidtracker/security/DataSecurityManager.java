package com.covoid.tracker.covidtracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.covoid.tracker.covidtracker.exceptions.SecurityException;
import com.covoid.tracker.covidtracker.secrets.SecretAccessException;
import com.covoid.tracker.covidtracker.secrets.SecretKeyManager;
import com.covoid.tracker.covidtracker.secrets.SecretValueWithVersion;
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

  private String salt = "Salt";

  /**
   * This method encrypts the data passed as text by using the SALT key
   * 
   * @param plainText
   * @return encryptedKey
   * @throws SecretAccessException
   * @throws SecurityException
   */
  public EncryptedValueWithKeyVersion encryptData(String plainText) throws SecurityException, SecretAccessException
  {
    SecretValueWithVersion secretWithVersion = secretKeyManager.getSecretValue(keyForSecret);
    String encryptedText = EncryptDecryptUtil.encrypt(plainText,
        secretWithVersion.getSecretValue(), salt);
    return new EncryptedValueWithKeyVersion(encryptedText, secretWithVersion.getSecretVersion());
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
  public String decryptData(String encryptedText, String version) throws SecurityException, SecretAccessException
  {
    String plainText = EncryptDecryptUtil.decrypt(encryptedText,
        secretKeyManager.getSecretValue(keyForSecret, version).getSecretValue(), salt);
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
  public String decryptData(String encryptedText) throws SecurityException, SecretAccessException
  {
    return decryptData(encryptedText, "latest");
  }

}
