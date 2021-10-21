package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.SensorDetailsDTO;
import ro.tuc.ds2020.entities.Sensor;

public class SensorBuilder {
    private SensorBuilder() {

    }

    public static SensorDetailsDTO toSensorDetailsDTO(Sensor sensor) {
        return new SensorDetailsDTO(sensor.getId(), sensor.getDescription(), sensor.getMax_value());
    }

    public static Sensor toEntity(SensorDetailsDTO sensorDetailsDTO) {
        return new Sensor(sensorDetailsDTO.getId(),
                sensorDetailsDTO.getDescription(),
                sensorDetailsDTO.getMax_value(),
                sensorDetailsDTO.getDevice(),
                sensorDetailsDTO.getMonitoredValues());
    }
}
