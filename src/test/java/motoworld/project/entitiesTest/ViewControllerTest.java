package motoworld.project.entitiesTest;

import motoworld.project.model.entities.Image;
import motoworld.project.model.entities.Place;
import motoworld.project.model.entities.UserEntity;
import motoworld.project.repository.MotorcycleRepository;
import motoworld.project.repository.PlaceRepository;
import motoworld.project.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ViewControllerTest {

    private static final String PLACES_PREFIX = "/view/places";

    private String testPlaceId;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlaceRepository placeRepository;

    @BeforeEach
    public void setup() {
        this.init();
    }

    @Test
    @WithMockUser(value = "admin", roles = {"USER", "ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                PLACES_PREFIX + "/details/{id}", testPlaceId
        )).
                andExpect(status().isOk()).
                andExpect(view().name("place-details")).
                andExpect(model().attributeExists("place"));
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"ROLE_USER", "ROLE_ADMIN"})
    void addPlace() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("admin/add/place/add")
                .param("name", "ace2").
                        param("location", "london2").
                        param("postcode", "KT62").
                        param("googleLocationURL", "somewhere2").
                        param("description", "nice2").
                        with(csrf())).
                andExpect(status().is3xxRedirection());

        Assertions.assertEquals(2, placeRepository.count());

    }


    private void init() {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("pesho").setPassword("12321");
        userRepository.save(userEntity);

        Place place = new Place();
        place
                .setName("ace")
                .setLocation("london")
                .setPostcode("kt6")
                .setGoogleLocationURL("somewhere")
                .setImages(List.of(new Image("image")))
                .setDescription("nice");


        place = placeRepository.save(place);
        testPlaceId = place.getId();
    }
}
