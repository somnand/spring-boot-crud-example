package application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.model.UserEntity;
import application.model.UserModel;
import application.service.UserService;

@RestController
public class RegistrationController
{
    @Autowired
    private UserService userService;
    
    //POST Methods
    @PostMapping("/register")
    public Long register(@RequestBody UserModel userModel)
    {
	UserEntity savedUser = userService.userRegisterService(userModel);
	return savedUser.getId();
    }
    
    @PostMapping("/registerUsers")
    public List<Long> registerUsers(@RequestBody List<UserModel> userModels)
    {
	List<Long> savedIds = new ArrayList<>();
	
	for(UserModel userModel : userModels)
	{
	    UserEntity savedUser = userService.userRegisterService(userModel);
	    savedIds.add(savedUser.getId());
	}
	
	return savedIds;
    }
    
    //GET Methods
    @GetMapping("/allUsers")
    public List<UserEntity> getAllUsers()
    {
	return userService.getAllUsers();
    }
}
