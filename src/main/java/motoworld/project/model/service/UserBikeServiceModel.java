package motoworld.project.model.service;

import motoworld.project.model.entities.Image;

import java.util.List;

public class UserBikeServiceModel extends BaseServiceModel{
    private String name;
    private List<Image> images;

    public UserBikeServiceModel() {
    }

    public String getName() {
        return name;
    }

    public UserBikeServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public List<Image> getImages() {
        return images;
    }

    public UserBikeServiceModel setImages(List<Image> images) {
        this.images = images;
        return this;
    }
}
