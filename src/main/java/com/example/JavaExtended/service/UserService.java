package com.example.JavaExtended.service;

import com.example.JavaExtended.model.dto.request.UserInfoReq;
import com.example.JavaExtended.model.dto.response.UserInfoResp;

import java.util.List;

public interface UserService {

    UserInfoResp addUser (UserInfoReq req);

    UserInfoResp getUser(Long id);

    UserInfoResp updateUser(Long id, UserInfoReq req);

    void deleteUser(Long id);

    List<UserInfoResp> getAllUsers();

    UserInfoResp getUser(String email, String lastName);

}
