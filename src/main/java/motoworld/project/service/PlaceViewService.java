package motoworld.project.service;



import motoworld.project.model.entities.Place;
import motoworld.project.model.entities.Review;
import motoworld.project.model.service.PlaceServiceModel;

import java.util.List;
import java.util.Optional;

public interface PlaceViewService {
    List<PlaceServiceModel> getAllPlaces();

    PlaceServiceModel getByIdModel(String id);

    Optional<Place> getById(String id);

    void save(Place place);

    List<Review> findAllById(String id);

    Optional<Place> findById(String id);
}
