package com.erbf.bugsLife.config;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService {

    @Transactional
    public UserDetails loadUserById(Long id, String role) {


        return UserPrincipal.create(id, role);
    }


}