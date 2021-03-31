package motoworld.project.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddUserBikeBindingModel {

    private String name;
    private MultipartFile imageUrl1;
    private MultipartFile imageUrl2;

    public AddUserBikeBindingModel() {
    }

    @NotBlank
    @Size(min = 3, max = 50, message = "Name must be between 3 and 20 characters")
    public String getName() {
        return name;
    }

    public AddUserBikeBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public MultipartFile getImageUrl1() {
        return imageUrl1;
    }

    public AddUserBikeBindingModel setImageUrl1(MultipartFile imageUrl1) {
        this.imageUrl1 = imageUrl1;
        return this;
    }

    public MultipartFile getImageUrl2() {
        return imageUrl2;
    }

    public AddUserBikeBindingModel setImageUrl2(MultipartFile imageUrl2) {
        this.imageUrl2 = imageUrl2;
        return this;
    }
}
