package motoworld.project.service;

import motoworld.project.model.binding.AdminAddMotorcycleBindingModel;

import java.io.IOException;

public interface AdminMotorcycleService {
    void add(AdminAddMotorcycleBindingModel adminAddMotorcycleBindingModel) throws IOException;
}
