package com.covoid.tracker.covidtracker.secrets;

/**
 * This class manages retrieval of the security Keys
 */
public interface SecretKeyManager
{
  /**
   * This method gets the security Key
   * @param key
   * @return
   */
  public SecretValueWithVersion getSecretValue(String key) throws SecretAccessException;
  
  /**
   * This method gets the Secret for a given version
   * @param secretId
   * @param version
   * @return
   * @throws SecretAccessException
   */
  public SecretValueWithVersion getSecretValue(String secretId, String version) throws SecretAccessException;
  
  

}
