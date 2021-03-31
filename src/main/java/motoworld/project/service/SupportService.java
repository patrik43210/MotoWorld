package motoworld.project.service;


import motoworld.project.model.binding.SupportRequestBindingModel;

import java.util.List;

public interface SupportService {


    void addMessage(SupportRequestBindingModel supportRequestBindingModel);

    List<SupportRequestBindingModel> findAll();

    void removeMessage(String id);
}
