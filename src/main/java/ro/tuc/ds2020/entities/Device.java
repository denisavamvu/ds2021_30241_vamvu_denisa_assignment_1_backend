package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Device implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    @Column(name = "id")
    private UUID id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "max_consumption", nullable = false)
    private float max_consumption;

    @Column(name = "average_consumption", nullable = false)
    private float average_consumption;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id", unique = true)
    private Sensor sensor;

    public Device() {

    }

    public Device(UUID id, String description, String address, float max_consumption, float average_consumption, User user) {
        this.id = id;
        this.description = description;
        this.address = address;
        this.max_consumption = max_consumption;
        this.average_consumption = average_consumption;
        this.user = user;
    }

    public Device(UUID id, String description,String address, float max_consumption, float average_consumption) {
        this.id = id;
        this.description = description;
        this.address =address;
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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
