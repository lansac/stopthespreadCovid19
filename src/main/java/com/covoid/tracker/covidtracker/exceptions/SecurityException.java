package com.covoid.tracker.covidtracker.exceptions;

public class SecurityException extends Exception
{
  
  public SecurityException(String message, Exception e)
  {
    super(message, e);
  }

}
