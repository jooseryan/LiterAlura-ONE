package br.com.livros.biblioteca;

import br.com.livros.biblioteca.main.Main;
import br.com.livros.biblioteca.service.AutorService;
import br.com.livros.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	@Autowired
	private LivroService service;

	@Autowired
	private AutorService autorService;

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(service, autorService);
		main.menu();
	}

}
