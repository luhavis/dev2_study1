package com.luhavis.service;

import com.luhavis.controller.dto.UserSaveRequestDto;
import com.luhavis.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
}
