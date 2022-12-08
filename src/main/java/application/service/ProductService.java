package application.service;

import java.util.List;
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
	Product nullProduct = new Product();
	nullProduct.setName("NULL");
	nullProduct.setPrice(0.0);
	nullProduct.setQuantity(0);
	
	System.out.println("Service : "+productId);
	Product product = repository.findById(productId).orElse(nullProduct);
	System.out.println("Service (Fetched Product) : "+product);
	return product;
    }
    
    public List<Product> getAllProducts()
    {
	List<Product> products = repository.findAll();
	return products;
    }
    
    //POST services
    public Product addProduct(Product product)
    {	
	System.out.println("Service (Old Product Id) : " + product.getId());
	Product insertedProduct = repository.save(product);
	System.out.println("Service (New Product Id) : " + insertedProduct.getId());
	return insertedProduct;
    }
}
