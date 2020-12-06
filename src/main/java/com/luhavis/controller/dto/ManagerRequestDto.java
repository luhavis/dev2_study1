package com.luhavis.controller.dto;

import com.luhavis.domain.Manager;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ManagerRequestDto {
    private Long id;
    private String managerNm;
    private String managerTelNo;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public ManagerRequestDto(Manager manager) {
        this.id = manager.getId();
        this.managerNm = manager.getManagerNm();
        this.managerTelNo = manager.getManagerTelNo();
        this.createDate = manager.getCreateDate();
        this.modifiedDate = manager.getModifiedDate();
    }
}
