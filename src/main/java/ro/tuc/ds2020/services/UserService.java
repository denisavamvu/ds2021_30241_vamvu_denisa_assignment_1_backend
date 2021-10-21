package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.dtos.ClientDetailsDTO;
import ro.tuc.ds2020.dtos.builders.UserBuilder;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.entities.UserRole;
import ro.tuc.ds2020.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) { this.userRepository = userRepository;}

    public List<ClientDTO> findClients() {
        List<User> clientList = userRepository.findByRole(UserRole.CLIENT);
        return clientList.stream()
                .map(UserBuilder::toClientDTO)
                .collect(Collectors.toList());
    }

    public UUID insertClient(ClientDetailsDTO clientDetailsDTO) {
        User user = UserBuilder.toEntity(clientDetailsDTO);
        user = userRepository.save(user);
        LOGGER.debug("Person with id {} was inserted in db", user.getId());
        return user.getId();
    }

    public UUID updateClient(UUID id, ClientDTO clientDTO) throws ResourceNotFoundException{
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ id));

        if(clientDTO.getAddress()!=null)
            user.setAddress(clientDTO.getAddress());

        if(clientDTO.getName()!=null)
            user.setName(clientDTO.getName());

        if(clientDTO.getBirthdate()!=null)
            user.setBirthdate(clientDTO.getBirthdate());


        userRepository.save(user);
        return user.getId();
    }

    public UUID deleteClient(UUID clientId) throws ResourceNotFoundException{
        User user = userRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ clientId));
        userRepository.delete(user);
        return user.getId();
    }
}
