package com.covoid.tracker.covidtracker.secrets;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.google.cloud.secretmanager.v1.AccessSecretVersionRequest;
import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretVersionName;

@Service
@ConditionalOnProperty(value = "spring.profile", havingValue = "GCP", matchIfMissing = false)
public class GoogleSecureKeyManager implements SecretKeyManager
{
  @Value("${gcp.project-id:stopthespreadcovid19}")
  private String projectId;

  private SecretManagerServiceClient secretManagerClient = null;

  @Override
  public String getSecretValue(String secretId) throws SecretAccessException
  {
    return getSecretValue(secretId, "latest");
  }
  
  @Override
  public String getSecretValue(String secretId, String version) throws SecretAccessException
  {
    SecretVersionName secretVersionName = SecretVersionName.of(projectId, secretId, "latest");
    AccessSecretVersionRequest request = AccessSecretVersionRequest.newBuilder().setName(secretVersionName.toString())
        .build();
    AccessSecretVersionResponse response = secretManagerClient.accessSecretVersion(request);
    return response.getPayload().getData().toStringUtf8();
  }

  @PostConstruct
  public void initiateSecretManagerClient() throws SecretAccessException
  {
    try
    {
      secretManagerClient = SecretManagerServiceClient.create();
    }
    catch (IOException e)
    {
      throw new SecretAccessException(e.getMessage(), e);
    }
  }

}
