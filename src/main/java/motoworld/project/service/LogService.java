package motoworld.project.service;


import motoworld.project.model.service.MotorcycleServiceModel;

public interface LogService {
    void createLog(String action, String albumId);
    MotorcycleServiceModel findMostViewed();
}
