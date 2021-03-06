package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.dtos.DeviceDetailsDTO;
import ro.tuc.ds2020.dtos.SensorDetailsDTO;
import ro.tuc.ds2020.services.DeviceService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) { this.deviceService = deviceService; }

    @GetMapping
    public ResponseEntity<List<DeviceDetailsDTO>> getDevices() {
        List<DeviceDetailsDTO> deviceDetailsDTOList = deviceService.findDevices();
        return new ResponseEntity<>(deviceDetailsDTOList, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<UUID> insertDevice(@Valid @RequestBody DeviceDetailsDTO deviceDetailsDTO) {
        UUID deviceID = deviceService.insert(deviceDetailsDTO);
        return new ResponseEntity<>(deviceID, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UUID> updateDevice(@PathVariable(value = "id") UUID id, @RequestBody DeviceDetailsDTO deviceDetailsDTO) {
        UUID sensorId = deviceService.updateDevice(id, deviceDetailsDTO);
        return new ResponseEntity<>(sensorId, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteDevice(@PathVariable(value = "id") UUID id) {
        UUID deletedDeviceId = deviceService.deleteDevice(id);
        return new ResponseEntity<>(deletedDeviceId, HttpStatus.OK);
    }

    @GetMapping("/noSensor")
    public ResponseEntity<List<DeviceDetailsDTO>> getDevicesWithoutSensors() {
        List<DeviceDetailsDTO> deviceDetailsDTOList = deviceService.findDevicesWithoutSensors();
        return new ResponseEntity<>(deviceDetailsDTOList, HttpStatus.OK);
    }

    @PutMapping("/addSensor/{deviceId}")
    public ResponseEntity<UUID> addSensorToDevice(@PathVariable(value = "deviceId") UUID deviceId, @RequestBody  SensorDetailsDTO sensorDetailsDTO) {
        UUID updatedDeviceId = deviceService.addSensorToDevice(deviceId, sensorDetailsDTO);
        return new ResponseEntity<>(updatedDeviceId, HttpStatus.OK);
    }

    @PutMapping("/addUser/{deviceId}")
    public ResponseEntity<UUID> addUserToDevice(@PathVariable(value = "deviceId") UUID deviceId, @RequestBody ClientDTO clientDTO) {
        UUID updatedDeviceId = deviceService.addUserToDevice(deviceId, clientDTO);
        return new ResponseEntity<>(updatedDeviceId, HttpStatus.OK);
    }

}
