package com.example.demo.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registration {

	private String name;
	
	private String email;
	
	private long mobileNumber;
	
	private String password;
}
