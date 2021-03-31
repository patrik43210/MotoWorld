package motoworld.project.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class Image extends BaseEntity{

    private String image;

    public Image(String image) {
        this.image = image;
    }

    public Image() {

    }

    @Column(columnDefinition = "TEXT")
    public String getImage() {
        return image;
    }

    public Image setImage(String image) {
        this.image = image;
        return this;
    }
}
