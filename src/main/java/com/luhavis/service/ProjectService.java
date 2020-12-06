package com.luhavis.service;


import com.luhavis.controller.dto.ProjectRequestDto;
import com.luhavis.controller.dto.ProjectSaveRequestDto;
import com.luhavis.controller.dto.ProjectUpdateRequestDto;
import com.luhavis.domain.Project;
import com.luhavis.domain.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public long save(ProjectSaveRequestDto requestDto) {
        return projectRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public ProjectRequestDto findById(Long id) {
        Project entity = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 없습니다. id = " + id));

        return new ProjectRequestDto(entity);
    }

    public List getAll() {
        return projectRepository.findAll();
    }

    public Page<Project> getAll(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable= PageRequest.of(page,10);
        return projectRepository.findAll(pageable);
    }

    @Transactional
    public Long update(Long id, ProjectUpdateRequestDto requestDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 없습니다. id = " + id));

        project.update(requestDto.getProjectNm(), requestDto.getUpperProjectId(), requestDto.getProjectDesc(), requestDto.getProjectStatus(),
                requestDto.getProjectAmount(), requestDto.getUser(), requestDto.getManager());


        return id;
    }
}
