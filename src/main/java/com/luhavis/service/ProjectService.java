package com.luhavis.service;


import com.luhavis.controller.dto.ProjectRequestDto;
import com.luhavis.controller.dto.ProjectSaveRequestDto;
import com.luhavis.controller.dto.ProjectUpdateRequestDto;
import com.luhavis.domain.CustomUserDetails;
import com.luhavis.domain.Project;
import com.luhavis.domain.ProjectRepository;
import com.luhavis.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public long save(ProjectSaveRequestDto requestDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken)
            throw new AuthenticationCredentialsNotFoundException("Not Logined");

        User user = ((CustomUserDetails) auth.getPrincipal()).getUser();
        requestDto.setCreatedUser(user.getId());
        requestDto.setModifiedUser(user.getId());

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken)
            throw new AuthenticationCredentialsNotFoundException("Not Logined");

        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 없습니다. id = " + id));
        User user = ((CustomUserDetails) auth.getPrincipal()).getUser();

        project.update(requestDto.getProjectNm(), requestDto.getUpperProjectId(), requestDto.getProjectDesc(), requestDto.getProjectStatus(),
                requestDto.getProjectAmount(), requestDto.getUser(), requestDto.getManager(), user.getId());


        return id;
    }

    public Page<Project> findByProjectNmContaining(String projectNm, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable= PageRequest.of(page,10);
        return projectRepository.findByProjectNmContaining(projectNm, pageable);
    }
    public Page<Project> findByManager_ManagerNmContaining(String managerNm, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable= PageRequest.of(page,10);
        return projectRepository.findByManager_ManagerNmContaining(managerNm, pageable);
    }
}
