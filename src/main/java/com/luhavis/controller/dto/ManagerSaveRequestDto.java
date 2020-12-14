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
    private Long createdUser;
    private Long modifiedUser;

    public Manager toEntity() {
        return Manager.builder()
                .managerNm(managerNm)
                .managerTelNo(managerTelNo)
                .createdUser(createdUser)
                .modifiedUser(modifiedUser)
                .build();
    }

}
