package motoworld.project.web;



import motoworld.project.service.SupportService;
import motoworld.project.service.UserService;
import motoworld.project.service.impl.BgImage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final SupportService supportService;
    private final BgImage bgImage;


    public AdminController(UserService userService, SupportService supportService, BgImage bgImage) {
        this.userService = userService;
        this.supportService = supportService;

        this.bgImage = bgImage;
    }


    @PreAuthorize("hasAnyRole('ROLE_ROOT_ADMIN')")
    @GetMapping("/all-users")
    public String allUsers(Model model){

        model.addAttribute("users",userService.findAll());
        model.addAttribute("bgImage",bgImage.getActive());

        return "all-users";
    }

    @PreAuthorize("hasAnyRole('ROLE_ROOT_ADMIN')")
    @GetMapping("/ban/user/{id}")
    public String banUser(@PathVariable("id") String id){
        userService.banUser(id);
        return "redirect:/admin/all-users";
    }

    @PreAuthorize("hasAnyRole('ROLE_ROOT_ADMIN')")
    @GetMapping("/unban/user/{id}")
    public String unbanUser(@PathVariable("id") String id){
        userService.unbanUser(id);
        return "redirect:/admin/all-users";
    }

    @PreAuthorize("hasAnyRole('ROLE_ROOT_ADMIN')")
    @GetMapping("/promote/user/{id}")
    public String promoteUser(@PathVariable("id") String id){
        userService.promoteUser(id);
        return "redirect:/admin/all-users";
    }

    @PreAuthorize("hasAnyRole('ROLE_ROOT_ADMIN')")
    @GetMapping("/demote/user/{id}")
    public String demoteUser(@PathVariable("id") String id){
        userService.demoteUser(id);
        return "redirect:/admin/all-users";
    }





    @GetMapping("/all-messages")
    public String allMessages(Model model){

        model.addAttribute("messages",supportService.findAll());
        model.addAttribute("bgImage",bgImage.getActive());

        return "support-messages";
    }

    @GetMapping("/delete/message/{id}")
    public String deleteMessage(@PathVariable("id") String id){

        supportService.removeMessage(id);

        return "redirect:/admin/all-messages";
    }

}
