package com.example.NoteDemo309;

import com.example.NoteDemo309.note.Note;
import com.example.NoteDemo309.note.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*@Bean
	public CommandLineRunner loadData(NoteRepository noteRepository) {
		return args -> {
			PageRequest pageable = PageRequest.of(0, 5);
			Page<Note> byUserId = noteRepository.findByUserId(String.valueOf(1), pageable);
			List<Note> content = byUserId.getContent();
			System.out.println("content = " + content);
		};
	}*/
}
