package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.repository.SerieRepository;
import br.com.alura.screenmatch.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {
	@Autowired
	private SerieRepository repository;

    public static void main(String[] args) {
		SpringApplication.	run(ScreenmatchApplication.class, args);
	}
	public void run(String... args) {
		Principal principal = new Principal(repository);
		principal.exibeMenu();
	}
}

