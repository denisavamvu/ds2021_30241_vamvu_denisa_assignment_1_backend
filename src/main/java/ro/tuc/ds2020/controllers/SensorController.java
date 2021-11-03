package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.SensorDetailsDTO;
import ro.tuc.ds2020.services.SensorService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/sensor")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) { this.sensorService = sensorService; }

    @Transactional
    @GetMapping()
    public ResponseEntity<List<SensorDetailsDTO>> getSensors() {
        List<SensorDetailsDTO> sensorDetailsDTOList = sensorService.findSensors();
        return new ResponseEntity<>(sensorDetailsDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UUID> insertSensor(@Valid @RequestBody SensorDetailsDTO sensorDetailsDTO)
    {
        UUID sensorID = sensorService.insert(sensorDetailsDTO);
        return new ResponseEntity<>(sensorID, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateSensor(@PathVariable(value = "id") UUID id, @RequestBody SensorDetailsDTO sensorDetailsDTO) {
        UUID sensorId = sensorService.updateSensor(id, sensorDetailsDTO);
        return new ResponseEntity<>(sensorId, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteSensor(@PathVariable(value = "id") UUID id) {
        UUID deletedSensorId = sensorService.deleteSensor(id);
        return new ResponseEntity<>(deletedSensorId, HttpStatus.OK);
    }

}
