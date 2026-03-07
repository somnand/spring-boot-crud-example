package application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)//Using Random port so that this test doesn't interfere with other apps
class SpringBootCrudExampleApplicationTests {

    @Test
    void contextLoads()
    {
	// This is currently empty. It only tests if all the configurations are loaded properly.	
    }

    //TODO Tets User save path
    //TODO Test verified tokens
    //TODO Test expiration
}
