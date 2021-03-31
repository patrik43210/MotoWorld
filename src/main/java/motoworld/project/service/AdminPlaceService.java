package motoworld.project.service;

import motoworld.project.model.binding.AdminAddPlaceBindingModel;

import java.io.IOException;

public interface AdminPlaceService {
    void add(AdminAddPlaceBindingModel adminAddPlaceBindingModel) throws IOException;

    boolean checkLocation(String location);

    boolean checkPostcode(String postcode);
}
