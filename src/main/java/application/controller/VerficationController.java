package application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.model.VerificationToken;
import application.service.VerificationServiceImpl;
import security.config.Constants;

import javax.servlet.http.HttpServletRequest;

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
		switch (result)
		{
			case "expired":
				return "User token is expired after " + Constants.EXPIRATION_TIME + " mins.";
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

	@GetMapping("/resendToken")
	public ResponseEntity<String> resendVerificationToken(@RequestParam("token") String oldToken, final HttpServletRequest request)
	{
		//TODO based on the oldToken search for the token. If found(ie: not expired) then resend the URL and extend the time again
		String url=new String();
		String validStatus = verificationService.validateResendToken(oldToken);
		String[] parts = validStatus.split(":");
		if(validStatus.contains("resendTokenStatusValid"))
		{
			url = UserRegistrationController.composeURL(request);
		}

		if(!url.isEmpty() && url!=null)
		{
			return new ResponseEntity<String>(url+"verifyRegistration?token="+parts[1], HttpStatus.OK);
		}
		return new ResponseEntity<String>("The User is not present or expired. Please register again!",HttpStatus.NOT_FOUND);
	}


    
    @GetMapping("/allTokens")
    public List<VerificationToken> getAllVerificationTokens()
    {
	return verificationService.getAllVerificationTokens();
    }
}
