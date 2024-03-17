package security.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import application.model.UserEntity;
import application.service.UserService;
import lombok.extern.slf4j.Slf4j;
import security.event.RegistrationCompleteEvent;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent>
{
    @Autowired
    UserService userService;
    
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event)
    {
	//Create verification token for the User
	UserEntity user = event.getUser();
	String token = UUID.randomUUID().toString();
	userService.saveVerificationTokenForUser(token, user);
	//Send URL link to User/ Currently printing to console
	String url = event.getApplicationURL() + "verifyRegistration?token="+token;
	log.info("Verifiaction URL :"+url);
    }
}
