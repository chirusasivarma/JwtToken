package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.SecurityConfiguration.JwtService;
import com.example.demo.SecurityConfiguration.LocalUserDetails;
import com.example.demo.exception.UserIsNotFoundedException;
import com.example.demo.helper.EncryptionService;
import com.example.demo.module.LoginDto;
import com.example.demo.module.Registration;
import com.example.demo.module.Role;
import com.example.demo.module.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public EncryptionService encryptionService;
	
	@Autowired
	public JwtService jwtService;
	
	
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> getAllUser(){
		List<User> users=userRepository.findAll();
		return users;
	}
	
	public User adminRegistration(Registration registration) {
		Optional<User> userOptional=userRepository.findByMobileNumber(registration.getMobileNumber());
		if (!userOptional.isPresent()) {
			User user=new User();
			user.setName(registration.getName());
			user.setEmail(registration.getEmail());
			user.setMobileNumber(registration.getMobileNumber());
			user.setPassword(encryptionService.encryptPassword(registration.getPassword()));
			user.setRole(Role.ADMIN);
			return userRepository.save(user);	
		}else {
			throw new UserIsNotFoundedException("User Already exist");
		}
	}

	public User userRegistration(Registration registration) {
		Optional<User> userOptional=userRepository.findByMobileNumber(registration.getMobileNumber());
		if (!userOptional.isPresent()) {
			User user=new User();
			user.setName(registration.getName());
			user.setEmail(registration.getEmail());
			user.setMobileNumber(registration.getMobileNumber());
			user.setPassword(encryptionService.encryptPassword(registration.getPassword()));
			user.setRole(Role.USER);
			return userRepository.save(user);	
		}else {
			throw new UserIsNotFoundedException("User Already exist");
		}
	}
	
	public LoginDto userLogin(String password,Long mobileNumber) {
		Optional<User> userOptional=userRepository.findByMobileNumber(mobileNumber);
		if (userOptional.isPresent()) {
			User user= userOptional.get();
			if (encryptionService.verifypassword(password, user.getPassword())) {
				LocalUserDetails userDetails=new LocalUserDetails(user);
				String jwt=jwtService.generateToken(userDetails);
				LoginDto loginDto=new LoginDto();
				loginDto.setJwtToken(jwt);
				loginDto.setName(user.getName());
				loginDto.setMobileNumber(user.getMobileNumber());
				loginDto.setEmail(user.getEmail());
				return loginDto;
			}else {
				throw new UserIsNotFoundedException("invalid password");
			}
		}else {
			throw new UserIsNotFoundedException("no one registered with this mobile nuber");
		}
		
	}

	public LoginDto adminLogin(String password,Long mobileNumber) {
		Optional<User> userOptional=userRepository.findByMobileNumber(mobileNumber);
		if (userOptional.isPresent()) {
			User user= userOptional.get();
			if (encryptionService.verifypassword(password, user.getPassword())) {
				LocalUserDetails userDetails=new LocalUserDetails(user);
				String jwt=jwtService.generateToken(userDetails);
				LoginDto loginDto=new LoginDto();
				loginDto.setJwtToken(jwt);
				loginDto.setName(user.getName());
				loginDto.setMobileNumber(user.getMobileNumber());
				loginDto.setEmail(user.getEmail());
				return loginDto;
			}else {
				throw new UserIsNotFoundedException("invalid password");
			}
		}else {
			throw new UserIsNotFoundedException("no one registered with this mobile nuber");
		}
	}
	
	
}
