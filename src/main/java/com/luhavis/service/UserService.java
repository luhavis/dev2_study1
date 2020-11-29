package com.luhavis.service;

import com.luhavis.controller.dto.UserSaveRequestDto;
import com.luhavis.domain.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;


    public String save(UserSaveRequestDto requestDto) {
        System.out.println("?");
        try {
            System.out.println(userRepository.save(requestDto.toEntity()));

            return "success";


        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        }
    }
}
