package motoworld.project.entitiesTest.service;

import motoworld.project.model.entities.Motorcycle;
import motoworld.project.repository.MotorcycleRepository;
import motoworld.project.service.MotorcycleViewService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class MotorcycleViewServiceTest {

    private final String MOTO_STRING_TEST = "Yamaha";
    private final int MOTO_NUMBER_TEST = 99;

    Motorcycle motorcycle;

    @MockBean
    MotorcycleRepository motorcycleRepository;

    @Autowired
    MotorcycleViewService motorcycleViewService;


    @BeforeEach
    void setup() {
        motorcycle = new Motorcycle();
        motorcycle.setModel(MOTO_STRING_TEST)
                .setMake(MOTO_STRING_TEST)
                .setCc(MOTO_NUMBER_TEST)
                .setHp(MOTO_NUMBER_TEST)
                .setType(MOTO_STRING_TEST)
                .setDescription(MOTO_STRING_TEST)
                .setDealerUrl(MOTO_STRING_TEST)
        .setId("1");

    }

    @AfterEach
    void reset() {
        Mockito.reset(motorcycleRepository);
    }



    @Test
    public void testGetByIdModel(){
        Mockito.when(motorcycleRepository.saveAndFlush(motorcycle))
                .thenReturn(motorcycle);


      motorcycleViewService.getById("1");



    }
}
