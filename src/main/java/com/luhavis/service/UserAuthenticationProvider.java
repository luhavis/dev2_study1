package com.luhavis.service;

import com.luhavis.domain.User;
import com.luhavis.domain.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@RequiredArgsConstructor
@Configuration
public class UserAuthenticationProvider implements AuthenticationProvider {
    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = userRepository.findByUserId(name)
                .orElseThrow(() -> new BadCredentialsException("Login Error"));

        Set<GrantedAuthority> grantedAuthorites = new HashSet<>();
        grantedAuthorites.add(new SimpleGrantedAuthority(user.getRoleKey()));

        return new UsernamePasswordAuthenticationToken(user, null, grantedAuthorites);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
