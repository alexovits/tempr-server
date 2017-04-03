package edu.endava.tempr.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by zsoltszabo on 4/3/17.
 */
public enum UserType implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
