package motoworld.project.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String username;

    private String password;

    private List<UserRole> roles = new ArrayList<>();

    private boolean isBanned = false;

    private UserInfo userInfo;

    public UserEntity() {
    }


    public UserEntity(String username, String password, List<UserRole> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Column(unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<UserRole> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRole> roles) {
        this.roles = roles;
        return this;
    }

    @Column(name = "is_banned")
    public boolean isBanned() {
        return isBanned;
    }

    public UserEntity setBanned(boolean banned) {
        isBanned = banned;
        return this;
    }

    @OneToOne
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public UserEntity setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        return this;
    }


    public UserEntity addRole(UserRole roleEntity) {
        this.roles.add(roleEntity);
        return this;
    }
}
