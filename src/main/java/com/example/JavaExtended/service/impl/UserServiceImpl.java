package com.example.JavaExtended.service.impl;

import com.example.JavaExtended.model.dto.request.UserInfoReq;
import com.example.JavaExtended.model.dto.response.UserInfoResp;
import com.example.JavaExtended.model.enums.Gender;
import com.example.JavaExtended.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j //для логирования
@Service
@RequiredArgsConstructor //созд. конструктора для инициализации бина
public class UserServiceImpl implements UserService {
    private final ObjectMapper mapper;

    @Override
    public UserInfoResp addUser(UserInfoReq req) {
        UserInfoResp userInfoResp = mapper.convertValue(req, UserInfoResp.class);
        userInfoResp.setId(1L);
        return userInfoResp;
    }

    @Override
    public UserInfoResp getUser(Long id) {
        return UserInfoResp.builder()
                .id(1L)
                .email("cow@mail.ru")
                .age(4)
                .firstName("Fedor")
                .lastName("Plushikov")
                .middleName("Korovich")
                .gender(Gender.MALE)
                .password("Cow12")
                .build();
    }

    @Override
    public UserInfoResp updateUser(Long id, UserInfoReq req) {
        if (id != 1L){
            log.error("User with id {} not found", id);
            return null;
        }
        return UserInfoResp.builder()
                .id(1L)
                .email("cat@mail.ru")
                .age(3)
                .firstName("Guri")
                .lastName("Plushikov")
                .middleName("Vladimirovich")
                .gender(Gender.MALE)
                .password("Cow123")
                .build();
    }

    @Override
    public void deleteUser(Long id) {
        if (id != 1L){
            log.error("User with id {} not found", id);
            return;
        }
        log.info("User with id {} deleted successfully", id);
    }

    @Override
    public List<UserInfoResp> getAllUsers() {
        return List.of(UserInfoResp.builder()
                .id(1L)
                .email("cat@mail.ru")
                .age(3)
                .firstName("Guri")
                .lastName("Plushikov")
                .middleName("Vladimirovich")
                .gender(Gender.MALE)
                .password("Cow123")
                .build());
    }

    @Override
    public UserInfoResp getUser(String email, String lastName) {
        return getUser(1L);
    }
}
