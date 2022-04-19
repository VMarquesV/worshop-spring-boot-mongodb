package com.example.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override 
	public void run(String... arg0) throws Exception {
		
		userRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User dog = new User(null, "Big Dog", "bigdog@gmail.com");
		User paper = new User(null, "Paper", "paper@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, dog, paper));
		
	}
}
