package com.example.demo.SecurityConfiguration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.exception.UserIsNotFoundedException;
import com.example.demo.module.User;
import com.example.demo.repository.UserRepository;


public class UserDetailService  implements UserDetailsService{

	@Autowired
	public UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional=userRepository.findByName(username);
		return userOptional.map(LocalUserDetails::new)
				.orElseThrow(()-> new UserIsNotFoundedException("user is not found " + username));  
	}
	
}
