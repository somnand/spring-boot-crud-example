package application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import application.controller.ProductController;
import application.service.ProductService;


@WebMvcTest(ProductController.class)
public class ProductRestControllerTest 
{
	@Autowired
    private MockMvc	mvc;
	
	@MockBean
	private ProductService productService;
	
	@Test
	void testWelcomeAPI()throws Exception
    {
		ResultActions response = mvc.perform(MockMvcRequestBuilders.get("/welcome"));
    	int status = response.andReturn().getResponse().getStatus();
    	assertEquals(200, status);
    }
	
	//@Test
	void testProductPath()throws Exception
    {
    	ResultActions response = mvc.perform(MockMvcRequestBuilders.post("/addProduct")
    			.content("{\r\n"
    			+ "\"name\" : \"Samsung S9\",\r\n"
    			+ "\"quantity\" : 1,\r\n"
    			+ "\"price\" : 78000\r\n"
    			+ "}")
    			.contentType(MediaType.APPLICATION_JSON)
    			);
    	int status = response.andReturn().getResponse().getStatus();
    	assertEquals(200, status);
    }

}
