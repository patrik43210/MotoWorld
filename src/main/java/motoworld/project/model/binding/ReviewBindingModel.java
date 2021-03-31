package motoworld.project.model.binding;


import motoworld.project.model.entities.UserEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ReviewBindingModel {

    private String review;
    private UserEntity User;


    public ReviewBindingModel() {
    }


    @NotBlank
    @Size(min = 5,message = "Review must be at least 5 char")
    public String getReview() {
        return review;
    }

    public ReviewBindingModel setReview(String review) {
        this.review = review;
        return this;
    }

    public UserEntity getUser() {
        return User;
    }

    public ReviewBindingModel setUser(UserEntity user) {
        User = user;
        return this;
    }
}
