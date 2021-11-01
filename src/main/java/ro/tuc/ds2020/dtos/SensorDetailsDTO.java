package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.MonitoredValue;

import java.util.Set;
import java.util.UUID;

public class SensorDetailsDTO {

    private UUID id;
    private String description;
    private float max_value;
    private String deviceDescription;
    private UUID deviceId;
    private Set<MonitoredValue> monitoredValues;

    public SensorDetailsDTO() {

    }

    public SensorDetailsDTO(UUID id, String description, float max_value, String device_description, UUID device_id) {
        this.id = id;
        this.description = description;
        this.max_value = max_value;
        this.deviceDescription = device_description;
        this.deviceId = device_id;
    }

    public SensorDetailsDTO(UUID id, String description, float max_value) {
        this.id = id;
        this.description = description;
        this.max_value = max_value;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMax_value() {
        return max_value;
    }

    public void setMax_value(float max_value) {
        this.max_value = max_value;
    }


    public String getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }

    public Set<MonitoredValue> getMonitoredValues() {
        return monitoredValues;
    }

    public void setMonitoredValues(Set<MonitoredValue> monitoredValues) {
        this.monitoredValues = monitoredValues;
    }
}
