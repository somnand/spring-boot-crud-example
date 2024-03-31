package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import application.controller.ProductController;
import application.controller.UserRegistrationController;
import application.model.Product;
import application.model.UserModel;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // Using Random port so that this test doesn't interfere with other apps
@SpringJUnitConfig
@AutoConfigureMockMvc
class SpringBootCrudExampleApplicationTests
{

    @Autowired
    private ProductController controller;

    @Autowired
    private UserRegistrationController userController;

    @Autowired
    private MockMvc mockMVC;

    //@Test
    void contextLoads()
    {
	// This is currently empty. It only tests if all the configurations are loaded
	// properly.
    }

    // @Test
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
	System.err.println("Product UUID : " + savedProductID);
	assertEquals("Test Product-1", revivedProduct.getName());
	assertEquals(1, revivedProduct.getQuantity());
	assertEquals(10.0, revivedProduct.getPrice());

    }

    @Test
    public void testUserSavePath()throws Exception
    {
	UserModel testUser = new UserModel();
	testUser.setFirstName("Test User First Name");	
	testUser.setPassword("test_password_unencrypted");
	
	ResultActions response =  mockMVC.perform(MockMvcRequestBuilders.get("/register")).andExpect(MockMvcResultMatchers.status().isOk());
	response.andDo(MockMvcResultHandlers.print());
	MvcResult result =  response.andReturn();
	result.getRequest();
	
//	Long savedUserID = userController.register(testUser, testRequest);
//	UserEntity revivedUser = userController.getAllUsers().get(0);//Since saving single user
//	
//	assertNotNull(savedUserID);
//	assertEquals(savedUserID, revivedUser.getId());
//	System.err.println("User Long ID : " + savedUserID);
//	assertEquals("Test User First Name", revivedUser.getFirstName());
//	assertNotEquals("test_password_unencrypted", revivedUser.getPassword());
//	assertFalse(revivedUser.isEnabled());

    }
    // TODO Test User save path
    // TODO Test verified tokens
    // TODO Test expiration
}
