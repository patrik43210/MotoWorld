package motoworld.project.service.impl;

import motoworld.project.model.binding.AdminAddMotorcycleBindingModel;
import motoworld.project.model.entities.Image;
import motoworld.project.model.entities.Motorcycle;
import motoworld.project.model.service.MotorcycleServiceModel;
import motoworld.project.repository.MotorcycleRepository;
import motoworld.project.service.AdminMotorcycleService;
import motoworld.project.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminMotorcycleServiceImpl implements AdminMotorcycleService {

    private final ModelMapper modelMapper;
    private final MotorcycleRepository motorcycleRepository;
    private final ImageService imageService;

    public AdminMotorcycleServiceImpl(ModelMapper modelMapper, MotorcycleRepository motorcycleRepository, ImageService imageService) {
        this.modelMapper = modelMapper;
        this.motorcycleRepository = motorcycleRepository;
        this.imageService = imageService;
    }


    @Override
    public void add(AdminAddMotorcycleBindingModel adminAddMotorcycleBindingModel) throws IOException {

        MotorcycleServiceModel motorcycleModel = modelMapper.map(adminAddMotorcycleBindingModel, MotorcycleServiceModel.class);
        Motorcycle motorcycle = modelMapper.map(motorcycleModel, Motorcycle.class);
        List<Image> images = new ArrayList<>();

        if (isNotNull(adminAddMotorcycleBindingModel.getImageUrl1())) {
            images.add( imageService.save(adminAddMotorcycleBindingModel.getImageUrl1()));
        }

        if (isNotNull(adminAddMotorcycleBindingModel.getImageUrl2())) {
            images.add( imageService.save(adminAddMotorcycleBindingModel.getImageUrl2()));
        }

        if (isNotNull(adminAddMotorcycleBindingModel.getImageUrl3())) {
            images.add( imageService.save(adminAddMotorcycleBindingModel.getImageUrl3()));
        }

        motorcycle.setImages(images);


        motorcycleRepository.save(motorcycle);

    }

    private boolean isNotNull(MultipartFile file) {
        return file != null;
    }

}
