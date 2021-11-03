package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping()
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/clients")
    public  ResponseEntity<List<ClientDTO>> getClients() {
        List<ClientDTO> clientDTOList = userService.findClients();
        return new ResponseEntity<>(clientDTOList, HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> clientDTOList = userService.findUsers();
        return new ResponseEntity<>(clientDTOList, HttpStatus.OK);
    }

    @PostMapping("/addClient")
    public ResponseEntity<UUID> insertClient(@Valid @RequestBody ClientDetailsDTO clientDetailsDTO) {
        UUID clientID = userService.insertClient(clientDetailsDTO);
        return new ResponseEntity<>(clientID, HttpStatus.CREATED);
    }
    @PostMapping("/addAdmin")
    public ResponseEntity<UUID> insertAdmin(@Valid @RequestBody ClientDetailsDTO clientDetailsDTO) {
        UUID adminID = userService.insertAdmin(clientDetailsDTO);
        return new ResponseEntity<>(adminID, HttpStatus.CREATED);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<UUID> updateClient(@PathVariable(value = "id") UUID clientId,@RequestBody ClientDTO newClientDTO) {
        UUID clientID = userService.updateClient(clientId, newClientDTO);
        return new ResponseEntity<>(clientID, HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<UUID> deleteClient(@PathVariable(value = "id") UUID clientId) {
        UUID deletedClientId = userService.deleteClient(clientId);
        return new ResponseEntity<>(deletedClientId, HttpStatus.OK);
    }

    @GetMapping("/getClient/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable(value = "id") UUID id) {
        ClientDTO clientDTO= userService.findClientById(id);
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }

    @GetMapping("/getDevices/{id}")
    public ResponseEntity<List<DeviceDetailsDTO>> getDevices(@PathVariable(value = "id") UUID id){
        List<DeviceDetailsDTO> deviceDetailsDTOList = userService.getDevices(id);
        return new ResponseEntity<>(deviceDetailsDTOList, HttpStatus.OK);
    }
}
