package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.SensorDetailsDTO;
import ro.tuc.ds2020.dtos.builders.SensorBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.repositories.DeviceRepository;
import ro.tuc.ds2020.repositories.SensorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SensorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensorService.class);
    private final SensorRepository sensorRepository;
    @Autowired
    private SensorBuilder sensorBuilder;

    @Autowired
    private DeviceRepository deviceRepository;

    public SensorService(SensorRepository sensorRepository) { this.sensorRepository = sensorRepository;
    }

    public List<SensorDetailsDTO> findSensors() {
        List<Sensor> sensorList = sensorRepository.findAll();
        return sensorList.stream()
                .map(sensor-> sensorBuilder.toSensorDetailsDTO(sensor))
                .collect(Collectors.toList());
    }

    public UUID insert(SensorDetailsDTO sensorDetailsDTO) {
        Sensor sensor = sensorBuilder.toEntity(sensorDetailsDTO);
        sensor = sensorRepository.save(sensor);
        LOGGER.debug("Sensor with id {} was inserted in db", sensor.getId());
        return sensor.getId();
    }

    public UUID updateSensor(UUID id, SensorDetailsDTO sensorDetailsDTO) throws ResourceNotFoundException{
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found on :: "+ id));

        if(sensorDetailsDTO.getDescription() != "")
            sensor.setDescription(sensorDetailsDTO.getDescription());

        if(sensorDetailsDTO.getMax_value() != 0)
            sensor.setMax_value(sensorDetailsDTO.getMax_value());

        sensorRepository.save(sensor);
        return sensor.getId();
    }

    public UUID deleteSensor(UUID sensorID) throws ResourceNotFoundException {
        Sensor sensor = sensorRepository.findById(sensorID)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found on :: "+ sensorID));

        if(sensor.getDevice()!=null){
            Optional<Device> optionalDevice = deviceRepository.findById(sensor.getDevice().getId());
            if(optionalDevice.isPresent()){
                Device device = optionalDevice.get();
                device.setSensor(null);
                deviceRepository.save(device);
            }
        }

        sensorRepository.delete(sensor);
        return sensor.getId();
    }
}
