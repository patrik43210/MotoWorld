package motoworld.project.web;

import motoworld.project.service.impl.BgImage;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DBErrorController implements ErrorController {

  private final BgImage bgImage;

  public DBErrorController(BgImage bgImage) {
    this.bgImage = bgImage;
  }

  @RequestMapping("/error")
  public String handleError(HttpServletRequest request, Model model) {

    model.addAttribute("bgImage",bgImage.getActive());
    Object status =
        request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    if (status != null) {
      Integer statusCode = Integer.parseInt(status.toString());

      if (statusCode == HttpStatus.NOT_FOUND.value()) {
        return "error-404";
      } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
        return "error-500";
      }
    }
    return "error";
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }
}
