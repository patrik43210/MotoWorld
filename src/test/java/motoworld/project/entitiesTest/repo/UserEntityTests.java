package motoworld.project.entitiesTest.repo;

import motoworld.project.model.entities.UserEntity;
import motoworld.project.model.entities.UserInfo;
import motoworld.project.model.entities.UserRole;
import motoworld.project.model.entities.enums.RoleEnum;
import motoworld.project.repository.UserInfoRepository;
import motoworld.project.repository.UserRepository;
import motoworld.project.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserEntityTests {

    private static final String TEST_STRING = "STRING";

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @BeforeEach
    public void setUp() {
        UserEntity userEntity = new UserEntity();
        UserInfo userInfo = new UserInfo();
        UserRole roleUser = new UserRole();
        UserRole roleAdmin = new UserRole();

        userEntity = new UserEntity();
        userInfo = new UserInfo();
        userInfo.setFullName(TEST_STRING);
        userEntity.setUsername(TEST_STRING).setPassword(TEST_STRING).setUserInfo(userInfo);


        roleUser = new UserRole();
        roleUser.setRole(RoleEnum.USER);
        roleAdmin = new UserRole();
        roleAdmin.setRole(RoleEnum.ADMIN);

        userEntity.setRoles(List.of(roleUser, roleAdmin));

        userInfoRepository.saveAndFlush(userInfo);

        userRoleRepository.saveAll(List.of(roleAdmin, roleUser));

        userRepository.saveAndFlush(userEntity);

    }

    @AfterEach
    public void tearAll() {
        userRepository.deleteAll();
    }


    @Test
    public void findByUsername() {
        Optional<UserEntity> actual = userRepository.findByUsername("STRING");
        Optional<UserEntity> actualNotPresent = userRepository.findByUsername(null);


        assertEquals("STRING", actual.get().getUsername());
        assertFalse(actualNotPresent.isPresent());
    }


    @Test
    public void testUserInfo() {
        Optional<UserEntity> actual = userRepository.findByUsername("STRING");

        assertEquals("STRING", actual.get().getUserInfo().getFullName());
    }

    @Test
    public void testRoles() {

        Optional<UserRole> actualByRole = userRoleRepository.findByRole(RoleEnum.ADMIN);
        Optional<UserRole> actualByRoleNotExist = userRoleRepository.findByRole(RoleEnum.ROOT_ADMIN);

        assertEquals(RoleEnum.ADMIN, actualByRole.get().getRole());
        assertFalse(actualByRoleNotExist.isPresent());

    }

}

