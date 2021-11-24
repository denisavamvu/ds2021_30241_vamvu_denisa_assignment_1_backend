package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.dtos.DeviceDetailsDTO;
import ro.tuc.ds2020.dtos.SensorDetailsDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.dtos.builders.SensorBuilder;
import ro.tuc.ds2020.dtos.builders.UserBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;
    @Autowired
    private SensorBuilder sensorBuilder;

    @Autowired
    private DeviceBuilder deviceBuilder;

    @Autowired
    private UserBuilder userBuilder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) { this.deviceRepository = deviceRepository; }

    public List<DeviceDetailsDTO> findDevices() {
        List<Device> deviceList = deviceRepository.findAll();
        return deviceList.stream()
                .map(device-> deviceBuilder.toDeviceDetailsDTO(device))
                .collect(Collectors.toList());
    }

    public Device getDevice(UUID id){
        return deviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Device not found on :: "+ id));
    }
    public List<DeviceDetailsDTO> findDevicesWithoutSensors(){
        List<Device> deviceList = deviceRepository.findAll();
        return deviceList.stream().filter(device -> device.getSensor() == null)
                .map(device-> deviceBuilder.toDeviceDetailsDTO(device))
                .collect(Collectors.toList());
    }
    public UUID insert(DeviceDetailsDTO deviceDetailsDTO) {
        Device device = deviceBuilder.toEntity(deviceDetailsDTO);
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
                .orElseThrow(() -> new ResourceNotFoundException("Device not found on :: "+ deviceId));
        deviceRepository.deleteById(deviceId);
        return deviceId;
    }

    @Transactional
    public UUID addSensorToDevice(UUID deviceId, SensorDetailsDTO sensorDetailsDTO)
    {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found on :: "+ deviceId));
        Sensor sensor = sensorBuilder.toEntity(sensorDetailsDTO);
        device.setSensor(sensor);
        deviceRepository.save(device);
        return deviceId;
    }
    @Transactional
    public UUID addUserToDevice(UUID deviceId, ClientDTO clientDTO)
    {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found on :: "+ deviceId));

        User user = userRepository.findById(clientDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Device not found on :: "+ deviceId));;
        device.setUser(user);

        deviceRepository.save(device);
        return deviceId;
    }
}
