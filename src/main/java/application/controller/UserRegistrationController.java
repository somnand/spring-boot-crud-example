package application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.model.UserEntity;
import application.model.UserModel;
import application.model.VerificationToken;
import application.service.UserService;
import security.event.RegistrationCompleteEvent;

@RestController
public class UserRegistrationController
{
    @Autowired
    private UserService userService;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    //POST Methods
    @PostMapping("/register")
    public Long register(@RequestBody UserModel userModel, final HttpServletRequest request)
    {
	UserEntity savedUser = userService.userRegisterService(userModel);
	publisher.publishEvent(new RegistrationCompleteEvent(savedUser, composeURL(request)));
	return savedUser.getId();
    }
    
    private String composeURL(HttpServletRequest request)
    {
	return "http://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath();
    }
    
    @GetMapping("/verifyRegistration")
    public String verifyRegisteredUser(@RequestParam("token") String token)
    {
	return null;
    }    
    
    @PostMapping("/registerUsers")
    public List<Long> registerUsers(@RequestBody List<UserModel> userModels,final HttpServletRequest request)
    {
	List<Long> savedIds = new ArrayList<>();
	
	for(UserModel userModel : userModels)
	{
	    savedIds.add(register(userModel,request));
	}
	
	return savedIds;
    }
    
    //GET Methods
    @GetMapping("/allUsers")
    public List<UserEntity> getAllUsers()
    {
	return userService.getAllUsers();
    }
    
    @GetMapping("/allTokens")
    public List<VerificationToken> getAllVerificationTokens()
    {
	return userService.getAllVerificationTokens();
    }
}
