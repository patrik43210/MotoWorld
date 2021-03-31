package motoworld.project.service;


import motoworld.project.model.entities.Motorcycle;
import motoworld.project.model.entities.Review;
import motoworld.project.model.service.MotorcycleRestModel;
import motoworld.project.model.service.MotorcycleServiceModel;

import java.util.List;
import java.util.Optional;

public interface MotorcycleViewService {



    MotorcycleServiceModel getByIdModel(String id);

    Optional<Motorcycle> getById(String id);

    void save(Motorcycle motorcycle);

    List<Review> findAllById(String id);

    Motorcycle findById(String motorcycleId);

    List<MotorcycleRestModel> getAllRestMotorcycles();
}
