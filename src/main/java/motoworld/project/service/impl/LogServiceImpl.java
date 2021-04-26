package motoworld.project.service.impl;


import motoworld.project.model.entities.LogEntity;
import motoworld.project.model.entities.Motorcycle;
import motoworld.project.model.entities.UserEntity;
import motoworld.project.model.service.MotorcycleServiceModel;
import motoworld.project.repository.LogRepository;
import motoworld.project.service.LogService;
import motoworld.project.service.MotorcycleViewService;
import motoworld.project.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final MotorcycleViewService motorcycleViewService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    private final Logger LOGGER = LoggerFactory.getLogger(LocalDateTime.class);


    public LogServiceImpl(LogRepository logRepository, MotorcycleViewService motorcycleViewService, UserService userService, ModelMapper modelMapper) {
        this.logRepository = logRepository;
        this.motorcycleViewService = motorcycleViewService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createLog(String action, String motorcycleId) {
        Motorcycle motorcycle = motorcycleViewService
                .findById(motorcycleId);

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = authentication.getName();
        UserEntity userEntity = userService.findByUsername(username).get();

        LogEntity logEntity = new LogEntity()
                .setMotorcycle(motorcycle)
                .setUserEntity(userEntity)
                .setAction(action);

        logRepository.save(logEntity);

    }

    @Override
    public MotorcycleServiceModel findMostViewed() {
        List<Motorcycle> list = logRepository.findMostPopular();

        if (list.isEmpty()) {
            return null;
        }
        Motorcycle motorcycle = motorcycleViewService.findById(list.get(0).getId());
        return modelMapper.map(motorcycle,
                MotorcycleServiceModel.class);
    }


    @Scheduled(cron = "50 * * * * *")
    public void onEachMinute() {
        LOGGER.info("The most popular bike for the day updated," +
                "should delete everyday but for the demo " +
                "is updated on every minute at :50sec");
       logRepository.deleteAll();
    }

}
