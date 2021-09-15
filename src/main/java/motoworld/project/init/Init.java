package motoworld.project.init;

import com.google.gson.Gson;
import motoworld.project.model.entities.*;
import motoworld.project.model.entities.enums.RoleEnum;
import motoworld.project.repository.*;
import motoworld.project.util.XmlParser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class Init {

    private final MotorcycleRepository motorcycleRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserInfoRepository userInfoRepository;
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;
    private final Gson gson;
    private final XmlParser xmlParser;


    public Init(MotorcycleRepository motorcycleRepository, PlaceRepository placeRepository, UserRepository userRepository,
                UserRoleRepository userRoleRepository,
                UserInfoRepository userInfoRepository,
                ImageRepository imageRepository, PasswordEncoder passwordEncoder,
                Gson gson, XmlParser xmlParser) {

        this.motorcycleRepository = motorcycleRepository;
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userInfoRepository = userInfoRepository;
        this.imageRepository = imageRepository;
        this.passwordEncoder = passwordEncoder;
        this.gson = gson;
        this.xmlParser = xmlParser;
    }


    public void seedAdmin() {
        if (userRepository.count() == 0) {

            UserRole adminRole = new UserRole().setRole(RoleEnum.ADMIN);
            UserRole userRole = new UserRole().setRole(RoleEnum.USER);
            UserRole rootAdmin = new UserRole().setRole(RoleEnum.ROOT_ADMIN);

            userRoleRepository.saveAll(List.of(adminRole, userRole, rootAdmin));

            UserInfo userInfo = new UserInfo("Admin Admin");

            userInfoRepository.save(userInfo);

            UserEntity admin = new UserEntity().setUsername("patrik43210").setUserInfo(userInfo).setPassword(passwordEncoder.encode("zFtzZtSbKTlU82oO92hI"));
            admin.setRoles(List.of(adminRole, userRole, rootAdmin));

            userRepository.save(admin);
        }
    }

    public void seedMotorcycle() {
        if (motorcycleRepository.count() == 0) {
            try {
                Motorcycle[] motorcycles =
                        gson.fromJson(readAuthorsFileContent(), Motorcycle[].class);

                Image image = new Image("https://dd5394a0b8ca8e97ba29-abf76f3d91a2125517d6c7c409f095c7.ssl.cf1.rackcdn.com/content/common/Models/2021/add55dc8-dd75-4539-8d8d-61ad2ada8adb.png");
                imageRepository.save(image);
                motorcycles[0].getImages().add(image);
                Arrays.stream(motorcycles).
                        forEach(motorcycleRepository::save);
            } catch (IOException e) {
                throw new IllegalStateException("Cannot seed:(");
            }
        }

    }


    public void seedPlace() throws JAXBException {
        String PLACE_PATH = "src/main/resources/init/places.xml";

        if (placeRepository.count() == 0) {
            Place place = this.xmlParser.parseXml(Place.class, PLACE_PATH);
            String url = "https://www.google.com/maps?client=opera&q=ace+cafe+london&oe=UTF-8&um=1&ie=UTF-8";
            Image image = new Image("https://media-exp1.licdn.com/dms/image/C4D1BAQFttGpXLsAU4A/company-background_10000/0/1543237878818?e=2159024400&v=beta&t=-eLpOSoDm45rIVO6h8MvvPftWjtSaTuoPCCYfO5ZZfM");
            imageRepository.save(image);

            place.setImages(List.of(image));
            place.setGoogleLocationURL(url);
            placeRepository.save(place);
        }

    }

    public String readAuthorsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of("src/main/resources/init/motorcycle.json")));
    }
}
