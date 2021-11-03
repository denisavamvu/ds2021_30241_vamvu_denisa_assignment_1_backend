package ro.tuc.ds2020.dtos;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class DeviceDetailsDTO {
    private UUID id;
    @NotNull
    private String description;
    private String address;
    @NotNull
    private float max_consumption;
    @NotNull
    private float average_consumption;
    private UUID userId;
    private String username;
    private SensorDetailsDTO sensor;

    public DeviceDetailsDTO() {

    }

    public DeviceDetailsDTO(UUID id, String description, String address, float max_consumption, float average_consumption) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.max_consumption = max_consumption;
        this.average_consumption = average_consumption;
    }

    public DeviceDetailsDTO(UUID id, String description, String address, float max_consumption, float average_consumption, UUID userId, String username, SensorDetailsDTO sensor) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.max_consumption = max_consumption;
        this.average_consumption = average_consumption;
        this.userId = userId;
        this.username = username;
        this.sensor = sensor;
    }

    public DeviceDetailsDTO(UUID id, String description, float max_consumption, float average_consumption) {
        this.id = id;
        this.description = description;
        this.max_consumption = max_consumption;
        this.average_consumption = average_consumption;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getMax_consumption() {
        return max_consumption;
    }

    public void setMax_consumption(float max_consumption) {
        this.max_consumption = max_consumption;
    }

    public float getAverage_consumption() {
        return average_consumption;
    }

    public void setAverage_consumption(float average_consumption) {
        this.average_consumption = average_consumption;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SensorDetailsDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDetailsDTO sensor) {
        this.sensor = sensor;
    }
}
