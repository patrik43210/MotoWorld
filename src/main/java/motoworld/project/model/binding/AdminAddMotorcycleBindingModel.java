package motoworld.project.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class AdminAddMotorcycleBindingModel {


    private String make;
    private String model;
    private Integer cc;
    private Integer hp;
    private String type;
    private String description;
    private MultipartFile imageUrl1;
    private MultipartFile imageUrl2;
    private MultipartFile imageUrl3;
    private String dealerUrl;


    public AdminAddMotorcycleBindingModel() {
    }


    @NotBlank
    @Size(min = 3, max = 50, message = "Make must be between 3 and 20 characters")
    public String getMake() {
        return make;
    }

    public AdminAddMotorcycleBindingModel setMake(String make) {
        this.make = make;
        return this;
    }

    @NotBlank
    @Size(min = 3, max = 50, message = "Model must be between 3 and 20 characters")
    public String getModel() {
        return model;
    }

    public AdminAddMotorcycleBindingModel setModel(String model) {
        this.model = model;
        return this;
    }


    @PositiveOrZero(message = "Cc can not be negative.")
    public Integer getCc() {
        return cc;
    }

    public AdminAddMotorcycleBindingModel setCc(Integer cc) {
        this.cc = cc;
        return this;
    }

    @PositiveOrZero(message = "Hp can not be negative.")
    public Integer getHp() {
        return hp;
    }

    public AdminAddMotorcycleBindingModel setHp(Integer hp) {
        this.hp = hp;
        return this;
    }

    @NotBlank
    @Size(min = 3, max = 50, message = "Type must be between 3 and 20 characters")
    public String getType() {
        return type;
    }

    public AdminAddMotorcycleBindingModel setType(String type) {
        this.type = type;
        return this;
    }

    @NotBlank
    @Size(min = 3, message = "Description must be at least 3 characters")
    public String getDescription() {
        return description;
    }

    public AdminAddMotorcycleBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }


    public String getDealerUrl() {
        return dealerUrl;
    }

    public AdminAddMotorcycleBindingModel setDealerUrl(String dealerUrl) {
        this.dealerUrl = dealerUrl;
        return this;
    }

    public MultipartFile getImageUrl1() {
        return imageUrl1;
    }

    public AdminAddMotorcycleBindingModel setImageUrl1(MultipartFile imageUrl1) {
        this.imageUrl1 = imageUrl1;
        return this;
    }

    public MultipartFile getImageUrl2() {
        return imageUrl2;
    }

    public AdminAddMotorcycleBindingModel setImageUrl2(MultipartFile imageUrl2) {
        this.imageUrl2 = imageUrl2;
        return this;
    }

    public MultipartFile getImageUrl3() {
        return imageUrl3;
    }

    public AdminAddMotorcycleBindingModel setImageUrl3(MultipartFile imageUrl3) {
        this.imageUrl3 = imageUrl3;
        return this;
    }
}
