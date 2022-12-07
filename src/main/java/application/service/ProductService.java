package application.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.model.Product;
import application.repository.ProductRepository;

@Service
public class ProductService 
{
    @Autowired
    private ProductRepository repository;
    
    //GET services
    public Product getProductById(UUID productId)
    {
	System.out.println("Till Service");
	Product product = repository.findById(productId).orElse(null);
	//getReferenceById(productId);
	return product;
    }
    
    //POST services
    public UUID addProduct(Product product)
    {	
	System.out.println("Till Service");
	Product insertedProduct = repository.save(product);
	System.out.println("New Product Id : "+product.getID());
	return product.getID();
    }
}
