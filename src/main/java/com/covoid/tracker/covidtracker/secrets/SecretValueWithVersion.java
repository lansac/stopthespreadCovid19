package com.covoid.tracker.covidtracker.secrets;

import lombok.Value;

@Value
public class SecretValueWithVersion
{ 
  private String secretValue;
  private String secretVersion;

}
