package motoworld.project.model.entities;


import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "motorcycle")
public class Motorcycle  extends BaseEntity {

    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private Integer cc;
    @Expose
    private Integer hp;
    @Expose
    private String type;
    @Expose
    private String description;
    @Expose
    private String dealerUrl;
    private List<Image> images = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();


    public Motorcycle() {
    }


    @Column(name = "make", nullable = false)
    public String getMake() {
        return make;
    }

    public Motorcycle setMake(String make) {
        this.make = make;
        return this;
    }

    @Column(name = "model", nullable = false)
    public String getModel() {
        return model;
    }

    public Motorcycle setModel(String model) {
        this.model = model;
        return this;
    }

    @Column(name = "cc", nullable = false)
    public Integer getCc() {
        return cc;
    }

    public Motorcycle setCc(Integer cc) {
        this.cc = cc;
        return this;
    }

    @Column(name = "hp", nullable = false)
    public Integer getHp() {
        return hp;
    }

    public Motorcycle setHp(Integer hp) {
        this.hp = hp;
        return this;
    }

    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }

    public Motorcycle setType(String type) {
        this.type = type;
        return this;
    }

    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Motorcycle setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name="dealer_url", columnDefinition = "TEXT")
    public String getDealerUrl() {
        return dealerUrl;
    }

    public Motorcycle setDealerUrl(String dealerUrl) {
        this.dealerUrl = dealerUrl;
        return this;
    }

    @OneToMany
    public List<Image> getImages() {
        return images;
    }

    public Motorcycle setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    @OneToMany
    public List<Review> getReviews() {
        return reviews;
    }

    public Motorcycle setReviews(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }
}
