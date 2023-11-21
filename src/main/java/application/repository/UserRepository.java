package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import application.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>
{
    //Automatically set by Spring
}
