package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.User;
import ro.tuc.ds2020.entities.UserRole;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

public class ClientDetailsDTO {
    private UUID id;
    private static final UserRole role = UserRole.CLIENT;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private Date birthdate;

    public ClientDetailsDTO() {

    }

    public ClientDetailsDTO(UUID id, String username, String password, String name, String address, Date birthdate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
    }

    public ClientDetailsDTO(String username, String password, String name, String address, Date birthdate) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public static UserRole getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
