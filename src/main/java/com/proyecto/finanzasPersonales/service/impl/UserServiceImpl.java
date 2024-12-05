package com.proyecto.finanzasPersonales.service.impl;

import com.proyecto.finanzasPersonales.controller.dto.UserDTO;
import com.proyecto.finanzasPersonales.entity.Category;
import com.proyecto.finanzasPersonales.entity.Expense;
import com.proyecto.finanzasPersonales.entity.User;
import com.proyecto.finanzasPersonales.entity.exception.ExpenseNotFoundException;
import com.proyecto.finanzasPersonales.entity.exception.UserNotFoundException;
import com.proyecto.finanzasPersonales.repository.UserRepository;
import com.proyecto.finanzasPersonales.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO get(Long id) throws Exception {
        log.debug("UserService-get executed");
        return userRepository.findById(id)
                .map(this::fromUserToUserDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public void create(UserDTO userDTO) throws Exception{
        log.debug("UserService-create executed");
        userRepository.save(User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build()
        );
    }

    @Override
    public void update(long id ,UserDTO userDTO) throws Exception {
        log.debug("UserService-update executed");
        User user =  userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) throws Exception {
        log.debug("UserService-delete executed");
        User user =  userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.deleteById(id);
    }

    private UserDTO fromUserToUserDTO(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
