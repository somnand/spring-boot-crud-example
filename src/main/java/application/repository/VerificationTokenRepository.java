package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> 
{

}
