package motoworld.project.entitiesTest;

import motoworld.project.model.entities.Image;
import motoworld.project.model.entities.Motorcycle;
import motoworld.project.model.entities.Review;
import motoworld.project.repository.MotorcycleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    //TODO : I HAVE NO IDEA :O !!!

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @BeforeEach
    public void  setUp(){
        Review review = new Review().setReview("Nice");
        Image image = new Image().setImage("Image");
        Motorcycle motorcycle1 = new Motorcycle().
                setCc(20).
                setDescription("Something").
                setHp(20).
                setMake("Yamaha").
                setModel("R3").
                setType("Sport").
                setReviews(List.of(review)).
                setImages(List.of(image)).
                setDealerUrl("URL");

        Motorcycle motorcycle2 = new Motorcycle().
                setCc(20).
                setDescription("Something").
                setHp(20).
                setMake("Yamaha").
                setModel("R3").
                setType("Sport").
                setReviews(List.of(review)).
                setImages(List.of(image)).
                setDealerUrl("URL");

        motorcycleRepository.saveAll(List.of(motorcycle1,motorcycle2));

    }

    @Test
    public  void test() throws Exception{
       this.mockMvc.perform(get("/moto/api"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$",hasSize(2)));

    }
}
