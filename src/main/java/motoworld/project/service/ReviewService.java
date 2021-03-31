package motoworld.project.service;


import motoworld.project.model.binding.ReviewBindingModel;

import java.util.List;

public interface ReviewService {
    String addReviewToMotorcycle(String id, ReviewBindingModel reviewBindingModel, String username);


    List<ReviewBindingModel> getMotorcycleReviews(String id);

    List<ReviewBindingModel> getPlaceReviews(String id);
}
