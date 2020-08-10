package br.com.sura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DesafioSuraApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioSuraApplication.class, args);
	}

}
