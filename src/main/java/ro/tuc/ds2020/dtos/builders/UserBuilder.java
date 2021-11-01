package ro.tuc.ds2020.dtos.builders;

import org.springframework.stereotype.Component;
import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.dtos.ClientDetailsDTO;
import ro.tuc.ds2020.dtos.CurrentUserDTO;
import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.entities.UserRole;

import java.time.LocalDate;

@Component
public class UserBuilder {
    private UserBuilder() {

    }

    public ClientDTO toClientDTO(User user) {
        return new ClientDTO(user.getId(), user.getName(), user.getUsername(), user.getAddress(), user.getBirthdate().toString());
    }

    public UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getRole(), user.getUsername(), user.getName(), user.getAddress(), user.getBirthdate());
    }

    public User toEntity(ClientDetailsDTO clientDetailsDTO) {
        LocalDate birthDate = LocalDate.parse(clientDetailsDTO.getBirthdate());
        return new User(UserRole.CLIENT, clientDetailsDTO.getUsername(),
                clientDetailsDTO.getPassword(), clientDetailsDTO.getName(),
                clientDetailsDTO.getAddress(), birthDate
                );
    }

    public User toEntity(ClientDTO clientDTO) {
        LocalDate birthDate = LocalDate.parse(clientDTO.getBirthdate());
        return new User(clientDTO.getId(), clientDTO.getName(),clientDTO.getUsername(),
                clientDTO.getAddress());
    }

    public CurrentUserDTO toCurrentUserDTO(User user){
        return new CurrentUserDTO(user.getId(), user.getUsername(), user.getRole());
    }
}
