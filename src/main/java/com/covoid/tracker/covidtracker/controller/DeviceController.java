package com.covoid.tracker.covidtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.covoid.tracker.covidtracker.domain.service.DeviceDomainService;
import com.covoid.tracker.covidtracker.dto.DeviceDTO;
import com.covoid.tracker.covidtracker.dto.DeviceProximityDTO;
import com.covoid.tracker.covidtracker.entity.Device;
import com.covoid.tracker.covidtracker.exceptions.DeviceDataHandlingException;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/device")
@Log4j2
public class DeviceController
{

  // TODO FIXME write service classes; currently all logic is hosted into controller.
  // TODO FIXME Use JAX-RS annotations instead of Spring annotations

  // TODO - Logging :)

  @Autowired
  private DeviceDomainService deviceDomainService;

  /**
   * On registering the device a unique id is generated. This id is then used to generate soft access tokens for the
   * device.
   * 
   * @param device
   * @return
   * @throws DeviceDataHandlingException
   */
  @PostMapping("/register")
  public Device registerDevice(@RequestBody DeviceDTO device) throws DeviceDataHandlingException
  {
    return deviceDomainService.registerDevice(device);
  }

  // TODO - FIXME - I assume this should be called when we un-install the app
  // In this scenario i think we need to delete the device data and device proximity data but keep the history
  // information for tracking
  @DeleteMapping("/{id}")
  public void deRegisterDevice(@PathVariable String id) throws DeviceDataHandlingException
  {
    deviceDomainService.deRegisterDevice(id);
  }

  @PostMapping("/{id}/deviceData")
  public void pushDeviceProximityData(@RequestBody List<DeviceProximityDTO> deviceProximityDetails)
      throws DeviceDataHandlingException
  {
    deviceDomainService.pushDeviceProximityData(deviceProximityDetails);
  }

  @DeleteMapping("/{id}/deviceData")
  public void deleteDeviceProximityData(@PathVariable String macId) throws DeviceDataHandlingException
  {
    deviceDomainService.deleteDeviceProximityData(macId);
  }

  // Logic to get impacted records
  // Input Source Mac id, timestmap.
  // Get all proximity data based on source mac id and timestmap based on following conditions
  // Filter this data by strength; get all strength value < -40 OR
  // Also filter where strength value between 40 - 60 and single duration time > 30 minutes OR
  // Also filter where strength value between 40 - 60 and SUM duration time > 2 hours OR
  // Also filter where strength value between 40 - 60 and number of occurrences >=5 and each single duration minimum of
  // 3 minutes OR
  // Also filter where strength value between 60 - 80 and single duration time > 60 minutes OR
  // Also filter where strength value between 60 - 80 and SUM duration time > 4 hours OR
  // Also filter where strength value between 60 - 80 and number of occurrences >=10 and each single duration minimum of
  // 3 minutes OR

}
