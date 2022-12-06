package service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Product;
import repository.ProductRepository;

@Service
public class ProductService 
{
    @Autowired
    private ProductRepository repository;
    
    //GET services
    public Product getProductById(UUID productId)
    {
	Product product = repository.findById(productId).orElse(null);
	//getReferenceById(productId);
	return product;
    }
    
    //POST services
    public void addProduct(Product product)
    {	
	repository.save(product);
    }
}
