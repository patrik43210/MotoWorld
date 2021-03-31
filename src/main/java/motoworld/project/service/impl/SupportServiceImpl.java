package motoworld.project.service.impl;

import motoworld.project.model.binding.SupportRequestBindingModel;
import motoworld.project.model.entities.SupportMessages;
import motoworld.project.repository.SupportRepository;
import motoworld.project.service.SupportService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SupportServiceImpl implements SupportService {

    private final ModelMapper modelMapper;
    private final SupportRepository supportRepository;

    public SupportServiceImpl(ModelMapper modelMapper, SupportRepository supportRepository) {
        this.modelMapper = modelMapper;
        this.supportRepository = supportRepository;
    }

    @Override
    public void addMessage(SupportRequestBindingModel supportRequestBindingModel) {
        SupportMessages supportMessages = modelMapper.map(supportRequestBindingModel, SupportMessages.class);
        supportRepository.save(supportMessages);
    }

    @Override
    public List<SupportRequestBindingModel> findAll() {

        List<SupportRequestBindingModel> messagesModel = new ArrayList<>();
        List<SupportMessages> messages = supportRepository.findAll();
        for (SupportMessages message : messages) {

            SupportRequestBindingModel model = modelMapper.map(message, SupportRequestBindingModel.class);
            model.setDate(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(message.getDate()));
            messagesModel.add(model);
        }
        return messagesModel;
    }

    @Override
    public void removeMessage(String id) {
        supportRepository.deleteById(id);
    }
}
