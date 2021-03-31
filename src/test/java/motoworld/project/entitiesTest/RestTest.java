package motoworld.project.entitiesTest;

import motoworld.project.model.entities.UserEntity;
import motoworld.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestTest {

//TODO : I HAVE NO IDEA :O !!!

    @Autowired
    private MockMvc mockMVC;

    @Autowired
    private UserRepository userRepository;


    private final String TEST_USERNAME_NAME = "Pesho";

    @BeforeEach
    public  void setUp(){

        UserEntity userEntity = new UserEntity().setUsername(TEST_USERNAME_NAME).setPassword("12321");
        userRepository.save(userEntity);

    }


    @Test
    public void  testRestAllUsersCorrectStatusCode() throws Exception {
        this.mockMVC.perform(get("/users")).
                andExpect(status().isOk());
    }


}
