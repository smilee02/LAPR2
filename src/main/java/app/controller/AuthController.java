package app.controller;

import auth.domain.model.UserRole;
import auth.mappers.dto.UserRoleDTO;

import java.util.Collections;
import java.util.List;

public class AuthController {

    private final App app;

    public AuthController() {
        this.app = App.getInstance();
    }

    public boolean doLogin(String email, String pwd) {
        try {
            return this.app.doLogin(email, pwd);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public List<UserRoleDTO> getUserRoles() {
        if (this.app.getCurrentUserSession().isLoggedIn()) {
            return this.app.getCurrentUserSession().getUserRoles();
        }
        return Collections.emptyList(); // testing method
    }

    public void doLogout() {
        this.app.doLogout();
    }
}
