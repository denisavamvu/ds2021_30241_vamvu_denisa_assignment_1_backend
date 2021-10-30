package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.UserRole;

import java.util.Date;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    private UserRole role;
    private String username;
    private String name;
    private String address;
    private Date birthdate;

    public UserDTO() {

    }
    public UserDTO(UUID id, UserRole role, String username, String name, String address, Date birthdate) {
        this.id = id;
        this.role = role;
        this.username = username;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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