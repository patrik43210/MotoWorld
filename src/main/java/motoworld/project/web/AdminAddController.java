package motoworld.project.web;


import motoworld.project.model.binding.AdminAddMotorcycleBindingModel;
import motoworld.project.model.binding.AdminAddPlaceBindingModel;
import motoworld.project.service.AdminMotorcycleService;
import motoworld.project.service.AdminPlaceService;
import motoworld.project.service.impl.BgImage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/admin/add")
public class AdminAddController {


    private final AdminMotorcycleService adminMotorcycleService;
    private final AdminPlaceService adminPlaceService;
    private final BgImage bgImage;

    public AdminAddController(AdminMotorcycleService adminMotorcycleService, AdminPlaceService adminPlaceService, BgImage bgImage) {

        this.adminMotorcycleService = adminMotorcycleService;
        this.adminPlaceService = adminPlaceService;
        this.bgImage = bgImage;
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/motorcycle")
    public String addMotorcycle(Model model) {
        model.addAttribute("bgImage",bgImage.getActive());

        if (!model.containsAttribute("adminAddMotorcycleBindingModel")) {
            model.addAttribute("adminAddMotorcycleBindingModel", new AdminAddMotorcycleBindingModel());
            model.addAttribute("bgImage",bgImage.getActive());
            model.addAttribute("putJpg", false);
        }

        return "admin-add-motorcycle";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/motorcycle")
    public String addMotorcycleConfirm(@Valid AdminAddMotorcycleBindingModel adminAddMotorcycleBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes,
                                       @RequestParam(name = "file1", required = false) MultipartFile file1,
                                       @RequestParam(name = "file2", required = false) MultipartFile file2,
                                       @RequestParam(name = "file3", required = false) MultipartFile file3) throws IOException {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("adminAddMotorcycleBindingModel", adminAddMotorcycleBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.adminAddMotorcycleBindingModel", bindingResult);

            return "redirect:motorcycle";
        }
        boolean ex = false;

        if (checkFile(file1)) {
            if (match(file1)) {
                adminAddMotorcycleBindingModel.setImageUrl1(file1);
            } else {
                redirectAttributes.addFlashAttribute("putJpg", true);
                ex = true;
            }
        }


        if (checkFile(file2)) {
            if (match(file2)) {
                adminAddMotorcycleBindingModel.setImageUrl2(file2);
            } else {
                redirectAttributes.addFlashAttribute("putJpg", true);
                ex = true;
            }
        }


        if (checkFile(file3)) {
            if (match(file3)) {
                adminAddMotorcycleBindingModel.setImageUrl3(file3);
            }
            else {
                redirectAttributes.addFlashAttribute("putJpg", true);
                ex = true;
            }
        }

        if (ex || (Objects.equals(file1.getOriginalFilename(), "") && Objects.equals(file2.getOriginalFilename(), "") && Objects.equals(file3.getOriginalFilename(), ""))) {
            redirectAttributes.addFlashAttribute("adminAddMotorcycleBindingModel", adminAddMotorcycleBindingModel);
            redirectAttributes.addFlashAttribute("putJpg", true);
            return "redirect:motorcycle";
        }


        adminMotorcycleService.add(adminAddMotorcycleBindingModel);
        return "redirect:/home";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/place")
    public String addPlace(Model model) {


        model.addAttribute("putJpg", false);
        model.addAttribute("bgImage", bgImage.getActive());

        if (!model.containsAttribute("adminAddPlaceBindingModel")) {
            model.addAttribute("adminAddPlaceBindingModel", new AdminAddPlaceBindingModel());
            model.addAttribute("bgImage", bgImage.getActive());
            model.addAttribute("locationExist", false);
            model.addAttribute("postcodeExist", false);

        }
        return "admin-add-place";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/place")
    public String addPlaceConfirm(@Valid AdminAddPlaceBindingModel adminAddPlaceBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  @RequestParam(name = "file1", required = false) MultipartFile file1,
                                  @RequestParam(name = "file2", required = false) MultipartFile file2,
                                  @RequestParam(name = "file3", required = false) MultipartFile file3) throws IOException {


        boolean ex = false;
        if (bindingResult.hasErrors()) {
            ex = true;
        }


        if (adminPlaceService.checkLocation(adminAddPlaceBindingModel.getLocation())) {
            redirectAttributes.addFlashAttribute("locationExist", true);

            ex = true;
        }

        if (adminPlaceService.checkPostcode(adminAddPlaceBindingModel.getPostcode())) {
            redirectAttributes.addFlashAttribute("postcodeExist", true);

            ex = true;
        }



        if (ex) {
            redirectAttributes.addFlashAttribute("adminAddPlaceBindingModel", adminAddPlaceBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.adminAddPlaceBindingModel", bindingResult);
            return "redirect:place";
        }


        boolean exF = false;

        if (checkFile(file1)) {
            if (match(file1)) {
                adminAddPlaceBindingModel.setImageUrl1(file1);
            } else {
                redirectAttributes.addFlashAttribute("putJpg", true);
                exF = true;
            }
        }

        if (checkFile(file2)) {
            if (match(file2)) {
                adminAddPlaceBindingModel.setImageUrl2(file2);
            } else {
                redirectAttributes.addFlashAttribute("putJpg", true);
                exF = true;
            }
        }

        if (checkFile(file3)) {
            if (match(file3)) {
                adminAddPlaceBindingModel.setImageUrl3(file3);
            } else {
                redirectAttributes.addFlashAttribute("putJpg", true);
                exF = true;
            }
        }

        if(exF || (Objects.equals(file1.getOriginalFilename(), "") && Objects.equals(file2.getOriginalFilename(), "") && Objects.equals(file3.getOriginalFilename(), ""))){
            redirectAttributes.addFlashAttribute("adminAddPlaceBindingModel", adminAddPlaceBindingModel);
            redirectAttributes.addFlashAttribute("putJpg", true);

            return "redirect:place";
        }


        adminPlaceService.add(adminAddPlaceBindingModel);
        return "redirect:/home";
    }






    private boolean checkFile(MultipartFile file) {
        return file != null && !file.isEmpty() && file.getOriginalFilename().length() > 0;
    }

    private boolean match(MultipartFile file) {
        return Pattern.matches(".+\\.(jpg|png|jpeg)", file.getOriginalFilename());
    }
}
