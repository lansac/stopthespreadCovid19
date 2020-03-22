package com.covoid.tracker.covidtracker.secrets;

/**
 * Exception class for secret access
 *
 */
public class SecretAccessException extends Exception
{
  
  public SecretAccessException(String exceptionMessage, Throwable t)
  {
    super(exceptionMessage, t);
  }

}
