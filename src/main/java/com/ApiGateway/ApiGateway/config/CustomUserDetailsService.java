package com.ApiGateway.ApiGateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;



@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

  
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	User user = userService.getbyemail(email);
        if (user != null) {
            return new CustomUserDetails(user);
        }

        

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
