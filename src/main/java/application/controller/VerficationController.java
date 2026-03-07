package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.model.VerificationToken;
import application.service.VerificationServiceImpl;
import security.config.Constants;

@RestController
public class VerficationController
{
    @Autowired
    VerificationServiceImpl verificationService;
    
    @GetMapping("/verifyRegistration")
    public String verifyRegisteredUser(@RequestParam("token") String token)
    {
	String result = verificationService.validateVerificationToken(token);
	//Checking for valid / invalid and expired	
	switch (result) {
	case "expired":
	    return "User token is expired after "+ Constants.EXPIRATION_TIME+" mins.";
	case "invalid":
	    return "Bad User/ incorrect token";
	default:
		if (result.contains("valid"))
		{
			String[] parts = result.split(":");
			return "Welcome onboard " + parts[1];
		}
	    return "Error";
	}
    }
    
    @GetMapping("/allTokens")
    public List<VerificationToken> getAllVerificationTokens()
    {
	return verificationService.getAllVerificationTokens();
    }
}
