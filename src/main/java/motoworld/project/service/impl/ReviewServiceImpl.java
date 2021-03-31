package motoworld.project.service.impl;

import motoworld.project.model.binding.ReviewBindingModel;
import motoworld.project.model.entities.*;

import motoworld.project.repository.ReviewRepository;
import motoworld.project.service.MotorcycleViewService;
import motoworld.project.service.PlaceViewService;
import motoworld.project.service.ReviewService;
import motoworld.project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ModelMapper modelMapper;

    private final MotorcycleViewService motorcycleViewService;
    private final PlaceViewService placeViewService;
    private final UserService userService;
    private final ReviewRepository reviewRepository;


    public ReviewServiceImpl(ModelMapper modelMapper,  MotorcycleViewService motorcycleViewService, PlaceViewService placeViewService, UserService userService, ReviewRepository reviewRepository) {
        this.modelMapper = modelMapper;

        this.motorcycleViewService = motorcycleViewService;
        this.placeViewService = placeViewService;
        this.userService = userService;

        this.reviewRepository = reviewRepository;
    }


    @Override
    public String addReviewToMotorcycle(String id, ReviewBindingModel reviewBindingModel, String username) {

        UserEntity user = userService.findByUsername(username).orElse(null);
        reviewBindingModel.setUser(user);


        Review review = modelMapper.map(reviewBindingModel, Review.class);
        reviewRepository.save(review);


        Optional<Motorcycle> motorcycle = motorcycleViewService.getById(id);
        Optional<Place> place = placeViewService.getById(id);
        if(motorcycle.isEmpty()){
            place.get().getReviews().add(review);
            placeViewService.save(place.get());
            return "p";
        }else {
            motorcycle.get().getReviews().add(review);
            motorcycleViewService.save(motorcycle.get());
            return "m";
        }
    }


    @Override
    public List<ReviewBindingModel> getMotorcycleReviews(String id) {
        List<ReviewBindingModel> reviewsModel = new ArrayList<>();
        List<Review> reviews = motorcycleViewService.findAllById(id);

        for (Review review : reviews) {
            reviewsModel.add(modelMapper.map(review, ReviewBindingModel.class));
        }

        return reviewsModel;
    }

    @Override
    public List<ReviewBindingModel> getPlaceReviews(String id) {
        List<ReviewBindingModel> reviewsModel = new ArrayList<>();
        List<Review> reviews = placeViewService.findAllById(id);

        for (Review review : reviews) {
            reviewsModel.add(modelMapper.map(review,ReviewBindingModel.class));
        }

        return reviewsModel;
    }
}
