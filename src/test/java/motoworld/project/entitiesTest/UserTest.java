package motoworld.project.entitiesTest;


import motoworld.project.model.entities.Place;
import motoworld.project.model.entities.UserEntity;
import motoworld.project.model.entities.UserInfo;
import motoworld.project.model.entities.UserRole;
import motoworld.project.model.entities.enums.RoleEnum;
import motoworld.project.model.service.UserRegistrationServiceModel;
import motoworld.project.model.service.Users;
import motoworld.project.repository.UserBikeRepository;
import motoworld.project.repository.UserRepository;
import motoworld.project.repository.UserRoleRepository;
import motoworld.project.security.SecurityUserDetails;
import motoworld.project.service.ImageService;
import motoworld.project.service.PlaceViewService;
import motoworld.project.service.UserInfoService;
import motoworld.project.service.UserService;
import motoworld.project.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;



@ExtendWith(MockitoExtension.class)
public class UserTest {


    private UserService userServiceToTest;

    @Mock
    UserRepository userRepository;
    @Mock
    UserRoleRepository userRoleRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    SecurityUserDetails securityUserDetails;
    @Mock
    ImageService imageService;
    @Mock
    UserInfoService userInfoService;
    @Mock
    PlaceViewService placeViewService;
    @Mock
    UserBikeRepository userBikeRepository;

    UserEntity userEntity;
    UserInfo userInfo;
    UserRole roleUser;
    UserRole roleAdmin;
    Place place;
    @Mock
    ModelMapper modelMapper;


    @BeforeEach
    public void setUp() {
        userServiceToTest = new UserServiceImpl(userRoleRepository, userRepository, passwordEncoder,
                modelMapper, securityUserDetails, imageService,
                userInfoService, placeViewService, userBikeRepository);


        userEntity = new UserEntity();
        userInfo = new UserInfo();
        userInfo.setFullName("user user");
        userEntity.setUsername("user").setPassword("12321").setUserInfo(userInfo)
                .setId("1");


        roleUser = new UserRole();
        roleUser.setRole(RoleEnum.USER).setId("1");
        roleAdmin = new UserRole();
        roleAdmin.setRole(RoleEnum.ADMIN).setId("2");

        userEntity.setRoles(List.of(roleUser, roleAdmin));

        place = new Place().setName("place").setLocation("KT3").setDescription("placeD");


        Mockito.when(userRepository.findById(userEntity.getId()))
                .thenReturn(Optional.of(userEntity));

        Mockito.when(userRepository.save(userEntity))
                .thenReturn(userEntity);





    }

    @AfterEach
    public void reset() {
        Mockito.reset(userRepository);
    }

    @Test
    void testRegistration(){
        UserRegistrationServiceModel user  = new UserRegistrationServiceModel();
        user.setFullName(userInfo.getFullName())
        .setPassword("12321")
        .setUsername(userEntity.getUsername());

        userServiceToTest.registerAndLoginUser(user);
        Mockito.verify(userRepository, Mockito.times(1));
    }




    @Test
    void testUser() {

        Mockito.when(userRepository.findByUsername("user")).thenReturn(Optional.of(userEntity));

        boolean exist = userServiceToTest.userNameExists("user");
        Assertions.assertTrue(exist);

        Optional<UserEntity> user = userServiceToTest.findByUsername("user");
        Assertions.assertEquals(userEntity.getUsername(), user.get().getUsername());


        Mockito.when(userRepository.findById("1")).thenReturn(Optional.of(userEntity));
        userServiceToTest.banUser("1");
        Assertions.assertTrue(userEntity.isBanned());
        userServiceToTest.unbanUser("1");
        Assertions.assertFalse(userEntity.isBanned());

        Mockito.when(userRepository.findAll()).thenReturn(List.of(userEntity));
        List<Users> all = userServiceToTest.findAllUsers();
        Assertions.assertEquals(1, all.size());


        boolean t = userServiceToTest.isOwner("user", "user");
        Assertions.assertTrue(t);


        Place place = (Place) new Place().setId("1");
        userEntity.getUserInfo().setFavouritePlaces(List.of(place));
        Mockito.when(placeViewService
                .findById("1")).thenReturn(Optional.of(place));

        Assertions.assertTrue(userServiceToTest.isFavourite("1", userEntity.getUsername()));



    }
}
