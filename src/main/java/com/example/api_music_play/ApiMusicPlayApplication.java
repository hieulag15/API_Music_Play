package com.example.api_music_play;

import com.example.api_music_play.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiMusicPlayApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiMusicPlayApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
