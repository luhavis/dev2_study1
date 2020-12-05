package com.luhavis.domain;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserNm(String userNm);

    Optional<User> findByUserId(String userId);
}
