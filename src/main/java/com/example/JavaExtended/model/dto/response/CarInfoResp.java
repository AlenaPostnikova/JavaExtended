package com.example.JavaExtended.model.dto.response;
import com.example.JavaExtended.model.dto.request.CarInfoReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CarInfoResp extends CarInfoReq {
    private Long id;
}
