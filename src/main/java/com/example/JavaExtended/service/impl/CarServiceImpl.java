package com.example.JavaExtended.service.impl;

import com.example.JavaExtended.model.dto.request.CarInfoReq;
import com.example.JavaExtended.model.dto.response.CarInfoResp;
import com.example.JavaExtended.model.enums.CarType;
import com.example.JavaExtended.model.enums.Color;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CarServiceImpl {
    private final ObjectMapper mapper;

    public CarInfoResp addCar(CarInfoReq req) {
        CarInfoResp carInfoResp = mapper.convertValue(req, CarInfoResp.class);
        carInfoResp.setId(1L);
        return carInfoResp;
    }


    public CarInfoResp getCar(Long id) {
        return CarInfoResp.builder()
                .id(1L)
                .brand("KIA")
                .model("Soul")
                .color(Color.BLACK)
                .year(2013)
                .price(830000L)
                .isNew(false)
                .type(CarType.SUV)
                .build();
    }


    public CarInfoResp updateCar(Long id, CarInfoReq req) {
        if (id != 1L){
            log.error("Car with id {} not found", id);
            return null;
        }
        return CarInfoResp.builder()
                .id(1L)
                .brand("KIA")
                .model("Soul")
                .color(Color.RED)
                .year(2018)
                .price(530000L)
                .isNew(false)
                .type(CarType.SUV)
                .build();
    }

    public void deleteCar(Long id) {
        if (id != 1L){
            log.error("Car with id {} not found", id);
            return;
        }
        log.info("Car with id {} deleted successfully", id);
    }

    public List<CarInfoResp> getAllCars() {
        return List.of(CarInfoResp.builder()
                .id(1L)
                .brand("KIA")
                .model("Soul")
                .color(Color.RED)
                .year(2018)
                .price(530000L)
                .isNew(false)
                .type(CarType.SUV)
                .build());
    }

     public CarInfoResp getCar(String brand, Color color) {
        return getCar(1L);
    }
}
