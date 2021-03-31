package motoworld.project.web;


import motoworld.project.model.binding.AddUserBikeBindingModel;
import motoworld.project.model.binding.ProfileBindingModel;
import motoworld.project.model.service.ProfileServiceModel;
import motoworld.project.service.UserService;
import motoworld.project.service.impl.BgImage;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    public static final String UPLOAD_DIR = "uploads";
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final BgImage bgImage;


    public ProfileController(UserService userService, ModelMapper modelMapper, BgImage bgImage) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.bgImage = bgImage;
    }


    @GetMapping("/{username}")
    public String profile(Model model, @PathVariable String username, @AuthenticationPrincipal UserDetails principal) {
        model.addAttribute("bgImage", bgImage.getActive());
        ProfileServiceModel profile = userService.findByUsernameModel(username);
        model.addAttribute("profile", profile);
        model.addAttribute("bikes", userService.getUserBikes(username));
        model.addAttribute("isBanned", userService.isBanned(principal.getUsername()));
        model.addAttribute("isOwner", userService.isOwner(principal.getUsername(), username));


        return "profile";
    }

    @GetMapping("/edit/{username}")
    public String editProfile(@PathVariable String username, Model model, @AuthenticationPrincipal UserDetails principal) {
        if (!userService.isOwner(principal.getUsername(), username)) {
            return "redirect:/home";
        }
        model.addAttribute("bgImage", bgImage.getActive());

        if (!model.containsAttribute("profile")) {
            model.addAttribute("profile", modelMapper.map(userService.findByUsernameModel(username), ProfileBindingModel.class));
            model.addAttribute("putJpg", false);

        }

        return "edit-user-profile";
    }


    @PostMapping("/edit/{username}")
    public String editProfileConfirm(@Valid @ModelAttribute ProfileBindingModel profileBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes,
                                     @PathVariable String username,
                                     @RequestParam(name = "file", required = false) MultipartFile file) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("profile", profileBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.profile", bindingResult);

            return "redirect:/profile/edit/{username}";
        }

        if (checkFile(file)) {
            if (match(file)) {
                handleMultipartFile(file);
                profileBindingModel.setProfilePicture("/" + UPLOAD_DIR + "/" + file.getOriginalFilename());
            } else {
                redirectAttributes.addFlashAttribute("putJpg", true);
                redirectAttributes.addFlashAttribute("profile", profileBindingModel);
                return "redirect:/profile/edit/{username}";
            }
        }


        userService.updateUser(profileBindingModel, username);

        return "redirect:/profile/{username}";
    }


    @GetMapping("/add-bike/{username}")
    public String addUserBike(@PathVariable String username, Model model, @AuthenticationPrincipal UserDetails principal) {
        if (!userService.isOwner(principal.getUsername(), username) || userService.isBanned(principal.getUsername())) {
            return "redirect:/home";
        }
        model.addAttribute("bgImage", bgImage.getActive());

        if (!model.containsAttribute("addUserBikeBindingModel")) {
            model.addAttribute("addUserBikeBindingModel", new AddUserBikeBindingModel());
            model.addAttribute("username", username);
            model.addAttribute("putJpg", false);

        }
        return "add-user-bike";
    }


    @PostMapping("/add-bike/{username}")
    public String addUserBikeConfirm(@PathVariable String username,
                                     @Valid AddUserBikeBindingModel addUserBikeBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes,
                                     @RequestParam(name = "file1", required = false) MultipartFile file1,
                                     @RequestParam(name = "file2", required = false) MultipartFile file2) throws IOException {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addUserBikeBindingModel", addUserBikeBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addUserBikeBindingModel", bindingResult);
            return "redirect:/profile/add-bike/{username}";
        }

        boolean exF = false;

        if (checkFile(file1)) {
            if (match(file1)) {
                addUserBikeBindingModel.setImageUrl1(file1);
            } else {
                redirectAttributes.addFlashAttribute("putJpg", true);
                exF = true;
            }
        }

        if (checkFile(file2)) {
            if (match(file2)) {
                addUserBikeBindingModel.setImageUrl2(file2);
            } else {
                redirectAttributes.addFlashAttribute("putJpg", true);
                exF = true;
            }
        }

        if (exF || (Objects.equals(file1.getOriginalFilename(), "") && Objects.equals(file2.getOriginalFilename(), ""))) {
            redirectAttributes.addFlashAttribute("addUserBikeBindingModel", addUserBikeBindingModel);
            redirectAttributes.addFlashAttribute("putJpg", true);

            return "redirect:/profile/add-bike/{username}";
        }


        userService.addUserBike(username, addUserBikeBindingModel);


        return "redirect:/profile/{username}";
    }


    private void handleMultipartFile(MultipartFile file) {
        try {
            File currentDir = new File(UPLOAD_DIR);
            if (!currentDir.exists()) {
                currentDir.mkdirs();
            }
            String path = currentDir.getAbsolutePath() + "/" + file.getOriginalFilename();
            path = new File(path).getAbsolutePath();
            File f = new File(path);
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(f));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private boolean match(MultipartFile file) {
        return Pattern.matches(".+\\.(jpg|png|jpeg)", file.getOriginalFilename());
    }


    private boolean checkFile(MultipartFile file) {
        return file != null && !file.isEmpty() && file.getOriginalFilename().length() > 0;
    }
}
