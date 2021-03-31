package motoworld.project.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class Review extends BaseEntity {

    private UserEntity user;
    private String review;

    public Review() {
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public Review setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getReview() {
        return review;
    }

    public Review setReview(String review) {
        this.review = review;
        return this;
    }
}
