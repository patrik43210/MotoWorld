package motoworld.project.service;

import motoworld.project.model.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {


    Image save(MultipartFile image) throws IOException;


    void saveUserProfile(Image image);
}
