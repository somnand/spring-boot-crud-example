package application.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.model.UserEntity;
import application.model.VerificationToken;
import application.repository.VerificationTokenRepository;

@Service
public class VerificationServiceImpl implements VerificationService
{
    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    
    @Override
    public String validateVerificationToken(String token)
    {
	/* Logic used for verification of token.
	 * 1. Token not present - Invalid User
	 * 2. Token Present but expired - Delete Token and return invalid User
	 * 3. Token Present and not expired - Valid User	
	 */
	 
	VerificationToken savedverificationToken = verificationTokenRepository.findByToken(token);
	
	if(savedverificationToken==null)
	    return "invalid";
	
	Calendar cal = Calendar.getInstance();
	
	if(savedverificationToken.getExpirationTime().getTime() - cal.getTime().getTime()<= 0)//This time is set in VerificationToken
	{
	    verificationTokenRepository.delete(savedverificationToken);
	    savedverificationToken.getUser().setEnabled(false);
	    verificationTokenRepository.save(savedverificationToken);//Saving the verification to Database
	    return "expired";	    
	}
	//If this section is reached then the User is Valid.
	savedverificationToken.getUser().setEnabled(true);
	verificationTokenRepository.save(savedverificationToken);
	return "valid :"+savedverificationToken.getUser().getFirstName()+" "+savedverificationToken.getUser().getLastName();
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
