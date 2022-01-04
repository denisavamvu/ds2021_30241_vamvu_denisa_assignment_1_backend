package ro.tuc.ds2020.services;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.MonitoredValue;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.repositories.SensorRepository;
import ro.tuc.ds2020.repositories.UserRepository;
import ro.tuc.ds2020.services.abstractions.RPCService;

import java.time.LocalDate;
import java.util.*;

@Service
@AutoJsonRpcServiceImpl
@CrossOrigin
public class RPCServiceImpl implements RPCService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public List<List<Float>> getEnergyConsumptionOverLastNDays(int d, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ userId));
        Set<Device> deviceList = user.getDevices();

        List<List<Float>> result = new ArrayList<>();

        for(Device device: deviceList){
            if (device.getSensor() != null) {
                Sensor sensor = sensorRepository.findById(device.getSensor().getId()).orElseThrow(() -> new ResourceNotFoundException("sensor not found "));
                List<MonitoredValue> monitoredValues = sensor.getMonitoredValues();

                for(int daysCount = d; daysCount >0;  daysCount--)
                {
                    List<Float> consumptionInOneDay = new ArrayList<Float>(Collections.nCopies(24, 0f));

                    for(int i = 0; i < monitoredValues.size() - 1; i++) {
                        MonitoredValue m = monitoredValues.get(i);
                        if(m.getTimestamp().toLocalDate().equals(LocalDate.now().minusDays(daysCount))){
                            for(int hour = 0; hour < 24; hour++)
                            {
                                if(m.getTimestamp().toLocalTime().getHour() == hour)
                                {
                                    MonitoredValue monitoredValue = null;
                                    for(int j = i + 1; j < monitoredValues.size(); j++)
                                    {
                                        MonitoredValue m1 = monitoredValues.get(j);
                                        if(m1.getTimestamp().toLocalTime().getHour() == hour)
                                        {
                                            monitoredValue = m1;
                                            break;
                                            }
                                    }
                                    if(monitoredValue != null)
                                        consumptionInOneDay.set(hour, Math.abs(m.getEnergy_consumption() - monitoredValue.getEnergy_consumption()));
                                }
                            }
                        }
                    }
                result.add(consumptionInOneDay);
                }
            }
        }
    return result;
    }

    @Override
    public List<Float> getBaselineOfClient(UUID userId)
    {
      List<List<Float>> list = getEnergyConsumptionOverLastNDays(7, userId);

      List<Float> baseline = new ArrayList<>(24);

      for (int i = 0; i < 24; i++)
          baseline.add(0.0f);

        for(List<Float> i : list)
            for(int j=0;j<=23;j++)
                baseline.set(j, baseline.get(j) + i.get(j));

        for(int j=0;j<=23;j++)
            baseline.set(j, baseline.get(j)/7);

        return baseline;
    }

    @Override
    public TreeMap<String, Float> getProgram(UUID userId, int d){
        TreeMap<String, Float> result =  new TreeMap<String, Float>();
        List<Float> baselineValues = getBaselineOfClient(userId);

        int index = -1;
        float min = 99999;

        for(int i = 0; i < 24 - d; i++){
            float sum = 0;
            for(int j = i + 1; j < i + d; j++){
                sum += baselineValues.get(j);
            }
            if(sum < min) {
                min = sum;
                index = i;
            }
        }

        for(int i = index; i < index + d; i++)
            result.put(i + ":00", baselineValues.get(i));
        return result;
    }

}
