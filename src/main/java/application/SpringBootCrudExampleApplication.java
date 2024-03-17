package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"application.controller","application.model","application.repository","application.service","security.event","security.listener","security.config"})
public class SpringBootCrudExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudExampleApplication.class, args);
	}
}
