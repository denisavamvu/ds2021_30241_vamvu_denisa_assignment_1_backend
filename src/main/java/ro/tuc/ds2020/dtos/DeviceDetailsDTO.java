package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.User;

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
    private User user;

    public DeviceDetailsDTO() {

    }

    public DeviceDetailsDTO(UUID id, String description, String address, float max_consumption, float average_consumption, User user) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.max_consumption = max_consumption;
        this.average_consumption = average_consumption;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
