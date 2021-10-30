package ro.tuc.ds2020.entities;

public enum UserRole {
    CLIENT ("Client"),
    ADMIN ("Admin");

    private final String role;

    UserRole(String s) {
        role = s;
    }

    public String getRole() {
        return role;
    }
    public boolean equalsRole(String otherRole) {
        return role.equals(otherRole);
    }

    public String toString() {
        return this.role;
    }
}
