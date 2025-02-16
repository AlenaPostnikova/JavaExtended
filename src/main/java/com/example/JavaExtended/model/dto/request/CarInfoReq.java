package com.example.JavaExtended.model.dto.request;
import com.example.JavaExtended.model.enums.CarType;
import com.example.JavaExtended.model.enums.Color;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarInfoReq {
    private String brand;
    private String model;
    private Color color;
    private Integer year;
    private Long price;
    private Boolean isNew;
    private CarType type;
}