package motoworld.project.web.rest;

import motoworld.project.model.service.MotorcycleRestModel;
import motoworld.project.service.MotorcycleViewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/moto")
@RestController
public class MotorcycleRestController {

  private final MotorcycleViewService motorcycleViewService;

  public MotorcycleRestController(MotorcycleViewService motorcycleViewService) {
    this.motorcycleViewService = motorcycleViewService;
  }


  @GetMapping("/api")
  public ResponseEntity<List<MotorcycleRestModel>> findAll() {
    return ResponseEntity
            .ok()
            .body(motorcycleViewService.getAllRestMotorcycles());
  }
}
