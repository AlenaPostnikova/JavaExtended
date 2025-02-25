package com.example.JavaExtended.model.dto.request;

import com.example.JavaExtended.model.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class UserInfoReq {

    @NotEmpty
    @Schema(description = "email")
    private String email;

    @NotEmpty
    @Schema(description = "Пароль")
    private String password;

    @NotEmpty
    @Schema(description = "Имя")
    private String firstName;

    @NotEmpty
    @Schema(description = "Фамилия")
    private String lastName;

    @Schema(description = "Отчество")
    private String middleName;

    @NotNull
    @Schema(description = "Возраст")
    private Integer age;

    @Schema(description = "Пол")
    private Gender gender;
}
