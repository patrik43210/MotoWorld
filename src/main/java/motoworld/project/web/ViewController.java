package motoworld.project.web;

import motoworld.project.model.binding.ReviewBindingModel;
import motoworld.project.service.MotorcycleViewService;
import motoworld.project.service.PlaceViewService;
import motoworld.project.service.ReviewService;
import motoworld.project.service.UserService;
import motoworld.project.service.impl.BgImage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("view")
public class ViewController {

    private final MotorcycleViewService motorcycleViewService;
    private final PlaceViewService placeViewService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final BgImage bgImage;

    public ViewController(MotorcycleViewService motorcycleViewService, PlaceViewService placeViewService, ReviewService reviewService, UserService userService, BgImage bgImage) {
        this.motorcycleViewService = motorcycleViewService;
        this.placeViewService = placeViewService;
        this.reviewService = reviewService;
        this.userService = userService;
        this.bgImage = bgImage;
    }


    @GetMapping("/motorcycles")
    public String viewMotorcycles(Model model) {
        model.addAttribute("bgImage",bgImage.getActive());
        return "motorcycles";
    }

    @GetMapping("/motorcycles/details/{id}")
    public String motorcycleDetails(@PathVariable String id, Model model, @AuthenticationPrincipal UserDetails principal) {
        model.addAttribute("id", id);
        model.addAttribute("motorcycle", motorcycleViewService.getByIdModel(id));
        model.addAttribute("reviews", reviewService.getMotorcycleReviews(id));
        model.addAttribute("isBanned", userService.isBanned(principal.getUsername()));
        model.addAttribute("bgImage",bgImage.getActive());


        return "motorcycle-details";
    }

    @GetMapping("/places")
    public String viewPlaces(Model model) {

        model.addAttribute("places", placeViewService.getAllPlaces());
        model.addAttribute("bgImage",bgImage.getActive());

        return "places";
    }


    @GetMapping("/favourite/places")
    public String viewFavouritePlaces(Model model, @AuthenticationPrincipal UserDetails principal) {

        model.addAttribute("places", userService.getAllFavourite(principal.getUsername()));
        model.addAttribute("bgImage",bgImage.getActive());


        return "places";
    }


    @GetMapping("/places/details/{id}")
    public String placeDetails(@PathVariable String id, Model model, @AuthenticationPrincipal UserDetails principal) {
        model.addAttribute("id", id);
        model.addAttribute("isFavourite", userService.isFavourite(id,principal.getUsername()));
        model.addAttribute("place", placeViewService.getByIdModel(id));
        model.addAttribute("reviews", reviewService.getPlaceReviews(id));
        model.addAttribute("isBanned", userService.isBanned(principal.getUsername()));
        model.addAttribute("bgImage",bgImage.getActive());

        return "place-details";
    }


    @GetMapping("/details/review/{id}")
    public String motorcycleReview(@PathVariable String id, Model model,@AuthenticationPrincipal UserDetails principal) {
        if( userService.isBanned(principal.getUsername())){
            return "redirect:/home";
        }
        model.addAttribute("bgImage",bgImage.getActive());
        if (!model.containsAttribute("reviewBindingModel")) {
            model.addAttribute("id", id);
            model.addAttribute("reviewBindingModel", new ReviewBindingModel());
        }
        return "review";
    }


    @PostMapping("/details/review/{id}")
    public String motorcycleReviewPost(@PathVariable("id") String id,
                                       @Valid @ModelAttribute ReviewBindingModel reviewBindingModel,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes,
                                       @AuthenticationPrincipal UserDetails principal
    ) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("reviewBindingModel", reviewBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.reviewBindingModel", bindingResult);

            return "redirect:/view/details/review/{id}";
        }


        String redirect = reviewService.addReviewToMotorcycle(id, reviewBindingModel, principal.getUsername());

        if (redirect.equals("m")) {
            return "redirect:/view/motorcycles/details/{id}";

        } else {
            return "redirect:/view/places/details/{id}";

        }

    }

    @GetMapping("/favourite/{id}")
    public String addToFavourite(@PathVariable("id") String id,
                                 @AuthenticationPrincipal UserDetails principal
                                 ) {
        userService.addFavourite(principal.getUsername(), id);
        return "redirect:/view/places/details/{id}";
    }


    @GetMapping("/favourite/remove/{id}")
    public String removeFromFavourite(@PathVariable("id") String id,
                                 @AuthenticationPrincipal UserDetails principal
    ) {
        userService.removeFavourite(principal.getUsername(), id);
        return "redirect:/view/places/details/{id}";
    }
}
