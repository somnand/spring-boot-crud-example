package security.listener;

import application.model.UserEntity;
import application.service.VerificationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import security.event.RegistrationCompleteEvent;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent>
{
    @Autowired
    VerificationServiceImpl verificationService;
    
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event)
    {
        //Create verification token for the User
        UserEntity user = event.getUser();
        String token = UUID.randomUUID().toString();
        verificationService.saveVerificationTokenForUser(token, user);
        //Send URL link to User/ Currently printing to console
        String url = event.getApplicationURL() + "verifyRegistration?token=" + token;
        log.info("Verification URL ---> {}", url);
    }
}
