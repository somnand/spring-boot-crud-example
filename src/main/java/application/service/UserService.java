package application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import application.model.UserEntity;
import application.model.UserModel;
import application.model.VerificationToken;

public interface UserService
{
    public UserEntity userRegisterService(UserModel userModel);
    public List<UserEntity> getAllUsers();        
}
