package com.luhavis.controller.dto;

import com.luhavis.domain.Manager;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ManagerSaveRequestDto {
    private String managerNm;
    private String managerTelNo;

    @Builder
    public ManagerSaveRequestDto(String managerNm, String managerTelNo) {
        this.managerNm = managerNm;
        this.managerTelNo = managerTelNo;
    }

    public Manager toEntity() {
        return Manager.builder()
                .managerNm(managerNm)
                .managerTelNo(managerTelNo)
                .build();
    }

}
