package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DeviceDetailsDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.repositories.DeviceRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) { this.deviceRepository = deviceRepository; }

    public List<DeviceDetailsDTO> findDevices() {
        List<Device> deviceList = deviceRepository.findAll();
        return deviceList.stream()
                .map(DeviceBuilder::toDeviceDetailsDTO)
                .collect(Collectors.toList());
    }

    public UUID insert(DeviceDetailsDTO deviceDetailsDTO) {
        Device device = DeviceBuilder.toEntity(deviceDetailsDTO);
        device = deviceRepository.save(device);
        LOGGER.debug("Device with id {} was inserted in db", device.getId());
        return device.getId();
    }

    public UUID updateDevice(UUID id, DeviceDetailsDTO deviceDetailsDTO) throws ResourceNotFoundException {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found on :: "+ id));

        if(deviceDetailsDTO.getDescription() != "")
            device.setDescription(deviceDetailsDTO.getDescription());

        if(deviceDetailsDTO.getAddress() != "")
            device.setAddress(deviceDetailsDTO.getAddress());

        if(deviceDetailsDTO.getMax_consumption() != 0)
            device.setMax_consumption(deviceDetailsDTO.getMax_consumption());

        if(deviceDetailsDTO.getAverage_consumption() != 0)
            device.setAverage_consumption(deviceDetailsDTO.getAverage_consumption());

        deviceRepository.save(device);
        return device.getId();
    }

    public UUID deleteDevice(UUID deviceId)throws ResourceNotFoundException {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found on :: "+ deviceId));
        deviceRepository.delete(device);
        return device.getId();
    }
}
