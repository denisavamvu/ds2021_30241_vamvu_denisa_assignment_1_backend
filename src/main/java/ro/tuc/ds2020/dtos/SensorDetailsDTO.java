package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.entities.MonitoredValue;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

public class SensorDetailsDTO {

    private UUID id;
    private String description;
    private float max_value;
    private Device device;
    private Set<MonitoredValue> monitoredValues;

    public SensorDetailsDTO() {

    }

    public SensorDetailsDTO(UUID id, String description, float max_value, Device device, Set<MonitoredValue> monitoredValues) {
        this.id = id;
        this.description = description;
        this.max_value = max_value;
        this.device = device;
        this.monitoredValues = monitoredValues;
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

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Set<MonitoredValue> getMonitoredValues() {
        return monitoredValues;
    }

    public void setMonitoredValues(Set<MonitoredValue> monitoredValues) {
        this.monitoredValues = monitoredValues;
    }
}
