package application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import application.model.Product;
import application.service.ProductService;

@RestController
public class ProductController
{
    @Autowired
    private ProductService service;
    
    @GetMapping("/welcome")
    public String testSetup()
    {
	System.out.println("Hello World");
	return "Hello World";
    }
    
    //GET methods
    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable(name = "id") UUID productId)
    {
	System.out.println("Controller : "+productId);
	Product fetchedProduct = service.getProductById(productId);
	System.out.println("Controller (fetched product) : "+fetchedProduct);
	return fetchedProduct;
    }
    
    @GetMapping("/allProducts")
    public List<Product> getAllProducts()
    {
	return service.getAllProducts();
    }
    
    //POST Methods
    @PostMapping("/addProduct")
    public UUID addProduct(@RequestBody Product newProduct)
    {
	System.out.println("Controller : "+newProduct);
	Product insertedProduct = service.addProduct(newProduct);
	System.out.println("Controller (Inserted Product) : "+insertedProduct.getId());
	return insertedProduct.getId();
    }
    
    //POST Methods
    @PostMapping("/addProducts")
    public List<UUID> addProduct(@RequestBody List<Product> newProducts)
    {
	System.out.println("Controller : "+newProducts);
	List<UUID> returnKeys = new ArrayList<>();
	for(Product newProduct : newProducts)
	    returnKeys.add(addProduct(newProduct));
	
	return returnKeys;
    }

}
