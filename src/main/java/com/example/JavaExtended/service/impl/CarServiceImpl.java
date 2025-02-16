package com.example.JavaExtended.service.impl;

import com.example.JavaExtended.model.db.entity.Car;
import com.example.JavaExtended.model.db.repository.CarRepository;
import com.example.JavaExtended.model.dto.request.CarInfoReq;
import com.example.JavaExtended.model.dto.response.CarInfoResp;
import com.example.JavaExtended.model.enums.CarStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CarServiceImpl {
    private final ObjectMapper mapper;
    private final CarRepository carRepository;

    public CarInfoResp addCar(CarInfoReq req) {
        Car car = mapper.convertValue(req, Car.class);
        car.setStatus(CarStatus.CREATED);

        Car save = carRepository.save(car); //сохранили в базу данных
        return mapper.convertValue(save, CarInfoResp.class);
    }

    private Car getCarFromDB(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.orElse(new Car());
    }

    public CarInfoResp getCar(Long id) {
        Car car = getCarFromDB(id);
        return mapper.convertValue(car, CarInfoResp.class);
    }


    public CarInfoResp updateCar(Long id, CarInfoReq req) {
        Car carFromDB = getCarFromDB(id);

        if (carFromDB.getId() == null){
            return mapper.convertValue(carFromDB, CarInfoResp.class);
        }
        Car carReq = mapper.convertValue(req, Car.class);

        carFromDB.setBrand(carReq.getBrand() == null ? carFromDB.getBrand() : carReq.getBrand());
        carFromDB.setModel(carReq.getModel() == null ? carFromDB.getModel() : carReq.getModel());
        carFromDB.setColor(carReq.getColor() == null ? carFromDB.getColor() : carReq.getColor());
        carFromDB.setYear(carReq.getYear() == null ? carFromDB.getYear() : carReq.getYear());
        carFromDB.setPrice(carReq.getPrice() == null ? carFromDB.getPrice() : carReq.getPrice());
        carFromDB.setIsNew(carReq.getIsNew() == null ? carFromDB.getIsNew() : carReq.getIsNew());
        carFromDB.setType(carReq.getType() == null ? carFromDB.getType() : carReq.getType());

        carFromDB.setStatus(CarStatus.CREATED);
        carFromDB = carRepository.save(carFromDB);

        return mapper.convertValue(carFromDB, CarInfoResp.class);
    }

    public void deleteCar(Long id) {
        Car carFromDB = getCarFromDB(id);
        if (carFromDB.getId() == null){
            log.error("Car with id {} not found", id);
            return;
        }
        carFromDB.setStatus(CarStatus.DELETED);
        carFromDB = carRepository.save(carFromDB);
    }

    public List<CarInfoResp> getAllCars() {
        return carRepository.findAll().stream()
                .map(car -> mapper.convertValue(car, CarInfoResp.class))
                .collect(Collectors.toList());
    }

}
