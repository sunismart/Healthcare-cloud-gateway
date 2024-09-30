package com.ApiGateway.ApiGateway.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	final Map<String, String> otpStorage = new ConcurrentHashMap<>();

    @Autowired
    private UserRepository userRepository;
   
    
    @Autowired
    private JwtUtils jwtService;
    
    
    public User getbyemail(String email) {
    	return userRepository.findByEmail(email).get();
    }

   
}


