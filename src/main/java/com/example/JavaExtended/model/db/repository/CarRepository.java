package com.example.JavaExtended.model.db.repository;

import com.example.JavaExtended.model.db.entity.Car;
import com.example.JavaExtended.model.db.entity.User;
import com.example.JavaExtended.model.enums.CarStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByBrand(String brand);

    List<Car> findAllByStatus(CarStatus status);

    @Query(nativeQuery = true, value = "select * from cars where true order by cars.updated_at desc limit 1")
    Car findByUpdateDate();

    @Query("select c from Car c where c.status <> :status")
    List<Car> findAllByStatusNot(@Param("status") String status);

    @Query("select u.cars from User u where u.id = :userId")
    List<Car> getUserCarsBrandName(@Param("userId") Long userId);

    @Query("select c from Car c where c.brand like %:filter% or c.model like %:filter% ")
    Page<Car> findAllFiltered(Pageable pageRequest, @Param("filter") String filter);
}