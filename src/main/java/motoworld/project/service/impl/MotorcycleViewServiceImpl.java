package motoworld.project.service.impl;

import motoworld.project.model.entities.Motorcycle;
import motoworld.project.model.entities.Review;
import motoworld.project.model.service.MotorcycleRestModel;
import motoworld.project.model.service.MotorcycleServiceModel;
import motoworld.project.repository.MotorcycleRepository;
import motoworld.project.service.MotorcycleViewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MotorcycleViewServiceImpl implements MotorcycleViewService {

    private final MotorcycleRepository motorcycleRepository;
    private final ModelMapper modelMapper;

    public MotorcycleViewServiceImpl(MotorcycleRepository motorcycleRepository, ModelMapper modelMapper) {
        this.motorcycleRepository = motorcycleRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public MotorcycleServiceModel getByIdModel(String id) {
        Motorcycle motorcycle = motorcycleRepository.findById(id).get();
        return modelMapper.map(motorcycle,MotorcycleServiceModel.class);
    }

    @Override
    public Optional<Motorcycle> getById(String id) {
        return motorcycleRepository.findById(id);
    }

    @Override
    public void save(Motorcycle motorcycle) {
        motorcycleRepository.save(motorcycle);
    }

    @Override
    public List<Review> findAllById(String id) {
        return motorcycleRepository.findById(id).get().getReviews();
    }

    @Override
    public Motorcycle findById(String motorcycleId) {
        return motorcycleRepository.findById(motorcycleId).get();
    }

    @Override
    public List<MotorcycleRestModel> getAllRestMotorcycles() {
        List<MotorcycleRestModel> motorcyclesModels = new ArrayList<>();
        List<Motorcycle> motorcycles = motorcycleRepository.findAll();

        for (Motorcycle motorcycle : motorcycles) {
            MotorcycleRestModel restM = modelMapper.map(motorcycle,MotorcycleRestModel.class);
            restM.setImage(motorcycle.getImages().get(0).getImage());
            restM.setId(motorcycle.getId());
            motorcyclesModels.add(restM);
        }

        return motorcyclesModels;
    }
}
