package com.example.JavaExtended.service.impl;

import com.example.JavaExtended.exeption.CommonBackendException;
import com.example.JavaExtended.model.db.entity.Car;
import com.example.JavaExtended.model.db.entity.User;
import com.example.JavaExtended.model.db.repository.CarRepository;
import com.example.JavaExtended.model.dto.request.CarInfoReq;
import com.example.JavaExtended.model.dto.response.CarInfoResp;
import com.example.JavaExtended.model.dto.response.UserInfoResp;
import com.example.JavaExtended.model.enums.CarStatus;
import com.example.JavaExtended.service.UserService;
import com.example.JavaExtended.utils.PaginationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CarServiceImpl {
    private final ObjectMapper mapper;
    private final CarRepository carRepository;
    private final UserService userService;

    public CarInfoResp addCar(CarInfoReq req) {

        Car car = mapper.convertValue(req, Car.class);
        car.setStatus(CarStatus.CREATED);

        Car save = carRepository.save(car); //сохранили в базу данных
        return mapper.convertValue(save, CarInfoResp.class);
    }

    private Car getCarFromDB(Long id) {

        Optional<Car> optionalCar = carRepository.findById(id);

        final String errMessage = String.format("Car with id: %s not found", id);

        return optionalCar.orElseThrow(() -> new CommonBackendException(errMessage, HttpStatus.NOT_FOUND));
    }

    public CarInfoResp getCar(Long id) {
        Car car = getCarFromDB(id);
        return mapper.convertValue(car, CarInfoResp.class);
    }


    public CarInfoResp updateCar(Long id, CarInfoReq req) {
        Car carFromDB = getCarFromDB(id);

        Car carReq = mapper.convertValue(req, Car.class);

        carFromDB.setBrand(carReq.getBrand() == null ? carFromDB.getBrand() : carReq.getBrand());
        carFromDB.setModel(carReq.getModel() == null ? carFromDB.getModel() : carReq.getModel());
        carFromDB.setColor(carReq.getColor() == null ? carFromDB.getColor() : carReq.getColor());
        carFromDB.setYear(carReq.getYear() == null ? carFromDB.getYear() : carReq.getYear());
        carFromDB.setPrice(carReq.getPrice() == null ? carFromDB.getPrice() : carReq.getPrice());
        carFromDB.setIsNew(carReq.getIsNew() == null ? carFromDB.getIsNew() : carReq.getIsNew());
        carFromDB.setType(carReq.getType() == null ? carFromDB.getType() : carReq.getType());

        carFromDB.setStatus(CarStatus.UPDATED);
        carFromDB = carRepository.save(carFromDB);

        return mapper.convertValue(carFromDB, CarInfoResp.class);
    }

    public void deleteCar(Long id) {
        Car carFromDB = getCarFromDB(id);

        carFromDB.setStatus(CarStatus.DELETED);
        carFromDB = carRepository.save(carFromDB);
    }

    public Page<CarInfoResp> getAllCars(Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {

        Pageable pageRequest = PaginationUtils.getPageRequest(page, perPage, sort, order);

        Page<Car> cars;

        if (StringUtils.hasText(filter)){
            cars = carRepository.findAllFiltered(pageRequest, filter);
        } else {
            cars = carRepository.findAll(pageRequest);
        }

        List<CarInfoResp> content = cars.getContent().stream()
                .map(c -> mapper.convertValue(c, CarInfoResp.class))
                .collect(Collectors.toList());
        return new PageImpl<>(content, pageRequest, cars.getTotalElements());
    }

    public CarInfoResp linkCarAndDriver(Long carId, Long userId){ //привязка машины к водителю
        Car carFromDB = getCarFromDB(carId);
        User userFromDB = userService.getUserFromDB(userId);

        if (carFromDB == null || userFromDB == null){
            return CarInfoResp.builder().build();
        }
        List<Car> cars = userFromDB.getCars();

        Car existingCar = cars.stream().filter(car -> car.getId().equals(carId)).findFirst().orElse(null);

        if (existingCar != null){
            return mapper.convertValue(existingCar, CarInfoResp.class);
        }

        cars.add(carFromDB); //доб новую машину в список машин пользователя
        User user = userService.updateCarList(userFromDB); // обновили список машин пользователя

        carFromDB.setUser(user); //установили машине пользователя
        carRepository.save(carFromDB); //пересохранили в БД

        CarInfoResp carInfoResp = mapper.convertValue(carFromDB, CarInfoResp.class);
        UserInfoResp userInfoResp = mapper.convertValue(userFromDB, UserInfoResp.class);

        carInfoResp.setUser(userInfoResp);

        return carInfoResp;
    }

    public List<CarInfoResp> getUserCars(Long userId){

        User user = userService.getUserFromDB(userId); //чтобы выбросить исключение, если пользователь не найден

        return carRepository.getUserCarsBrandName(userId).stream()
                .map(car-> mapper.convertValue(car, CarInfoResp.class))
                .collect(Collectors.toList());
    }

}
