package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.dtos.ClientDetailsDTO;
import ro.tuc.ds2020.dtos.CurrentUserDTO;
import ro.tuc.ds2020.dtos.UserDTO;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.entities.UserRole;

import java.time.LocalDate;

public class UserBuilder {
    private UserBuilder() {

    }

    public static ClientDTO toClientDTO(User user) {
        return new ClientDTO(user.getId(), user.getName(), user.getUsername(), user.getAddress(), user.getBirthdate().toString());
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getRole(), user.getUsername(), user.getName(), user.getAddress(), user.getBirthdate());
    }

    public static User toEntity(ClientDetailsDTO clientDetailsDTO) {
        System.out.println(clientDetailsDTO.getAddress());
        System.out.println(clientDetailsDTO.getBirthdate());
        LocalDate birthDate = LocalDate.parse(clientDetailsDTO.getBirthdate());
        return new User(UserRole.CLIENT, clientDetailsDTO.getUsername(),
                clientDetailsDTO.getPassword(), clientDetailsDTO.getName(),
                clientDetailsDTO.getAddress(), birthDate
                );
    }

    public static CurrentUserDTO toCurrentUserDTO(User user){
        return new CurrentUserDTO(user.getId(), user.getUsername(), user.getRole());
    }
}
