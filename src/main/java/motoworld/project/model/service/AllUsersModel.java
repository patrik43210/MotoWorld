package motoworld.project.model.service;

public class AllUsersModel extends BaseServiceModel{

    private String username;
    private boolean isBanned;
    private boolean isAdmin;


    public AllUsersModel() {
    }

    public boolean isBanned() {
        return isBanned;
    }

    public AllUsersModel setBanned(boolean banned) {
        isBanned = banned;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AllUsersModel setUsername(String username) {
        this.username = username;
        return this;
    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public AllUsersModel setAdmin(boolean admin) {
        isAdmin = admin;
        return this;
    }
}
