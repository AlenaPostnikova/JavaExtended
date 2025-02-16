package com.example.JavaExtended.controllers;

import com.example.JavaExtended.model.dto.request.UserInfoReq;
import com.example.JavaExtended.model.dto.response.UserInfoResp;
import com.example.JavaExtended.model.enums.Gender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public UserInfoResp getUser() {
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

    @PostMapping
    public UserInfoReq addUser(@RequestBody UserInfoReq req){

//        System.out.println("User created!");
//        System.out.println(req);
        return req;
    }

    }

