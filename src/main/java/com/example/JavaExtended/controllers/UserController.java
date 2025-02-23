package com.example.JavaExtended.controllers;

import com.example.JavaExtended.model.dto.request.UserInfoReq;
import com.example.JavaExtended.model.dto.response.UserInfoResp;
import com.example.JavaExtended.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserInfoResp getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

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
//    public List<UserInfoResp> getAllUser(){
//        return userService.getAllUsers();
    public Page<UserInfoResp> getAllUsers(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer perPage,
                                          @RequestParam(defaultValue = "lastName") String sort,
                                          @RequestParam(defaultValue = "ASC") Sort.Direction order,
                                          @RequestParam(required = false) String filter){
        return userService.getAllUsers(page, perPage, sort, order, filter);
    }

}