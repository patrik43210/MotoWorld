package motoworld.project.model.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_info")
public class UserInfo extends BaseEntity {

    private String fullName;

    private String aboutUser;

    private String fromCountry;

    private Image profilePicture;

    private List<UserBike> userBikes = new ArrayList<>();

    private List<Place> favouritePlaces;

    public UserInfo() {
    }

    public UserInfo(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "full_name", nullable = false)
    public String getFullName() {
        return fullName;
    }

    public UserInfo setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Column(name = "about_user", columnDefinition = "TEXT")
    public String getAboutUser() {
        return aboutUser;
    }

    public UserInfo setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
        return this;
    }

    @Column(name = "from_country")
    public String getFromCountry() {
        return fromCountry;
    }

    public UserInfo setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
        return this;
    }

    @OneToOne
    public Image getProfilePicture() {
        return profilePicture;
    }

    public UserInfo setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    @OneToMany
    public List<UserBike> getUserBikes() {
        return userBikes;
    }

    public UserInfo setUserBikes(List<UserBike> userBikes) {
        this.userBikes = userBikes;
        return this;
    }

    @ManyToMany
    public List<Place> getFavouritePlaces() {
        return favouritePlaces;
    }

    public UserInfo setFavouritePlaces(List<Place> favouritePlaces) {
        this.favouritePlaces = favouritePlaces;
        return this;
    }
}
