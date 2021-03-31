package motoworld.project.service.impl;

import motoworld.project.model.binding.AddUserBikeBindingModel;
import motoworld.project.model.binding.ProfileBindingModel;
import motoworld.project.model.entities.*;
import motoworld.project.model.entities.enums.RoleEnum;
import motoworld.project.model.service.*;
import motoworld.project.repository.UserBikeRepository;
import motoworld.project.repository.UserRepository;
import motoworld.project.repository.UserRoleRepository;
import motoworld.project.security.SecurityUserDetails;
import motoworld.project.service.ImageService;
import motoworld.project.service.PlaceViewService;
import motoworld.project.service.UserInfoService;
import motoworld.project.service.UserService;
import org.modelmapper.ModelMapper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final SecurityUserDetails securityUserDetails;
    private final ImageService imageService;
    private final UserInfoService userInfoService;
    private final PlaceViewService placeViewService;
    private final UserBikeRepository userBikeRepository;


    public UserServiceImpl(UserRoleRepository userRoleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper,
                           SecurityUserDetails securityUserDetails,
                           ImageService imageService,
                           UserInfoService userInfoService,
                           PlaceViewService placeViewService,
                           UserBikeRepository userBikeRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.securityUserDetails = securityUserDetails;
        this.imageService = imageService;
        this.userInfoService = userInfoService;
        this.placeViewService = placeViewService;
        this.userBikeRepository = userBikeRepository;


    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel serviceModel) {
        UserEntity newUser = modelMapper.map(serviceModel, UserEntity.class);
        UserInfo userInfo = modelMapper.map(serviceModel, UserInfo.class);

        newUser.setPassword(passwordEncoder.encode(serviceModel.getPassword()));

        UserRole userRole = userRoleRepository.
                findByRole(RoleEnum.USER).orElseThrow(
                () -> new IllegalStateException("USER role not found. Please seed the roles."));

        newUser.addRole(userRole);

        userInfoService.save(userInfo);
        newUser.setUserInfo(userInfo);

        newUser = userRepository.save(newUser);

        UserDetails principal = securityUserDetails.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public boolean isBanned(String username) {
        return userRepository.findByUsername(username).get().isBanned();
    }

    @Override
    public List<AllUsersModel> findAll() {
        List<AllUsersModel> usersModels = new ArrayList<>();
        List<UserEntity> users = userRepository.findAll();

        for (UserEntity user : users) {
            AllUsersModel userModel = modelMapper.map(user, AllUsersModel.class);
            UserRole userRole = userRoleRepository.findByRole(RoleEnum.ADMIN).orElse(null);
            userModel.setAdmin(user.getRoles().contains(userRole));
            usersModels.add(userModel);
        }

        return usersModels;
    }

    @Override
    public boolean userNameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }


    @Override
    public void banUser(String id) {
        UserEntity user = userRepository.findById(id).get();
        user.setBanned(true);
        userRepository.save(user);
    }

    @Override
    public void unbanUser(String id) {
        UserEntity user = userRepository.findById(id).get();
        user.setBanned(false);
        userRepository.save(user);
    }

    @Override
    public void promoteUser(String id) {
        UserEntity user = userRepository.findById(id).get();
        UserRole role = userRoleRepository.findByRole(RoleEnum.ADMIN).get();
        user.addRole(role);
        userRepository.save(user);
    }

    @Override
    public void demoteUser(String id) {
        UserEntity user = userRepository.findById(id).get();
        UserRole role = userRoleRepository.findByRole(RoleEnum.ADMIN).get();
        user.getRoles().remove(role);
        userRepository.save(user);
    }

    @Override
    public void addFavourite(String username, String placeId) {

        UserInfo userInfo = getUserInfo(username);
        userInfo.getFavouritePlaces()
                .add(placeViewService
                        .findById(placeId).get());
        userInfoService.save(userInfo);
    }

    @Override
    public void removeFavourite(String username, String placeId) {

        UserInfo userInfo = getUserInfo(username);
        userInfo.getFavouritePlaces()
                .remove(placeViewService
                        .findById(placeId).get());
        userInfoService.save(userInfo);
    }

    @Override
    public boolean isFavourite(String placeId, String username) {

        return getUserInfo(username)
                .getFavouritePlaces()
                .contains(placeViewService
                        .findById(placeId).get());
    }

    @Override
    public void addUserBike(String username, AddUserBikeBindingModel addUserBikeBindingModel) throws IOException {

        UserBikeServiceModel userBikeServiceModel = modelMapper.map(addUserBikeBindingModel, UserBikeServiceModel.class);
        UserBike userBike = modelMapper.map(userBikeServiceModel, UserBike.class);

        List<Image> images = new ArrayList<>();


        if (isNotNullFile(addUserBikeBindingModel.getImageUrl1())) {
            images.add(imageService.save(addUserBikeBindingModel.getImageUrl1()));
        }

        if (isNotNullFile(addUserBikeBindingModel.getImageUrl2())) {
            images.add(imageService.save(addUserBikeBindingModel.getImageUrl2()));
        }

        userBike.setImages(images);

        userBikeRepository.save(userBike);

        UserInfo userInfo = getUserInfo(username);
        userInfo.getUserBikes().add(userBike);
        userInfoService.save(userInfo);

    }

    @Override
    public List<UserBikeServiceModel> getUserBikes(String username) {

        UserInfo userInfo = getUserInfo(username);
        List<UserBikeServiceModel> bikes = new ArrayList<>();

        for (UserBike userBike : userInfo.getUserBikes()) {
            UserBikeServiceModel bike = modelMapper.map(userBike, UserBikeServiceModel.class);
            bikes.add(bike);
        }

        return bikes;
    }

    @Override
    public List<Users> findAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        List<Users> model = new ArrayList<>();
        for (UserEntity user : users) {
            if (!user.getUsername().equals("admin")) {
                Users u = modelMapper.map(user, Users.class);
                model.add(u);
            }
        }
        return model;
    }

    private UserInfo getUserInfo(String username) {

        return userRepository
                .findByUsername(username)
                .get()
                .getUserInfo();
    }


    @Override
    public List<PlaceServiceModel> getAllFavourite(String username) {

        UserEntity user = userRepository.findByUsername(username).get();

        List<PlaceServiceModel> placesModels = new ArrayList<>();
        List<Place> places = user.getUserInfo().getFavouritePlaces();

        for (Place place : places) {
            placesModels.add(modelMapper.map(place, PlaceServiceModel.class));
        }

        return placesModels;
    }


    @Override
    public ProfileServiceModel findByUsernameModel(String username) {


        UserEntity user = userRepository.findByUsername(username).get();
        UserInfo userInfo = user.getUserInfo();
        return modelMapper.map(userInfo, ProfileServiceModel.class);
    }

    @Override
    public void updateUser(ProfileBindingModel profileBindingModel, String username) {


        UserInfo userInfo = userRepository.findByUsername(username).get().getUserInfo();

        if (isNotNull(profileBindingModel.getProfilePicture())) {
            Image image = new Image(profileBindingModel.getProfilePicture());

            imageService.saveUserProfile(image);
            userInfo.setProfilePicture(image);
        }
        userInfo.setFullName(profileBindingModel.getFullName());


        if (!profileBindingModel.getAboutUser().isEmpty())
            userInfo.setAboutUser(profileBindingModel.getAboutUser());

        if (!profileBindingModel.getFromCountry().isEmpty())
            userInfo.setFromCountry(profileBindingModel.getFromCountry());

        userInfoService.save(userInfo);

    }

    @Override
    public boolean isOwner(String visitorUsername, String ownerUsername) {
        String visitorId = userRepository.findByUsername(visitorUsername).get().getId();
        String ownerId = userRepository.findByUsername(ownerUsername).get().getId();
        return visitorId.equals(ownerId);
    }


    private boolean isNotNull(String str) {
        return str != null;
    }

    private boolean isNotNullFile(MultipartFile file) {
        return file != null;
    }


}
