package ro.tuc.ds2020.dtos;


import java.util.Objects;
import java.util.UUID;

public class ClientDTO {
    private UUID id;
    private String name;
    private String username;
    private String address;
    private String birthdate;

    public ClientDTO(){

    }

    public ClientDTO(String name, String address,String username, String birthdate) {
        this.name = name;
        this.username = username;
        this.address = address;
        this.birthdate = birthdate;
    }

    public ClientDTO(UUID id,String name,String username, String address, String birthdate) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.address = address;
        this.birthdate = birthdate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDTO that = (ClientDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(address, that.address) &&
                Objects.equals(birthdate,that.birthdate);
        //birthdate.equals(that.birthdate);
    }

    @Override
    public int hashCode() { return Objects.hash(name, address, birthdate); }
}
