package motoworld.project.model.service;



import motoworld.project.model.entities.Image;
import motoworld.project.model.entities.UserBike;

import java.util.ArrayList;
import java.util.List;

public class ProfileServiceModel extends BaseServiceModel{

    private String username;
    private String fullName;
    private String aboutUser;
    private String fromCountry;
    private Image profilePicture;
    private List<UserBike> userBikes = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public ProfileServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public ProfileServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getAboutUser() {
        return aboutUser;
    }

    public ProfileServiceModel setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
        return this;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public ProfileServiceModel setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
        return this;
    }

    public List<UserBike> getUserBikes() {
        return userBikes;
    }

    public ProfileServiceModel setUserBikes(List<UserBike> userBikes) {
        this.userBikes = userBikes;
        return this;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public ProfileServiceModel setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }


}
