package motoworld.project.model.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "place")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "place")
public class Place extends BaseEntity{

    @XmlElement
    private String name;
    @XmlElement
    private String location;
    @XmlElement
    private String postcode;
    @XmlElement
    private String description;
    @XmlElement
    private String googleLocationURL;
    private List<Image> images = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();


    public Place() {
    }


    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public Place setName(String name) {
        this.name = name;
        return this;
    }

    @Column(name = "location",unique = true, nullable = false)
    public String getLocation() {
        return location;
    }

    public Place setLocation(String location) {
        this.location = location;
        return this;
    }

    @Column(name = "postcode",unique = true, nullable = false)
    public String getPostcode() {
        return postcode;
    }

    public Place setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Place setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name="google_location_url", columnDefinition = "TEXT")
    public String getGoogleLocationURL() {
        return googleLocationURL;
    }

    public Place setGoogleLocationURL(String googleLocationURL) {
        this.googleLocationURL = googleLocationURL;
        return this;
    }

    @OneToMany
    public List<Image> getImages() {
        return images;
    }

    public Place setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    @OneToMany
    public List<Review> getReviews() {
        return reviews;
    }

    public Place setReviews(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }
}
