package com.luhavis.service;

import com.luhavis.controller.dto.ManagerSaveRequestDto;
import com.luhavis.domain.Manager;
import com.luhavis.domain.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Manager> getAll(Pageable pageable) {
        // 페이징 인텍스는 0부터 시작해야되서, 0이 아니면 페이징 값에서 1을 뺴줌
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() -1);
        pageable = PageRequest.of(page, 10);
        return managerRepository.findAll(pageable);
    }
}
