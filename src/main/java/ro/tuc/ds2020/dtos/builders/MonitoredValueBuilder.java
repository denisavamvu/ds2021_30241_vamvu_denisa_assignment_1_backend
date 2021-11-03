package ro.tuc.ds2020.dtos.builders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MonitoredValueDTO;
import ro.tuc.ds2020.entities.MonitoredValue;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.repositories.SensorRepository;

@Component
public class MonitoredValueBuilder {

    @Autowired
    private SensorRepository sensorRepository;

    public MonitoredValue toEntity(MonitoredValueDTO monitoredValueDTO){
        if(monitoredValueDTO.getSensorId()!=null){
            Sensor sensor = sensorRepository.findById(monitoredValueDTO.getSensorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Sensor not found on :: "+ monitoredValueDTO.getSensorId()));
            return new MonitoredValue(monitoredValueDTO.getId(), monitoredValueDTO.getTimestamp(), monitoredValueDTO.getEnergyConsumption(), sensor);
        }
        return new MonitoredValue(monitoredValueDTO.getId(), monitoredValueDTO.getTimestamp(), monitoredValueDTO.getEnergyConsumption());
    }

    public MonitoredValueDTO toMonitoredValueDTO(MonitoredValue monitoredValue){
        if(monitoredValue.getSensor()!=null){
            return new MonitoredValueDTO(monitoredValue.getId(), monitoredValue.getTimestamp(),
                    monitoredValue.getEnergy_consumption(), monitoredValue.getSensor().getId());
        }
        return new MonitoredValueDTO(monitoredValue.getId(), monitoredValue.getTimestamp(),
                monitoredValue.getEnergy_consumption());
    }
}
