package motoworld.project.model.service;

import motoworld.project.model.entities.Image;

public class UserInfoServiceModel extends BaseServiceModel{

    private String fullName;
    private String fromCountry;
    private String aboutUser;
    private Image profilePicture;

    public UserInfoServiceModel() {
    }

    public String getFullName() {
        return fullName;
    }

    public UserInfoServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public UserInfoServiceModel setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
        return this;
    }

    public String getAboutUser() {
        return aboutUser;
    }

    public UserInfoServiceModel setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
        return this;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public UserInfoServiceModel setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }
}
