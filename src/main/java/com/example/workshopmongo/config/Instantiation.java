package com.example.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.dto.AuthorDTO;
import com.example.workshopmongo.dto.CommentDTO;
import com.example.workshopmongo.repository.PostRepository;
import com.example.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override 
	public void run(String... arg0) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User dog = new User(null, "Big Dog", "bigdog@gmail.com");
		User paper = new User(null, "Paper", "paper@gmail.com");
		userRepository.saveAll(Arrays.asList(maria, dog, paper));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem" , "Vou viajar para São Paulo!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom Dia" , "Acordei feliz hoje", new AuthorDTO(maria));
		
		CommentDTO cmt1 = new CommentDTO("Boa Viagem!", sdf.parse("21/03/2018"), new AuthorDTO(dog));
		CommentDTO cmt2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(paper));
		CommentDTO cmt3 = new CommentDTO("Tenha um ótimo dia", sdf.parse("23/03/2018"), new AuthorDTO(dog));
		
		post1.getComment().addAll(Arrays.asList(cmt1, cmt2));
		post2.getComment().addAll(Arrays.asList(cmt3));

		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}
}
