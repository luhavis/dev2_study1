package com.luhavis.service;


import com.luhavis.controller.dto.ProjectSaveRequestDto;
import com.luhavis.domain.Project;
import com.luhavis.domain.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public long save(ProjectSaveRequestDto requestDto) {
        return projectRepository.save(requestDto.toEntity()).getId();
    }

    public List getAll() {
        return projectRepository.findAll();
    }

    public Page<Project> getAll(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable= PageRequest.of(page,10);
        return projectRepository.findAll(pageable);
    }

}
