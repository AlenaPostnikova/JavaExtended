package com.example.JavaExtended.model.dto.response;

import com.example.JavaExtended.model.dto.request.UserInfoReq;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResp extends UserInfoReq {
    private Long id;
}
