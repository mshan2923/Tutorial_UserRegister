package com.tutorial.tutorial_userregister.Config;

import com.tutorial.tutorial_userregister.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    private final UserService userService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        System.out.println("-------------->" + id + " / " + password);
        return null;
    }

    //작동 안함

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println("------------is supports");
        return false;
    }
}
