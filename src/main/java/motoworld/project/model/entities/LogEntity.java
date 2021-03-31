package motoworld.project.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "logs")
public class LogEntity extends BaseEntity {

    private UserEntity userEntity;
    private String action;
    private Motorcycle motorcycle;

    public LogEntity() {
    }

    @ManyToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public LogEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    @Column(name = "action", nullable = false)
    public String getAction() {
        return action;
    }

    public LogEntity setAction(String action) {
        this.action = action;
        return this;
    }

    @ManyToOne
    public Motorcycle getMotorcycle() {
        return motorcycle;
    }

    public LogEntity setMotorcycle(Motorcycle motorcycle) {
        this.motorcycle = motorcycle;
        return this;
    }

}
