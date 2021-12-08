package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class Sensor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    @Column(name = "id")
    private UUID id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "max_value")
    private float max_value;

    @OneToOne(mappedBy = "sensor",fetch = FetchType.EAGER)
    private Device device;

    @OneToMany(mappedBy = "sensor", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<MonitoredValue> monitoredValues;

    public Sensor() {

    }

    public Sensor(UUID id, String description, float max_value, Device device) {
        this.id = id;
        this.description = description;
        this.max_value = max_value;
        this.device = device;
    }

    public Sensor(UUID id, String description, float max_value) {
        this.id = id;
        this.description = description;
        this.max_value = max_value;
    }
    public Sensor(UUID id, String description, float max_value, Device device, List<MonitoredValue> monitoredValues) {
        this.id = id;
        this.description = description;
        this.max_value = max_value;
        this.device = device;
        this.monitoredValues = monitoredValues;
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

    public List<MonitoredValue> getMonitoredValues() {
        return monitoredValues;
    }

    public void setMonitoredValues(List<MonitoredValue> monitoredValues) {
        this.monitoredValues = monitoredValues;
    }
}
