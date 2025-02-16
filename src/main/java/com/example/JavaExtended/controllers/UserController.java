package com.example.JavaExtended.controllers;

import com.example.JavaExtended.model.dto.request.UserInfoReq;
import com.example.JavaExtended.model.dto.response.UserInfoResp;
//import com.example.JavaExtended.model.enums.Gender;
import com.example.JavaExtended.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    @GetMapping
//    public UserInfoResp getUser() {
//        return UserInfoResp.builder()
//                .id(1L)
//                .email("cow@mail.ru")
//                .age(4)
//                .firstName("Fedor")
//                .lastName("Plushikov")
//                .middleName("Korovich")
//                .gender(Gender.MALE)
//                .password("Cow12")
//                .build();
//    }

    @GetMapping("/{id}")
    public UserInfoResp getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

//    @PostMapping
//    public UserInfoReq addUser(@RequestBody UserInfoReq req){
//
////        System.out.println("User created!");
////        System.out.println(req);
//        return req;
//    }

    @PostMapping
    public UserInfoResp addUser(@RequestBody UserInfoReq req){
        return userService.addUser(req);
    }

    @PutMapping("/{id}")
    public UserInfoResp updateUser(@PathVariable Long id, @RequestBody UserInfoReq req){
        return userService.updateUser(id, req);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/all")
    public List<UserInfoResp> getAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping
    public UserInfoResp getUserWithParams(@RequestParam(required = false) String email, @RequestParam String lastName){
        return userService.getUser(email, lastName);
    }


}

