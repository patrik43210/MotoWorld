package motoworld.project.service.impl;

import motoworld.project.model.binding.AdminAddPlaceBindingModel;
import motoworld.project.model.entities.Image;
import motoworld.project.model.entities.Place;
import motoworld.project.model.service.PlaceServiceModel;
import motoworld.project.repository.PlaceRepository;
import motoworld.project.service.AdminPlaceService;
import motoworld.project.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminPlaceServiceImpl implements AdminPlaceService {

    private final ModelMapper modelMapper;
    private final ImageService imageService;
    private final PlaceRepository placeRepository;

    public AdminPlaceServiceImpl(ModelMapper modelMapper, ImageService imageService, PlaceRepository placeRepository) {
        this.modelMapper = modelMapper;
        this.imageService = imageService;
        this.placeRepository = placeRepository;
    }

    @Override
    public void add(AdminAddPlaceBindingModel adminAddPlaceBindingModel) throws IOException {

        PlaceServiceModel placeModel = modelMapper.map(adminAddPlaceBindingModel, PlaceServiceModel.class);
        Place place = modelMapper.map(placeModel, Place.class);
        List<Image> images = new ArrayList<>();

        if (isNotNull(adminAddPlaceBindingModel.getImageUrl1())) {
            images.add(imageService.save(adminAddPlaceBindingModel.getImageUrl1()));

        }

        if (isNotNull(adminAddPlaceBindingModel.getImageUrl2())) {
            images.add(imageService.save(adminAddPlaceBindingModel.getImageUrl2()));

        }

        if (isNotNull(adminAddPlaceBindingModel.getImageUrl3())) {
            images.add(imageService.save(adminAddPlaceBindingModel.getImageUrl3()));
        }
        place.setImages(images);

        placeRepository.save(place);

    }


    @Override
    public boolean checkLocation(String location) {
        return placeRepository.findByLocation(location).isPresent();
    }

    @Override
    public boolean checkPostcode(String postcode) {
        return placeRepository.findByPostcode(postcode).isPresent();
    }

    private boolean isNotNull(MultipartFile file) {
        return file != null;
    }
}
