package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import application.controller.ProductController;
import application.model.Product;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)//Using Random port so that this test doesn't interfere with other apps
class SpringBootCrudExampleApplicationTests {

    @Autowired
    private ProductController controller;
    

    @Test
    void contextLoads()
    {
	// This is currently empty. It only tests if all the configurations are loaded properly.	
    }
    
    @Test
    void testProductPath()
    {
	Product testProduct = new Product();
	testProduct.setName("Test Product-1");
	testProduct.setQuantity(1);
	testProduct.setPrice(10.0);
	
	UUID savedProductID = controller.addProduct(testProduct);
	Product revivedProduct = controller.getProductById(savedProductID);
	
	assertNotNull(savedProductID);
	assertEquals(savedProductID, revivedProduct.getId());
	System.err.println("Product UUID : "+savedProductID);
	assertEquals("Test Product-1", revivedProduct.getName());
	assertEquals(1, revivedProduct.getQuantity());
	assertEquals(10.0, revivedProduct.getPrice());
	
    }
}
