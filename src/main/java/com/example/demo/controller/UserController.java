package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.helper.ResponseBody;
import com.example.demo.module.Login;
import com.example.demo.module.LoginDto;
import com.example.demo.module.Registration;
import com.example.demo.module.User;
import com.example.demo.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseBody<?>> saveUser(@RequestBody User user) {
		userService.saveUser(user);
		ResponseBody<String> body=new ResponseBody<>();
		body.setStatusCode(HttpStatus.OK.value());
		body.setStatus("SUCCESS");
		body.setData("User Added Successfully");
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}

	@PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody Registration registration) {
		userService.userRegistration(registration);
            ResponseBody<Registration> body = new ResponseBody<>();
            body.setStatusCode(HttpStatus.OK.value());
            body.setStatus("SUCCESS");
            body.setData(registration);
            return ResponseEntity.ok(body);
 
    }
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseBody<?>> getAllUser(){
		List<User> users=userService.getAllUser();
		ResponseBody<List<User>> body=new ResponseBody<>();
		body.setStatusCode(HttpStatus.OK.value());
		body.setStatus("SUCCESS");
		body.setData(users);
		return ResponseEntity.status(HttpStatus.OK).body(body);
		
	}
	
	@PostMapping("/admin/registration")
    public ResponseEntity<?> adminRegistration(@RequestBody Registration registration) {
		userService.adminRegistration(registration);
            ResponseBody<Registration> body = new ResponseBody<>();
            body.setStatusCode(HttpStatus.OK.value());
            body.setStatus("SUCCESS");
            body.setData(registration);
            return ResponseEntity.ok(body);
 
    }
	
	@PostMapping("/login")
	public ResponseEntity<?> userLogInPage(@RequestBody Login login){
	LoginDto user =userService.userLogin(login.getPassword(), login.getMobileNumber());
			ResponseBody<LoginDto> body=new ResponseBody<>();
			body.setStatusCode(HttpStatus.OK.value());
			body.setStatus("SUCCESS");
			body.setData(user);
			return ResponseEntity.ok(body);
		
	}
	
	@PostMapping("/admin/login")
	public ResponseEntity<?> AdminLogInPage(@RequestBody Login login){
		userService.adminLogin(login.getPassword(), login.getMobileNumber());
			ResponseBody<String> body=new ResponseBody<>();
			body.setStatusCode(HttpStatus.OK.value());
			body.setStatus("SUCCESS");
			body.setData("login successful");
			return ResponseEntity.ok(body);
		
	}
	
}
