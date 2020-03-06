package com.mathieuaime.hhf.bar;

import com.mathieuaime.hhf.bar.model.BarDocument;
import com.mathieuaime.hhf.bar.repository.BarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BarServiceApplication implements CommandLineRunner {

	@Autowired
	private BarRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(BarServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		BarDocument barDocument = new BarDocument();
		barDocument.setName("Bar1");
		repository.save(barDocument);
	}
}
