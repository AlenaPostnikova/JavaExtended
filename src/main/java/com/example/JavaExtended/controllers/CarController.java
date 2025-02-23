package com.example.JavaExtended.controllers;

import com.example.JavaExtended.model.dto.request.CarInfoReq;
import com.example.JavaExtended.model.dto.response.CarInfoResp;
import com.example.JavaExtended.service.impl.CarServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
    public Page<CarInfoResp> getAllCars(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer perPage,
                                          @RequestParam(defaultValue = "brand") String sort,
                                          @RequestParam(defaultValue = "ASC") Sort.Direction order,
                                          @RequestParam(required = false) String filter){
        return carService.getAllCars(page, perPage, sort, order, filter);
    }

    @PostMapping("/linkCarAndDriver/{carId}/{userId}")
    public CarInfoResp linkCarAndDriver(@PathVariable Long carId, @PathVariable Long userId) {
        return carService.linkCarAndDriver(carId, userId);
    }

    @GetMapping("/{id}/myCars")
    public List<CarInfoResp> getMyCars(@PathVariable Long id){
        return carService.getUserCars(id);
    }

}
