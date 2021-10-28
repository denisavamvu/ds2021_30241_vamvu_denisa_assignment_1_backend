package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.dtos.ClientDetailsDTO;
import ro.tuc.ds2020.dtos.CurrentUserDTO;
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
    public ResponseEntity<List<ClientDTO>> getClients() {
        List<ClientDTO> clientDTOList = userService.findClients();
        return new ResponseEntity<>(clientDTOList, HttpStatus.OK);
    }

    @PostMapping("/addClient")
    public ResponseEntity<UUID> insertClient(@Valid @RequestBody ClientDetailsDTO clientDetailsDTO) {
        UUID clientID = userService.insertClient(clientDetailsDTO);
        return new ResponseEntity<>(clientID, HttpStatus.CREATED);
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

    @GetMapping("/userDetails/{username}")
    public ResponseEntity<CurrentUserDTO> getUserDetails(@PathVariable(value = "username") String username)
    {
        CurrentUserDTO currentUserDTO = userService.getCurrrentUserDetails(username);
        return new ResponseEntity<>(currentUserDTO, HttpStatus.OK);
    }
}
