package application.controller;

import application.model.UserEntity;
import application.model.UserModel;
import application.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import security.event.RegistrationCompleteEvent;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class UserRegistrationController
{
    @Autowired
    private UserService userService;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    //POST Methods
    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserModel userModel, final HttpServletRequest request)
    {
        UserEntity savedUser = userService.userRegisterService(userModel);
        log.debug("User created and saved"+savedUser);
        publisher.publishEvent(new RegistrationCompleteEvent(savedUser, composeURL(request)));
        log.debug("Publishing Registration Completion Event");

        if(savedUser!=null)
            return new ResponseEntity(savedUser,HttpStatus.CREATED);
        else
            return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
    }
    
    private String composeURL(HttpServletRequest request)
    {
        StringBuilder url = new StringBuilder();
        url.append("http://").append(request.getServerName()).append(":").append(request.getServerPort()).append("/").append(request.getContextPath());
        log.debug("Composed URL : {}",url);
	    return url.toString();
    }    
    
    @PostMapping("/registerUsers")
    public ResponseEntity<List<Long>> registerUsers(@RequestBody List<UserModel> userModels,final HttpServletRequest request)
    {
        List<Long> savedIds = new ArrayList<>();

        try
        {
            for (UserModel userModel : userModels)
            {
                ResponseEntity<UserEntity> savedUserResponse = register(userModel, request);
                savedIds.add(savedUserResponse.getBody().getId());
            }
        }
        catch(RuntimeException re)
        {
            log.error("Error in saving multiple users ",re);
            return new ResponseEntity<List<Long>>(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<List<Long>>(savedIds,HttpStatus.CREATED);
    }
    
    //GET Methods
    @GetMapping("/allUsers")
    public List<UserEntity> getAllUsers()
    {
        return userService.getAllUsers();
    }
}
