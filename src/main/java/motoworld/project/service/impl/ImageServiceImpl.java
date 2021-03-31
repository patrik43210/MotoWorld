package motoworld.project.service.impl;

import motoworld.project.model.entities.Image;
import motoworld.project.repository.ImageRepository;
import motoworld.project.service.CloudinaryService;
import motoworld.project.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;

    public ImageServiceImpl(CloudinaryService cloudinaryService, ImageRepository imageRepository) {
        this.cloudinaryService = cloudinaryService;
        this.imageRepository = imageRepository;
    }

    @Override
    public Image save(MultipartFile image) throws IOException {

        Image imageToSave = new Image(cloudinaryService.uploadImage(image));
        imageRepository.saveAndFlush(imageToSave);
        return imageToSave;
    }

    @Override
    public void saveUserProfile(Image image) {
        imageRepository.saveAndFlush(image);
    }
}
