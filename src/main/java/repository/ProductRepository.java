package repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Product;

public interface ProductRepository extends JpaRepository<Product, UUID>
{    
    //Pre-filled by Spring 
}
