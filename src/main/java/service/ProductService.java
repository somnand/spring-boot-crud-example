package service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import model.Product;
import repository.ProductRepository;

public class ProductService 
{
    @Autowired
    private ProductRepository repository;
    
    //GET methods
    public void getProductById(UUID productId)
    {
	repository.getReferenceById(productId);
    }
    
    //POST methods
    public void addProduct(Product product)
    {	
	repository.save(product);
    }
}
