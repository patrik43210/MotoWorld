package motoworld.project.web.rest;


import motoworld.project.model.service.Users;
import motoworld.project.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AllUsersRestController {

    private final UserService userService;

    public AllUsersRestController( UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/users")
    public List<Users> getAll() {
     return userService.findAllUsers();
    }




}
