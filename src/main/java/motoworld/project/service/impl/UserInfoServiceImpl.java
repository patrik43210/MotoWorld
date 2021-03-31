package motoworld.project.service.impl;


import motoworld.project.model.entities.UserInfo;
import motoworld.project.repository.UserInfoRepository;
import motoworld.project.service.UserInfoService;
import org.springframework.stereotype.Service;


@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;


    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;

    }


    @Override
    public void save(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }


    }



