package ro.tuc.ds2020.entities;

import java.io.Serializable;
import java.util.UUID;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
    private final String username;
    private UserRole role;
    private UUID id;
    public JwtResponse(String jwtToken, UUID id, String username, UserRole role) {
        this.jwtToken = jwtToken;
        this.id = id;
        this.username = username;
        this.role = role;
    }
    public String getUsername() {
        return username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getToken() {
        return this.jwtToken;
    }
}