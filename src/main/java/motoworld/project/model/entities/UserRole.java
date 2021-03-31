package motoworld.project.model.entities;


import motoworld.project.model.entities.enums.RoleEnum;

import javax.persistence.*;

@Table
@Entity(name = "roles")
public class UserRole extends BaseEntity {

    private RoleEnum role;

    public UserRole() {
    }

    public UserRole(RoleEnum role) {
        this.role = role;
    }

    @Enumerated(EnumType.STRING)
    public RoleEnum getRole() {
        return role;
    }

    public UserRole setRole(RoleEnum role) {
        this.role = role;
        return this;
    }
}
