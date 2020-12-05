package com.luhavis.service;


import com.luhavis.domain.CustomUserDetails;
import com.luhavis.domain.Role;
import com.luhavis.domain.User;
import com.luhavis.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        System.out.println("------------------------------------------------------------");
        System.out.println("loadUserByUsername");
        System.out.println("------------------------------------------------------------");


        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException(userId));

        Set<GrantedAuthority> grantedAuthorites = new HashSet<>();
        grantedAuthorites.add(new SimpleGrantedAuthority(user.getRoleKey()));


        return new org.springframework.security.core.userdetails.User(user.getUserNm(), user.getUserPw(), grantedAuthorites);

    }
}
