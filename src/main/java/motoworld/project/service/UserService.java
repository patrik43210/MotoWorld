package motoworld.project.service;

import motoworld.project.model.binding.AddUserBikeBindingModel;
import motoworld.project.model.binding.ProfileBindingModel;
import motoworld.project.model.entities.UserEntity;
import motoworld.project.model.service.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean userNameExists(String username);

    void registerAndLoginUser(UserRegistrationServiceModel serviceModel);

    Optional<UserEntity> findByUsername(String username);


    boolean isBanned(String username);

    List<AllUsersModel> findAll();



    ProfileServiceModel findByUsernameModel(String username);


    boolean isOwner(String visitorUsername, String ownerUsername);


    void updateUser(ProfileBindingModel profileBindingModel, String id);

    void banUser(String id);

    void unbanUser(String id);

    void promoteUser(String id);

    void demoteUser(String id);

    void addFavourite(String username, String placeId);

    List<PlaceServiceModel> getAllFavourite(String username);

    void removeFavourite(String username, String id);

    boolean isFavourite(String placeId, String username);

    void addUserBike(String username, AddUserBikeBindingModel addUserBikeBindingModel)throws IOException;

    List<UserBikeServiceModel> getUserBikes(String username);


    List<Users> findAllUsers();
}

