package motoworld.project.repository;

import motoworld.project.model.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository  extends JpaRepository<Image,String> {



}
