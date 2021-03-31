package motoworld.project.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AdminAddPlaceBindingModel {
    private String name;
    private String location;
    private String postcode;
    private String description;
    private String googleLocationURL;
    private MultipartFile imageUrl1;
    private MultipartFile imageUrl2;
    private MultipartFile imageUrl3;

    public AdminAddPlaceBindingModel() {
    }

    @NotBlank
    @Size(min = 3, max = 50,message = "name must be between 3 and 20 characters")
    public String getName() {
        return name;
    }

    public AdminAddPlaceBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotBlank
    @Size(min = 3, max = 50,message = "Location must be between 3 and 20 characters")
    public String getLocation() {
        return location;
    }

    public AdminAddPlaceBindingModel setLocation(String location) {
        this.location = location;
        return this;
    }

    @NotBlank
    @Size(min = 3, max = 50,message = "Postcode must be between 3 and 20 characters")
    public String getPostcode() {
        return postcode;
    }

    public AdminAddPlaceBindingModel setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    @NotBlank
    @Size(min = 3,message = "Description at least 3 characters")
    public String getDescription() {
        return description;
    }

    public AdminAddPlaceBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotBlank
    @Size(min = 3,message = "Google url is require")
    public String getGoogleLocationURL() {
        return googleLocationURL;
    }

    public AdminAddPlaceBindingModel setGoogleLocationURL(String googleLocationURL) {
        this.googleLocationURL = googleLocationURL;
        return this;
    }


    public MultipartFile getImageUrl1() {
        return imageUrl1;
    }

    public AdminAddPlaceBindingModel setImageUrl1(MultipartFile imageUrl1) {
        this.imageUrl1 = imageUrl1;
        return this;
    }

    public MultipartFile getImageUrl2() {
        return imageUrl2;
    }

    public AdminAddPlaceBindingModel setImageUrl2(MultipartFile imageUrl2) {
        this.imageUrl2 = imageUrl2;
        return this;
    }

    public MultipartFile getImageUrl3() {
        return imageUrl3;
    }

    public AdminAddPlaceBindingModel setImageUrl3(MultipartFile imageUrl3) {
        this.imageUrl3 = imageUrl3;
        return this;
    }
}
