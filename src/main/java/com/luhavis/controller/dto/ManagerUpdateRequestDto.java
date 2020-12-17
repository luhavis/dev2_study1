package com.luhavis.controller.dto;

import com.luhavis.domain.Manager;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ManagerUpdateRequestDto {
    private String managerNm;
    private String managerTelNo;
    private Long modifiedUser;

    @Builder
    public ManagerUpdateRequestDto(String managerNm, String managerTelNo, Long modifiedUser) {
        this.managerNm = managerNm;
        this.managerTelNo = managerTelNo;
        this.modifiedUser = modifiedUser;
    }
}
