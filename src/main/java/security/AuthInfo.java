package security;

import domain.entity.HiaAuthenticationEntity;

/**
 * Created by ohseoklee on 2018. 11. 18..
 *
 * handle authenticate information with singleton
 */
public class AuthInfo {
    private static AuthInfo authInfo;

    private boolean isAuthenticated = false;
    private AuthRole role = null;

    private AuthInfo() {}
    private AuthInfo(AuthRole role) {
        this.role = role;
    };

    public static AuthInfo getInstance() {

        if (authInfo == null) {
            authInfo = new AuthInfo(AuthRole.ROLE_NONE);
        }

        return authInfo;
    }

    public void init(HiaAuthenticationEntity authenticationEntity) {
        if (authenticationEntity != null) {
            this.isAuthenticated = true;
            this.role = authenticationEntity.getRole();
        }
    }

    public AuthRole getRole() {
        return role;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }
}
