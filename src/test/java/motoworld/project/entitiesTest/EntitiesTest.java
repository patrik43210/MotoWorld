package motoworld.project.entitiesTest;

import motoworld.project.model.entities.*;
import motoworld.project.model.entities.enums.RoleEnum;
import motoworld.project.repository.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;


class EntitiesTest {



    private ImageRepository imageRepository;
    private MotorcycleRepository motorcycleRepository;
    private PlaceRepository placeRepository;
    private ReviewRepository reviewRepository;
    private SupportRepository supportRepository;
    private UserBikeRepository userBikeRepository;
    private UserRepository userRepository;

    @Before
    public void initRepo(){

        this.imageRepository = Mockito.mock(ImageRepository.class);
        this.motorcycleRepository = Mockito.mock(MotorcycleRepository.class);
        this.placeRepository = Mockito.mock(PlaceRepository.class);
        this.reviewRepository = Mockito.mock(ReviewRepository.class);
        this.supportRepository = Mockito.mock(SupportRepository.class);
        this.userBikeRepository = Mockito.mock(UserBikeRepository.class);
        this.userRepository = Mockito.mock(UserRepository.class);

    }

    private LogEntity setUpLog() {
        return new LogEntity()
                .setAction("something")
                .setMotorcycle(setUpMotorcycle())
                .setUserEntity(setUpUserEntity());
    }

    private UserEntity setUpUserEntity() {
        return new UserEntity().
                setUsername("User").
                setPassword("123").
                setBanned(false).
                setUserInfo(setUpUserInfo()).
                setRoles(List.of(setUpUserRole()));

    }

    private Review setUpReview() {
        return new Review().setReview("Nice");
    }


    private UserInfo setUpUserInfo() {
        return new UserInfo().
                setFullName("User 123").
                setAboutUser("Im user").
                setFavouritePlaces(List.of(setUpPlace())).
                setFromCountry("BG").
                setProfilePicture(setUpImage()).
                setUserBikes(List.of(setUpUserBike()));

    }


    private UserBike setUpUserBike() {
        return new UserBike().
                setImages(List.of(setUpImage())).
                setName("Bike");

    }


    private UserRole setUpUserRole() {
        return new UserRole().setRole(RoleEnum.ADMIN);
    }



    private SupportMessages setUpSuppMessage() {
        return new SupportMessages().
                setSupportRequest("txt").
                setEmail("email@email");
    }


    private Place setUpPlace() {
        Review review = new Review().setReview("Nice");
        Image image = new Image().setImage("Image");
        return new Place().
                setName("Ace").
                setDescription("Nice").
                setLocation("London").
                setPostcode("kt6").
                setReviews(List.of(review)).
                setImages(List.of(image)).
                setGoogleLocationURL("URL");
    }


    private Motorcycle setUpMotorcycle() {
        Review review = new Review().setReview("Nice");
        Image image = new Image().setImage("Image");
        return new Motorcycle().
                setCc(20).
                setDescription("Something").
                setHp(20).
                setMake("Yamaha").
                setModel("R3").
                setType("Sport").
                setReviews(List.of(review)).
                setImages(List.of(image)).
                setDealerUrl("URL");
    }


    private Image setUpImage() {
        return new Image().setImage("Image");
    }



    @Test
    void textCreateImage() {

        Image image = setUpImage();
        Image exImage = new Image().setImage("Image");
        Assert.assertEquals(exImage.getImage(), image.getImage());

    }

    @Test
    void textCreateLog() {

        LogEntity logEntity = setUpLog();

        String action = logEntity.getAction();
        String exAction = "something";
        Assert.assertEquals(exAction, action);


        String username = logEntity.getUserEntity().getUsername();
        String exUsername = "User";
        Assert.assertEquals(exUsername, username);

        String make = logEntity.getMotorcycle().getMake();
        String exMake= "Yamaha";
        Assert.assertEquals(exMake, make);
    }



    @Test
    void textCreateReview() {

        Review review = setUpReview();
        Review exReview = new Review().setReview("Nice");
        Assert.assertEquals(exReview.getReview(), review.getReview());


    }


    @Test
    void textCreateMotorcycleWithCorrectValues() {
        Motorcycle motorcycle = setUpMotorcycle();

        Integer cc = motorcycle.getCc();
        Integer exCC = 20;
        Assert.assertEquals(exCC, cc);

        String description = motorcycle.getDescription();
        String exDescription = "Something";
        Assert.assertEquals(exDescription, description);

        Integer hp = motorcycle.getHp();
        Integer exHP = 20;
        Assert.assertEquals(exHP, hp);

        String make = motorcycle.getMake();
        String exMake = "Yamaha";
        Assert.assertEquals(exMake, make);

        String model = motorcycle.getModel();
        String exModel = "R3";
        Assert.assertEquals(exModel, model);

        String type = motorcycle.getType();
        String exType = "Sport";
        Assert.assertEquals(exType, type);

        Image image = motorcycle.getImages().get(0);
        Image exImage = new Image().setImage("Image");
        Assert.assertEquals(exImage.getImage(), image.getImage());


        Review review = motorcycle.getReviews().get(0);
        Review exReview = new Review().setReview("Nice");
        Assert.assertEquals(exReview.getReview(), review.getReview());


    }





    @Test
    void textCreatePlaceWithCorrectValues() {
        Place place = setUpPlace();

        String name = place.getName();
        String exName = "Ace";
        Assert.assertEquals(exName, name);

        String description = place.getDescription();
        String exDescription = "Nice";
        Assert.assertEquals(exDescription, description);

        String location = place.getLocation();
        String exLocation = "London";
        Assert.assertEquals(exLocation, location);

        String postcode = place.getPostcode();
        String exPostcode = "kt6";
        Assert.assertEquals(exPostcode, postcode);

        String url = place.getGoogleLocationURL();
        String exUrl = "URL";
        Assert.assertEquals(exUrl, url);


        Image image = place.getImages().get(0);
        Image exImage = new Image().setImage("Image");
        Assert.assertEquals(exImage.getImage(), image.getImage());


        Review review = place.getReviews().get(0);
        Review exReview = new Review().setReview("Nice");
        Assert.assertEquals(exReview.getReview(), review.getReview());


    }





    @Test
    void textCreateSupportMessageWithCorrectValues() {
        SupportMessages supportMessages = setUpSuppMessage();

        String message = supportMessages.getSupportRequest();
        String exMessage = "txt";
        Assert.assertEquals(exMessage, message);

        String email = supportMessages.getEmail();
        String exEmail = "email@email";
        Assert.assertEquals(exEmail, email);
    }





    @Test
    void textCreateUserRoles() {

        UserRole userRole = setUpUserRole();

        RoleEnum exRoleEnum = RoleEnum.ADMIN;
        Assert.assertEquals(exRoleEnum, userRole.getRole());
    }




    @Test
    void textCreateUserBike() {

        UserBike userBike = setUpUserBike();

        Image image = userBike.getImages().get(0);
        String exImage = "Image";
        Assert.assertEquals(exImage, image.getImage());

        String name = userBike.getName();
        String exName = "Bike";
        Assert.assertEquals(exName, name);


    }




    @Test
    void textCreateUserInfo() {

        UserInfo userInfo = setUpUserInfo();


        String fullName = userInfo.getFullName();
        String exFullName = "User 123";
        Assert.assertEquals(exFullName, fullName);

        String aboutUser = userInfo.getAboutUser();
        String exAboutUser = "Im user";
        Assert.assertEquals(exAboutUser, aboutUser);


        String favouritePlaces = userInfo.getFavouritePlaces().get(0).getName();
        String exFavouritePlaces = "Ace";
        Assert.assertEquals(exFavouritePlaces, favouritePlaces);

        String from = userInfo.getFromCountry();
        String exFrom = "BG";
        Assert.assertEquals(exFrom, from);

        String img = userInfo.getProfilePicture().getImage();
        String exImage = "Image";
        Assert.assertEquals(exImage, img);

        String userBikeName = userInfo.getUserBikes().get(0).getName();
        String exUserBikeName = "Bike";
        Assert.assertEquals(exUserBikeName, userBikeName);

    }




    @Test
    void textCreateUserEntity() {

        UserEntity userEntity = setUpUserEntity();


        String username = userEntity.getUsername();
        String exUsername = "User";
        Assert.assertEquals(exUsername, username);

        String password = userEntity.getPassword();
        String exPassword = "123";
        Assert.assertEquals(exPassword, password);


        boolean banned = userEntity.isBanned();
        Assert.assertFalse(banned);


        String userFullName = userEntity.getUserInfo().getFullName();
        String exUserFullName = "User 123";
        Assert.assertEquals(exUserFullName, userFullName);

        UserRole userRole = userEntity.getRoles().get(0);
        RoleEnum exRoleEnum = RoleEnum.ADMIN;
        Assert.assertEquals(exRoleEnum, userRole.getRole());

    }



    @Test
    void saveImageWithWrongData() {
       assertThrows(NullPointerException.class, () -> {
            this.imageRepository.save(new Image());
        });
    }


    @Test
    void saveMotorcycleWithWrongData() {
        assertThrows(NullPointerException.class, () -> {
            this.motorcycleRepository.save(new Motorcycle());
        });
    }


    @Test
    void savePlaceWithWrongData() {
        assertThrows(NullPointerException.class, () -> {
            this.placeRepository.save(new Place());
        });
    }

    @Test
    void saveReviewWithWrongData() {
        assertThrows(NullPointerException.class, () -> {
            this.reviewRepository.save(new Review());
        });
    }


    @Test
    void saveSupportWithWrongData() {
        assertThrows(NullPointerException.class, () -> {
            this.supportRepository.save(new SupportMessages());
        });
    }

    @Test
    void saveUserBikeWithWrongData() {
        assertThrows(NullPointerException.class, () -> {
            this.userBikeRepository.save(new UserBike());
        });
    }
    @Test
    void saveUserWithWrongData() {
        assertThrows(NullPointerException.class, () -> {
            this.userRepository.save(new UserEntity());
        });
    }



}
