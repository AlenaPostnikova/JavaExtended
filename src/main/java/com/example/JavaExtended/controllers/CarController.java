package com.example.JavaExtended.controllers;

import com.example.JavaExtended.model.dto.request.CarInfoReq;
import com.example.JavaExtended.model.dto.response.CarInfoResp;
import com.example.JavaExtended.service.impl.CarServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
@Tag(name = "Автомобили")
public class CarController {
    private final CarServiceImpl carService;

    @GetMapping("/{id}")
    @Operation(summary = "Получить автомобиль по id")
    public CarInfoResp getCar(@PathVariable Long id){
        return carService.getCar(id);
    }

    @PostMapping
    @Operation(summary = "Добавить автомобиль")
    public CarInfoResp addCar(@RequestBody CarInfoReq req){
        return carService.addCar(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные автомобиля по id")
    public CarInfoResp updateCar(@PathVariable Long id, @RequestBody CarInfoReq req){
        return carService.updateCar(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить автомобиль по id")
    public void deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Получить список всех автомобилей")
    public Page<CarInfoResp> getAllCars(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer perPage,
                                          @RequestParam(defaultValue = "brand") String sort,
                                          @RequestParam(defaultValue = "ASC") Sort.Direction order,
                                          @RequestParam(required = false) String filter){
        return carService.getAllCars(page, perPage, sort, order, filter);
    }

    @PostMapping("/linkCarAndDriver/{carId}/{userId}")
    @Operation(summary = "Прикрепить автомобиль с id к пользователю с id")
    public CarInfoResp linkCarAndDriver(@PathVariable Long carId, @PathVariable Long userId) {
        return carService.linkCarAndDriver(carId, userId);
    }

    @GetMapping("/{id}/myCars")
    @Operation(summary = "Получить список автомобилей пользователя по id")
    public List<CarInfoResp> getMyCars(@PathVariable Long id){
        return carService.getUserCars(id);
    }

}
