package application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import application.model.UserEntity;
import application.model.UserModel;
import application.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;
    
    //@Autowired
    //private PasswordEncoder passwordEncoder;
    
    @Override
    public UserEntity userRegisterService(UserModel userModel)
    {
	UserEntity user = new UserEntity();
	user.setFirstName(userModel.getFirstName());
	user.setLastName(userModel.getLastName());
	user.setEmail(userModel.getEmail());
	user.setRole("USER");
	user.setPassword(userModel.getPassword());
	
	return userRepository.save(user);
    }
    
    @Override
    public List<UserEntity> getAllUsers()
    {
	return userRepository.findAll();
    }
}
