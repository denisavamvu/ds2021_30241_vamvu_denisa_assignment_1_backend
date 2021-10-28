package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.ClientDTO;
import ro.tuc.ds2020.dtos.ClientDetailsDTO;
import ro.tuc.ds2020.dtos.CurrentUserDTO;
import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.entities.UserRole;

public class UserBuilder {
    private UserBuilder() {

    }

    public static ClientDTO toClientDTO(User user) {
        return new ClientDTO(user.getId(), user.getName(), user.getAddress(), user.getBirthdate());
    }

    public static User toEntity(ClientDetailsDTO clientDetailsDTO) {
        return new User(clientDetailsDTO.getId(), clientDetailsDTO.getRole() ,clientDetailsDTO.getUsername(),
                clientDetailsDTO.getPassword(), clientDetailsDTO.getName(),
                clientDetailsDTO.getAddress(), clientDetailsDTO.getBirthdate()
                );
    }

    public static CurrentUserDTO toCurrentUserDTO(User user){
        return new CurrentUserDTO(user.getId(), user.getUsername(), user.getRole());
    }
}
