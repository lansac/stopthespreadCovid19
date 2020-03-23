package com.covoid.tracker.covidtracker.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.covoid.tracker.covidtracker.dao.DeviceHistoryRepository;
import com.covoid.tracker.covidtracker.dao.DeviceProximityRepository;
import com.covoid.tracker.covidtracker.dao.DeviceRepository;
import com.covoid.tracker.covidtracker.dto.DeviceDTO;
import com.covoid.tracker.covidtracker.dto.DeviceProximityDTO;
import com.covoid.tracker.covidtracker.entity.Device;
import com.covoid.tracker.covidtracker.entity.DeviceHistory;
import com.covoid.tracker.covidtracker.entity.DeviceHistory.EventType;
import com.covoid.tracker.covidtracker.entity.DeviceProximity;
import com.covoid.tracker.covidtracker.exceptions.DeviceDataHandlingException;
import com.covoid.tracker.covidtracker.exceptions.SecurityException;
import com.covoid.tracker.covidtracker.secrets.SecretAccessException;
import com.covoid.tracker.covidtracker.security.DataSecurityManager;
import com.covoid.tracker.covidtracker.security.EncryptedValueWithKeyVersion;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DeviceDomainService
{

  @Autowired
  private DeviceRepository deviceRepository;

  @Autowired
  private DataSecurityManager dataSecurityManager;

  @Autowired
  private DeviceHistoryRepository deviceHistoryRepository;

  @Autowired
  private DeviceProximityRepository devicePromixityRepo;

  @Value("${app.persist.unencrypted-values:true}")
  private boolean isUnEncryptedValuesPersisted;

  /**
   * This method registers the device
   * 
   * @param deviceDTO
   * @return
   * @throws DeviceDataHandlingException
   */
  public Device registerDevice(DeviceDTO deviceDTO) throws DeviceDataHandlingException
  {
    EncryptedValueWithKeyVersion encryptedMacId;
    String originalMacId = null;
    String originalPhoneNumber = null;
    try
    {
      encryptedMacId = dataSecurityManager.encryptData(deviceDTO.getMacId());
      EncryptedValueWithKeyVersion encryptdPhoneNumber = dataSecurityManager.encryptData(deviceDTO.getPhoneNumber());
      if (isUnEncryptedValuesPersisted)
      {
        originalMacId = deviceDTO.getMacId();
        originalPhoneNumber = deviceDTO.getPhoneNumber();
      }
      Device device = new Device(encryptedMacId.getEncryptedText(), deviceDTO.getNotificationId(),
          encryptdPhoneNumber.getEncryptedText(), encryptedMacId.getVersion(), originalMacId, originalPhoneNumber);
      device = deviceRepository.save(device);
      deviceHistoryRepository.save(new DeviceHistory(device.getId(), EventType.REGISTER, encryptedMacId.getVersion()));
      return device;
    }
    catch (SecurityException | SecretAccessException e)
    {
      throw new DeviceDataHandlingException(e.getMessage(), e);
    }
  }

  /**
   * This method gets the Device by ID
   * 
   * @param id
   * @return
   * @throws DeviceDataHandlingException
   */
  public Device getDeviceDetailsByMacId(String macId) throws DeviceDataHandlingException
  {
    try
    {
      EncryptedValueWithKeyVersion encryptedMacId = dataSecurityManager.encryptData(macId);
      return deviceRepository.findByMacId(encryptedMacId.getEncryptedText()).orElse(null);

    }
    catch (SecurityException | SecretAccessException e)
    {
      throw new DeviceDataHandlingException(e.getMessage(), e);
    }
  }

  /**
   * This method deregisters the device
   * 
   * @param id
   * @throws DeviceDataHandlingException
   */
  public void deRegisterDevice(String id) throws DeviceDataHandlingException
  {
    Device device = getDeviceDetailsByMacId(id);
    if (null != device)
    {
      deviceHistoryRepository.save(new DeviceHistory(device.getId(), EventType.DE_REGISTER));
      deviceRepository.deleteById(id);
    }
    devicePromixityRepo.deleteByDeviceId(device.getId());

  }

  /**
   * This method pushs the device proximity data
   * @param deviceProximityDetails
   * @throws DeviceDataHandlingException
   */
  public void pushDeviceProximityData(List<DeviceProximityDTO> deviceProximityDetails)
      throws DeviceDataHandlingException
  {
    List<DeviceProximity> deviceProximityInfo = new ArrayList<DeviceProximity>();
    try
    {
      if (deviceProximityDetails != null && deviceProximityDetails.size() > 0)
      {
        Device device;

        device = getDeviceDetailsByMacId(deviceProximityDetails.get(0).getMacId());

        if (null == device)
        {
          return;
        }
        for (DeviceProximityDTO dataProximityDTO : deviceProximityDetails)
        {
          EncryptedValueWithKeyVersion encryptedMacId = dataSecurityManager
              .encryptData(dataProximityDTO.getProximityDeviceMacId());
          DeviceProximity deviceProximity = new DeviceProximity(dataProximityDTO, device.getId(),
              encryptedMacId.getEncryptedText(), isUnEncryptedValuesPersisted, encryptedMacId.getVersion());
          deviceProximityInfo.add(deviceProximity);
        }

        devicePromixityRepo.saveAll(deviceProximityInfo);
        deviceHistoryRepository.save(new DeviceHistory(deviceProximityDetails.get(0).getMacId(), EventType.UPLOAD,
            "Records uploaded : " + deviceProximityDetails.size()));
      }
    }
    catch (SecurityException | SecretAccessException e)
    {
      throw new DeviceDataHandlingException(e.getMessage(), e);
    }
  }
  
  public void deleteDeviceProximityData(String macId) throws DeviceDataHandlingException
  {
    Device device = getDeviceDetailsByMacId(macId);
    if (null!=device)
    {
      devicePromixityRepo.deleteByDeviceId(device.getId());
      deviceHistoryRepository.save(new DeviceHistory(device.getId(), EventType.CLEAR_PROXIMITY_DATA));
    }
    
  }
}
