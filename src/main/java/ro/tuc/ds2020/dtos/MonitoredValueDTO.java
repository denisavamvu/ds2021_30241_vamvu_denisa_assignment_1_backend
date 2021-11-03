package ro.tuc.ds2020.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class MonitoredValueDTO {
    private UUID id;
    private LocalDateTime timestamp;
    private float energyConsumption;
    private UUID sensorId;

    public MonitoredValueDTO() {

    }

    public MonitoredValueDTO(UUID id, LocalDateTime timestamp, float energyConsumption, UUID sensorId) {
        this.id = id;
        this.timestamp = timestamp;
        this.energyConsumption = energyConsumption;
        this.sensorId = sensorId;
    }
    public MonitoredValueDTO(UUID id, LocalDateTime timestamp, float energyConsumption) {
        this.id = id;
        this.timestamp = timestamp;
        this.energyConsumption = energyConsumption;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public float getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(float energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public UUID getSensorId() {
        return sensorId;
    }

    public void setSensorId(UUID sensorId) {
        this.sensorId = sensorId;
    }
}
