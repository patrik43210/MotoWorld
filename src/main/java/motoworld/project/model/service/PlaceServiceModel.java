package motoworld.project.model.service;


import motoworld.project.model.entities.Image;

import java.util.List;

public class PlaceServiceModel extends BaseServiceModel{

    private String name;
    private String location;
    private String postcode;
    private String description;
    private String googleLocationURL;
    private String profilePicture;
    private List<Image> images;


    public PlaceServiceModel() {
    }

    public String getName() {
        return name;
    }

    public PlaceServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public PlaceServiceModel setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getPostcode() {
        return postcode;
    }

    public PlaceServiceModel setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PlaceServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGoogleLocationURL() {
        return googleLocationURL;
    }

    public PlaceServiceModel setGoogleLocationURL(String googleLocationURL) {
        this.googleLocationURL = googleLocationURL;
        return this;
    }

    public List<Image> getImages() {
        return images;
    }

    public PlaceServiceModel setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public PlaceServiceModel setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }
}
