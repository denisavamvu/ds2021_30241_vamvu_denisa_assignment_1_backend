package ro.tuc.ds2020.dtos.builders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.dtos.SensorDetailsDTO;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.repositories.DeviceRepository;

import java.util.Optional;

@Component
public class SensorBuilder {

    @Autowired
    private DeviceRepository deviceRepository;

    public SensorBuilder() {

    }

    public SensorDetailsDTO toSensorDetailsDTO(Sensor sensor) {
        if(sensor.getDevice()!=null)
            return new SensorDetailsDTO(sensor.getId(), sensor.getDescription(), sensor.getMax_value(),
                sensor.getDevice().getDescription(), sensor.getDevice().getId());
        return new SensorDetailsDTO(sensor.getId(), sensor.getDescription(), sensor.getMax_value());
    }

    public Sensor toEntity(SensorDetailsDTO sensorDetailsDTO) {
        if(sensorDetailsDTO.getDeviceId()!=null)
        {
            Optional<Device> optionalDevice = deviceRepository.findById(sensorDetailsDTO.getDeviceId());
            if(optionalDevice.isPresent())
                return new Sensor(sensorDetailsDTO.getId(),
                        sensorDetailsDTO.getDescription(),
                        sensorDetailsDTO.getMax_value(),
                        optionalDevice.get());
        }

        return new Sensor(sensorDetailsDTO.getId(),
                sensorDetailsDTO.getDescription(),
                sensorDetailsDTO.getMax_value());
    }
}
