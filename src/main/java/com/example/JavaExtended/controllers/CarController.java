package com.example.JavaExtended.controllers;

import com.example.JavaExtended.model.dto.request.CarInfoReq;
import com.example.JavaExtended.model.dto.response.CarInfoResp;
import com.example.JavaExtended.model.enums.Color;
import com.example.JavaExtended.service.impl.CarServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarServiceImpl carService;


    @GetMapping("/{id}")
    public CarInfoResp getCar(@PathVariable Long id){
        return carService.getCar(id);
    }

    @PostMapping
    public CarInfoResp addCar(@RequestBody CarInfoReq req){
        return carService.addCar(req);
    }

    @PutMapping("/{id}")
    public CarInfoResp updateCar(@PathVariable Long id, @RequestBody CarInfoReq req){
        return carService.updateCar(id, req);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
    }

    @GetMapping("/all")
    public List<CarInfoResp> getAllCars(){
        return carService.getAllCars();
    }

    @GetMapping
    public CarInfoResp getCarWithParams(@RequestParam(required = false) String brand, @RequestParam Color color){
        return carService.getCar(brand, color);
    }

}
