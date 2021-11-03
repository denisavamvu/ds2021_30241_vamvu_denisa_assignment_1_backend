package ro.tuc.ds2020.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class MonitoredValue implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "energy_consumption", nullable = false)
    private float energy_consumption;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    public MonitoredValue() {

    }

    public MonitoredValue(UUID id, LocalDateTime timestamp, float energy_consumption, Sensor sensor) {
        this.id = id;
        this.timestamp = timestamp;
        this.energy_consumption = energy_consumption;
        this.sensor = sensor;
    }

    public MonitoredValue(UUID id, LocalDateTime timestamp, float energy_consumption) {
        this.id = id;
        this.timestamp = timestamp;
        this.energy_consumption = energy_consumption;
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

    public float getEnergy_consumption() {
        return energy_consumption;
    }

    public void setEnergy_consumption(float energy_consumption) {
        this.energy_consumption = energy_consumption;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
