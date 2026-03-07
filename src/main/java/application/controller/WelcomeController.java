package application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController
{
	@RequestMapping("/welcome")
	public String welcomeTestAPI()
	{
		return "Welcome to Spring Security Project";
	}

}
