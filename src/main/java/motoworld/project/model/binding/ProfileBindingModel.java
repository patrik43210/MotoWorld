package motoworld.project.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProfileBindingModel {

private String fullName;
    private String fromCountry;
    private String aboutUser;
    private String profilePicture;

    @NotBlank
    @Size(min = 3, max = 20,message = "First name is require")
    public String getFullName() {
        return fullName;
    }

    public ProfileBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public ProfileBindingModel setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
        return this;
    }

    public String getAboutUser() {
        return aboutUser;
    }

    public ProfileBindingModel setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
        return this;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public ProfileBindingModel setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }
}
