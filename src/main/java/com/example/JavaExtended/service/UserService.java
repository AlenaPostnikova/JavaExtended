package com.example.JavaExtended.service;

import com.example.JavaExtended.model.db.entity.User;
import com.example.JavaExtended.model.dto.request.UserInfoReq;
import com.example.JavaExtended.model.dto.response.UserInfoResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {

    UserInfoResp addUser (UserInfoReq req);

    UserInfoResp getUser(Long id);

    User getUserFromDB(Long id);

    UserInfoResp updateUser(Long id, UserInfoReq req);

    void deleteUser(Long id);

//    List<UserInfoResp> getAllUsers();

    Page<UserInfoResp> getAllUsers(Integer page, Integer perPage, String sort, Sort.Direction order, String filter);

    User updateCarList(User userFromDB);

//    UserInfoResp getUser(String email, String lastName);

}
