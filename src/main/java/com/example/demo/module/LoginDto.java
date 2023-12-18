package com.example.demo.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
	
	private String name;
	private long mobileNumber;
	private String email;
	private String jwtToken;
    
}
