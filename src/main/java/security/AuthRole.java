package security;

/**
 * Created by ohseoklee on 2018. 11. 18..
 *
 */
public enum AuthRole {
    ROLE_NONE("none"),
    ROLE_STUDENT("student"), ROLE_ADMIN("admin"), ROLE_PROFESSOR("professor"), ROLE_DEVICE("device");

    private String role;

    AuthRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
