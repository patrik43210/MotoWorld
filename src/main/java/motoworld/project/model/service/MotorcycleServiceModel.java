package motoworld.project.model.service;

import motoworld.project.model.entities.Image;

import java.util.List;

public class MotorcycleServiceModel extends BaseServiceModel{


    private String make;
    private String model;
    private Integer cc;
    private Integer hp;
    private String type;
    private String description;
    private String dealerUrl;
    private List<Image> images;


    public MotorcycleServiceModel() {
    }

    public String getMake() {
        return make;
    }

    public MotorcycleServiceModel setMake(String make) {
        this.make = make;
        return this;
    }

    public String getModel() {
        return model;
    }

    public MotorcycleServiceModel setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getCc() {
        return cc;
    }

    public MotorcycleServiceModel setCc(Integer cc) {
        this.cc = cc;
        return this;
    }

    public Integer getHp() {
        return hp;
    }

    public MotorcycleServiceModel setHp(Integer hp) {
        this.hp = hp;
        return this;
    }

    public String getType() {
        return type;
    }

    public MotorcycleServiceModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MotorcycleServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDealerUrl() {
        return dealerUrl;
    }

    public MotorcycleServiceModel setDealerUrl(String dealerUrl) {
        this.dealerUrl = dealerUrl;
        return this;
    }

    public List<Image> getImages() {
        return images;
    }

    public MotorcycleServiceModel setImages(List<Image> images) {
        this.images = images;
        return this;
    }
}
