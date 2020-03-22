package com.covoid.tracker.covidtracker.secrets;

/**
 * This class manages retrieval of the security Keys
 */
public interface SecurityKeyManager
{
  /**
   * This method gets the security Key
   * @param key
   * @return
   */
  public String getSecretValue(String key) throws SecretAccessException;
  
  

}
