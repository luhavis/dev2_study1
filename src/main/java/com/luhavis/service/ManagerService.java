package com.luhavis.service;

import com.luhavis.controller.dto.ManagerSaveRequestDto;
import com.luhavis.domain.Manager;
import com.luhavis.domain.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ManagerService {

    private final ManagerRepository managerRepository;

    @Transactional
    public Long save(ManagerSaveRequestDto requestDto) {
        return managerRepository.save(requestDto.toEntity()).getId();
    }


    public List getAll() {
        return managerRepository.findAll();
    }
}
