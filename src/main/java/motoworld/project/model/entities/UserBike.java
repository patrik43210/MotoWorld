package motoworld.project.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_bikes")
public class UserBike extends BaseEntity{


    private String name;

    private List<Image> images = new ArrayList<>();


    public UserBike() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public UserBike setName(String name) {
        this.name = name;
        return this;
    }

    @OneToMany
    public List<Image> getImages() {
        return images;
    }

    public UserBike setImages(List<Image> images) {
        this.images = images;
        return this;
    }
}
