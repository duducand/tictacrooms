package es.duducand.tic.tac.rooms.persistance.dao.security.impl;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider  implements AuthenticationProvider{

    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        return null;
    }

    public boolean supports(Class<? extends Object> authentication) {
        return true;
    }

}
