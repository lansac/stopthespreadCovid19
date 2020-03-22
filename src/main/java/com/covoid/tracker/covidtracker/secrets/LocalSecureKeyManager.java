package com.covoid.tracker.covidtracker.secrets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Security Key manager for local. Reads value from property
 * @author jm733d
 *
 */
@Service
@ConditionalOnProperty(value="spring.profile", havingValue="local", matchIfMissing=true)
public class LocalSecureKeyManager implements SecretKeyManager
{
  @Autowired
  private Environment environment;

  @Override
  public String getSecretValue(String key)
  {
    return environment.getProperty(key);
  }
  
  @Override
  public String getSecretValue(String key, String version)
  {
    // No version support in properties
    return environment.getProperty(key);
  }

}
