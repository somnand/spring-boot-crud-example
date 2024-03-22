package application.service;

import java.util.List;

import application.model.UserEntity;
import application.model.VerificationToken;

public interface VerificationService
{
    public Long saveVerificationTokenForUser(String token,UserEntity user);
    public String validateVerificationToken(String token);
    public List<VerificationToken> getAllVerificationTokens();
}
