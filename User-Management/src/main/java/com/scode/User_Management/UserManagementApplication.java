package com.scode.User_Management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableCaching
public class UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
//		System.out.println(new BCryptPasswordEncoder().encode("Admin@123"));

	}
}
