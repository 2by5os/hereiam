package request;

import security.AuthRole;

/**
 * Created by ohseoklee on 2018-11-23.
 *
 */
public class AuthenticationRequest {
    private String id;
    private String password;
    private AuthRole role;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String id, String password, AuthRole role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthRole getRole() {
        return role;
    }

    public void setRole(AuthRole role) {
        this.role = role;
    }
}
