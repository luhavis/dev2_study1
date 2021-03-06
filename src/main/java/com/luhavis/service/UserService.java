package com.luhavis.service;

import com.luhavis.controller.dto.UserSaveRequestDto;
import com.luhavis.domain.User;
import com.luhavis.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long save(UserSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getId();
    }

    public List getAll() {
        return userRepository.findAll();
    }

    public User findByUserEmail(String userEmail) {
        User user = userRepository.findByUserEmail(userEmail)
                .orElse(null);


        return user;

    }
}
