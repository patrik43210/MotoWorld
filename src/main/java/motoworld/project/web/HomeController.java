package motoworld.project.web;

import motoworld.project.model.binding.SupportRequestBindingModel;
import motoworld.project.service.LogService;
import motoworld.project.service.SupportService;
import motoworld.project.service.impl.BgImage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class HomeController {


    private final SupportService supportService;
    private final BgImage bgImage;
    private final LogService logService;



    public HomeController(SupportService supportService, BgImage bgImage, LogService logService) {
        this.supportService = supportService;

        this.bgImage = bgImage;
        this.logService = logService;
    }

//    @GetMapping("/")
//    public String index(@AuthenticationPrincipal UserDetails principal, Model model) {
//        if (principal == null) {
//            model.addAttribute("img","images/welcome.jpg");
//            return "index";
//        }
//        return "redirect:/home";
//    }

    @GetMapping("/")
    public String index() {
        return "Hello";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("bgImage",bgImage.getActive());
        model.addAttribute("motorcycle",logService.findMostViewed());
        return "home";
    }


    @GetMapping("/support")
    public String supportMessages(Model model) {

        model.addAttribute("bgImage",bgImage.getActive());

        if (!model.containsAttribute("supportRequestBindingModel")) {
            model.addAttribute("supportRequestBindingModel", new SupportRequestBindingModel());
        }

        return "support";
    }

    @PostMapping("/support")
    public String supportMessagesConfirm(@Valid @ModelAttribute SupportRequestBindingModel supportRequestBindingModel,
                                         BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("supportRequestBindingModel", supportRequestBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.supportRequestBindingModel", bindingResult);

            return "redirect:support";
        }
        supportService.addMessage(supportRequestBindingModel);

        return "redirect:/home";
    }


}
