package motoworld.project.service.impl;


import motoworld.project.model.entities.Place;
import motoworld.project.model.entities.Review;
import motoworld.project.model.service.PlaceServiceModel;
import motoworld.project.repository.PlaceRepository;
import motoworld.project.service.PlaceViewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceViewServiceImpl implements PlaceViewService {

    private final PlaceRepository placeRepository;
    private final ModelMapper modelMapper;

    public PlaceViewServiceImpl(PlaceRepository placeRepository, ModelMapper modelMapper) {
        this.placeRepository = placeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<PlaceServiceModel> getAllPlaces() {
        List<PlaceServiceModel> placesModels = new ArrayList<>();
        List<Place> places = placeRepository.findAll();

        for (Place place : places) {
            placesModels.add(modelMapper.map(place,PlaceServiceModel.class));
        }

        return placesModels;
    }

    @Override
    public PlaceServiceModel getByIdModel(String id) {
        Place place = placeRepository.findById(id).get();

        return modelMapper.map(place, PlaceServiceModel.class);
    }

    @Override
    public Optional<Place> getById(String id) {
        return placeRepository.findById(id);
    }

    @Override
    public void save(Place place) {
        placeRepository.save(place);
    }

    @Override
    public List<Review> findAllById(String id) {
        return placeRepository.findById(id).get().getReviews();
    }

    @Override
    public Optional<Place> findById(String id) {
        return placeRepository.findById(id);
    }
}
