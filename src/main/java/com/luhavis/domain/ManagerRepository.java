package com.luhavis.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Page<Manager> findByManagerNmContaining(String managerNm, Pageable pageable);

}
