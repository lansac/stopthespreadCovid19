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
public class LocalSecurityKeyManager implements SecurityKeyManager
{
  @Autowired
  private Environment environment;

  @Override
  public String getSecretValue(String key)
  {
    // TODO Auto-generated method stub
    return environment.getProperty(key);
  }

}
