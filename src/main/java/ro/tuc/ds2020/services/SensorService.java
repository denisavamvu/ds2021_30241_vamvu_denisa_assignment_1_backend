package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.SensorDetailsDTO;
import ro.tuc.ds2020.dtos.builders.SensorBuilder;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.repositories.SensorRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SensorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SensorService.class);
    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) { this.sensorRepository = sensorRepository; }

    public List<SensorDetailsDTO> findSensors() {
        List<Sensor> sensorList = sensorRepository.findAll();
        return sensorList.stream()
                .map(SensorBuilder::toSensorDetailsDTO)
                .collect(Collectors.toList());
    }

    public UUID insert(SensorDetailsDTO sensorDetailsDTO) {
        Sensor sensor = SensorBuilder.toEntity(sensorDetailsDTO);
        sensor = sensorRepository.save(sensor);
        LOGGER.debug("Sensor with id {} was inserted in db", sensor.getId());
        return sensor.getId();
    }

    public UUID updateSensor(UUID id, SensorDetailsDTO sensorDetailsDTO) throws ResourceNotFoundException{
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found on :: "+ id));

        if(sensorDetailsDTO.getDescription() != null)
            sensor.setDescription(sensorDetailsDTO.getDescription());

        if(sensorDetailsDTO.getMax_value() != 0)
            sensor.setMax_value(sensorDetailsDTO.getMax_value());

        sensorRepository.save(sensor);
        return sensor.getId();
    }

    public UUID deleteSensor(UUID sensorID) throws ResourceNotFoundException {
        Sensor sensor = sensorRepository.findById(sensorID)
                .orElseThrow(() -> new ResourceNotFoundException("Sensor not found on :: "+ sensorID));
        sensorRepository.delete(sensor);
        return sensor.getId();
    }
}
