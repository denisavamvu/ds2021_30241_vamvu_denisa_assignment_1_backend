package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.MonitoredValueDTO;
import ro.tuc.ds2020.services.MonitoredValueService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@CrossOrigin
@RequestMapping
public class MonitoredValueController {
    @Autowired
    private MonitoredValueService monitoredValueService;

    @GetMapping("/monitoredValues")
    public ResponseEntity<List<MonitoredValueDTO>> getMonitoredValues() {
        List<MonitoredValueDTO> monitoredValueDTOList = monitoredValueService.getMonitoredValues();
        return new ResponseEntity<>(monitoredValueDTOList, HttpStatus.OK);
    }

    @PostMapping("/addMonitoredValue")
    public ResponseEntity<UUID> insertMonitoredValue(@Valid @RequestBody MonitoredValueDTO monitoredValueDTO){
        UUID monitoredValueId = monitoredValueService.insertMonitoredValue(monitoredValueDTO);
        return new ResponseEntity<>(monitoredValueId, HttpStatus.CREATED);
    }

    @GetMapping("getHistoricalMonitoredData/{id}/{date}")
    public ResponseEntity<Map<Integer, Float>> getHistoricalMonitoredData(@PathVariable(value = "id") UUID id, @PathVariable(value = "date") String date){
        Map map = monitoredValueService.getHistoricalMonitoredData(id, date);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/getMonitoredValues/{id}")
    public ResponseEntity<List<MonitoredValueDTO>> getMonitoredValuesOfUser(@PathVariable(value = "id") UUID id){
        List<MonitoredValueDTO> monitoredValueDTOList = monitoredValueService.getMonitoredValuesOfUser(id);
        return new ResponseEntity<>(monitoredValueDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/deleteMonitoredValues")
    public void deleteAllMonitoredValues(){
        monitoredValueService.deleteAll();
    }

}
