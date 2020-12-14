package com.luhavis.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByProjectNmContaining(String projectNm, Pageable pageable);
    Page<Project> findByManager_ManagerNmContaining(String managerNm, Pageable pageable);
}
