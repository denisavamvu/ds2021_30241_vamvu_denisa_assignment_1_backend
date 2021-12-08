package ro.tuc.ds2020.consumer;

import org.json.simple.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import ro.tuc.ds2020.controllers.WebSocketNotification;
import ro.tuc.ds2020.dtos.MonitoredValueDTO;
import ro.tuc.ds2020.dtos.SensorDetailsDTO;
import ro.tuc.ds2020.dtos.builders.SensorBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.MonitoredValue;
import ro.tuc.ds2020.entities.Sensor;
import ro.tuc.ds2020.services.DeviceService;
import ro.tuc.ds2020.services.MonitoredValueService;
import ro.tuc.ds2020.services.SensorService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.UUID;

@Component
public class Consumer {

    private long lastTimestamp = LocalDateTime.now().atZone(ZoneId.of("Europe/Bucharest")).toEpochSecond();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private UUID lastSensorId = null;
    private double lastMeasurementValue = 0;
    private MonitoredValueService monitoredValueService;
    private SensorService sensorService;
    private DeviceService deviceService;
    @Autowired
    private SensorBuilder sensorBuilder;

    @Autowired
    public Consumer(MonitoredValueService monitoredValueService, DeviceService deviceService, SensorService sensorService){
        this.monitoredValueService = monitoredValueService;
        this.deviceService= deviceService;
        this.sensorService = sensorService;
    }

    @RabbitListener(queues = "queue")
    public void handleMessage(byte[] message) {
        JSONObject jsonObject = (JSONObject) SerializationUtils.deserialize(message);

        LocalDateTime timestamp = (LocalDateTime)jsonObject.get("timestamp");
        float measurement = (float) jsonObject.get("measurement_value");
        UUID id = (UUID) jsonObject.get("sensor_id");

        Sensor sensor = sensorService.getSensor(id);

        monitoredValueService.insertMonitoredValue(new MonitoredValueDTO(timestamp, measurement, sensor.getId()));

        SensorDetailsDTO sensorDetailsDTO = sensorBuilder.toSensorDetailsDTO(sensor);
        Device device = deviceService.getDevice(sensorDetailsDTO.getDeviceId());
        UUID userId = device.getUser().getId();

        System.out.println("User " + userId);

        long time =  timestamp.atZone(ZoneId.of("Europe/Bucharest")).toEpochSecond();
        System.out.println(lastSensorId + " " + id);
        System.out.println(measurement + "     " + lastMeasurementValue);
        if(!lastSensorId.equals(id))
        {
            MonitoredValue lastMonitoredValue = new MonitoredValue();
            Set<MonitoredValue> monitoredValues = sensor.getMonitoredValues();
            String str = "2016-01-01 11:30";
            LocalDateTime maxTimeStamp = LocalDateTime.parse(str, formatter);

            for(MonitoredValue m : monitoredValues)
                if(m.getTimestamp().isAfter(maxTimeStamp)) {
                    lastMonitoredValue = m;
                    maxTimeStamp = m.getTimestamp();
                }

            measurement = lastMonitoredValue.getEnergy_consumption();
            time = lastMonitoredValue.getTimestamp().atZone(ZoneId.of("Europe/Bucharest")).toEpochSecond();
        }
        double peak = (measurement - lastMeasurementValue)/(time-lastTimestamp);
        System.out.println(measurement + "     " + lastMeasurementValue);
        System.out.println("peak "+ peak*200);
        if(peak * 200 >= sensor.getMax_value()){

            try {
                WebSocketNotification.sendMsg(userId.toString(),"Your sensor with id "+ id
                        +" has exceeded the maximum value!");
            }catch (Exception e){

            }
        }

        lastMeasurementValue = measurement;
        lastTimestamp = time;
        lastSensorId = id;
    }
}