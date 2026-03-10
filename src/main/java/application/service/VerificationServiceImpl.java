package application.service;

import java.util.Calendar;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import security.config.Constants;
import application.model.UserEntity;
import application.model.VerificationToken;
import application.repository.VerificationTokenRepository;

@Slf4j
@Service
public class VerificationServiceImpl implements VerificationService
{
    @Autowired
    VerificationTokenRepository verificationTokenRepository;

	/**
	 *  Logic used for verification of token.
	 * 1. Token not present - Invalid User
	 * 2. Token Present but expired - Delete Token and return Expired User
	 * 3. Token Present and not expired - Valid User
	 */
    @Override
	public String validateVerificationToken(String token)
	{
		VerificationToken savedverificationToken = verificationTokenRepository.findByToken(token);

		if (savedverificationToken == null)
			return "invalid";

		Calendar cal = Calendar.getInstance();
		//Checking if the token has expired or not
		if (savedverificationToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0)//This time is set in VerificationToken
		{
			verificationTokenRepository.delete(savedverificationToken);
			savedverificationToken.getUser().setEnabled(false);
			//verificationTokenRepository.save(savedverificationToken);//Saving the verification token to Database
			return "expired";
		}
		//If this section is reached then the User is Valid.
		savedverificationToken.getUser().setEnabled(true);
		verificationTokenRepository.save(savedverificationToken);
		return "valid :" + savedverificationToken.getUser().getFirstName() + " " + savedverificationToken.getUser().getLastName();
	}

	public String validateResendToken(String oldToken)
	{
		String validationResult = validateVerificationToken(oldToken);
		if(validationResult.startsWith("valid"))
		{
			VerificationToken token = verificationTokenRepository.findByToken(oldToken);
			Calendar cal = Calendar.getInstance();
			long expirationTime = token.getExpirationTime().getTime();
			cal.setTimeInMillis(expirationTime);
			cal.add(Calendar.MINUTE,Constants.EXPIRATION_TIME);
			token.setExpirationTime(cal.getTime());
			verificationTokenRepository.save(token);
			return "resendTokenStatusValid"+":"+token.getToken();
		}
		return "resendTokenStatusInvalid";
	}
    
    @Override
    public List<VerificationToken> getAllVerificationTokens()
    {
	return verificationTokenRepository.findAll();
    }
    
    @Override
    public Long saveVerificationTokenForUser(String token,UserEntity user)
    {
	VerificationToken verificationToken = new VerificationToken(user,token);
	VerificationToken savedToken = verificationTokenRepository.save(verificationToken);
	return savedToken.getId();
    }

}
