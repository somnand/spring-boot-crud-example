package application.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import application.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>
{    
    //Pre-filled by Spring
}
