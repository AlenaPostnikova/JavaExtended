package com.example.JavaExtended.model.dto.request;
import com.example.JavaExtended.model.enums.CarType;
import com.example.JavaExtended.model.enums.Color;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class CarInfoReq {

    @NotEmpty
    @Schema(description = "Марка автомобиля")
    private String brand;

    @Schema(description = "Модель")
    private String model;

    @Schema(description = "Цвет")
    private Color color;

    @Schema(description = "Год выпуска")
    private Integer year;

    @Schema(description = "Цена, руб.")
    private Long price;

    @Schema(description = "Новая или б/у")
    private Boolean isNew;

    @Schema(description = "Тип")
    private CarType type;
}