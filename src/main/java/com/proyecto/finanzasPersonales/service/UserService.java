package com.proyecto.finanzasPersonales.service;

import com.proyecto.finanzasPersonales.controller.dto.UserDTO;
import com.proyecto.finanzasPersonales.entity.User;

public interface UserService {
    public UserDTO get(Long id) throws Exception;
    public void create(UserDTO userDTO) throws Exception;
    public void update(long id , UserDTO userDTO) throws Exception;
    public void delete(Long id) throws Exception;
}
