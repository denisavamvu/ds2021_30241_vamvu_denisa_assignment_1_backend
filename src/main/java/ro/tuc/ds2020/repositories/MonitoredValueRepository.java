package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.MonitoredValue;

import java.util.UUID;

public interface MonitoredValueRepository extends JpaRepository<MonitoredValue, UUID> {
}
