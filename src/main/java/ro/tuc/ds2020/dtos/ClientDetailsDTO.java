package ro.tuc.ds2020.dtos;
import javax.validation.constraints.NotNull;


public class ClientDetailsDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String birthdate;

    public ClientDetailsDTO() {

    }

    public ClientDetailsDTO( String username, String password, String name, String address, String birthdate) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

}
