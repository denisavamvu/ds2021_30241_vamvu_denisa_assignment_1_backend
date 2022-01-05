package ro.tuc.ds2020.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MonitoredValueDTO;
import ro.tuc.ds2020.dtos.builders.MonitoredValueBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.MonitoredValue;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.repositories.MonitoredValueRepository;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.tuc.ds2020.repositories.SensorRepository;
import ro.tuc.ds2020.repositories.UserRepository;

@Service
public class MonitoredValueService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private MonitoredValueRepository monitoredValueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private MonitoredValueBuilder monitoredValueBuilder;

    public UUID insertMonitoredValue(MonitoredValueDTO monitoredValueDTO){
        MonitoredValue monitoredValue = monitoredValueBuilder.toEntity(monitoredValueDTO);
        monitoredValue = monitoredValueRepository.save(monitoredValue);
        LOGGER.debug("monitoredValue with id {} was inserted in db", monitoredValue.getId());
        return monitoredValue.getId();
    }

    public List<MonitoredValueDTO> getMonitoredValues() {
        return monitoredValueRepository.findAll().stream().map(monitoredValue -> monitoredValueBuilder.toMonitoredValueDTO(monitoredValue))
                .collect(Collectors.toList());
    }
    public Map<Integer, Float> getHistoricalMonitoredData(UUID userId, String data){
        Map<Integer, Float> map = new HashMap<Integer, Float>();

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ userId));
        if(user.getDevices()!=null) {
            for (Device device : user.getDevices()) {
                if (device.getSensor() != null) {
                    Sensor sensor = sensorRepository.findById(device.getSensor().getId()).orElseThrow(() -> new ResourceNotFoundException("sensor not found "));
                    List<MonitoredValue> monitoredValues = sensor.getMonitoredValues();
                    for (MonitoredValue monitoredValue : monitoredValues) {
                        String convertedDate = monitoredValue.getTimestamp().format(formatter);
                        String day = convertedDate.substring(0, 10);
                        int hour = Integer.parseInt(convertedDate.substring(11, 13));
                        Integer hourAux = Integer.valueOf(hour);
                        if (day.equals(data)) {
                            if (map.containsKey(hourAux))
                                map.put(hourAux, map.get(hourAux) + monitoredValue.getEnergy_consumption());
                            else
                                map.put(hourAux, monitoredValue.getEnergy_consumption());

                        }
                    }
                }


            }
        }
        return map;
    }

    public List<MonitoredValueDTO> getMonitoredValuesOfUser(UUID id) {
        List<MonitoredValueDTO> monitoredValueDTOList = new ArrayList<>();
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + id));
        if (user.getDevices() != null) {
            for (Device device : user.getDevices()) {
                if (device.getSensor() != null) {
                    Sensor sensor = sensorRepository.findById(device.getSensor().getId()).orElseThrow(() -> new ResourceNotFoundException("sensor not found "));
                    List<MonitoredValue> monitoredValues = sensor.getMonitoredValues();
                    for (MonitoredValue monitoredValue : monitoredValues) {
                        MonitoredValueDTO monitoredValueDTO = monitoredValueBuilder.toMonitoredValueDTO(monitoredValue);
                        monitoredValueDTOList.add(monitoredValueDTO);
                    }
                }
            }
        }
        return monitoredValueDTOList;
    }

    public void deleteAll(){
        monitoredValueRepository.deleteAll();
    }

}
