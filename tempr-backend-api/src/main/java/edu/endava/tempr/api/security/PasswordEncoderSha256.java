package edu.endava.tempr.api.security;


import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by zsoltszabo on 19/12/2016.
 */
public class PasswordEncoderSha256 implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return null;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
