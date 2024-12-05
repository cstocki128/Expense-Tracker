//SE COMENTA POR IMPLEMENTACION DE SPRING DATA JPA

/* package com.proyecto.finanzasPersonales.repository.impl;

import com.proyecto.finanzasPersonales.entity.User;
import com.proyecto.finanzasPersonales.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class UserRepositoryImplH2 implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImplH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(User user) {
        String query = "INSERT INTO GESTION_PERSONAS.GP_USER (name, email) values (?, ?)";
        try {
            jdbcTemplate.update(query, user.getName(), user.getEmail());
            System.out.println("Usuario creado exitosamente");
        }catch (Exception e){
            System.out.println("UserRepositoryImplH2-create-Error: " + e.getMessage());
        }
    }

    @Override
    public User getById(int id) {
        String query = "SELECT * FROM GESTION_PERSONAS.GP_USER WHERE id = ?";
        try {
            List<User> users = jdbcTemplate.query(query,new Object[]{id},new BeanPropertyRowMapper(User.class));
            return users.get(0);
        } catch (Exception e) {
            System.out.println("UserRepositoryImplH2-getById-Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<User> GetAll() {
        String query = "SELECT * FROM GESTION_PERSONAS.GP_USER";
        try {
            return jdbcTemplate.query(query,new BeanPropertyRowMapper(User.class));
        } catch (Exception e) {
            System.out.println("UserRepositoryImplH2-GetAll-Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void Update(User user) {
        String query = "UPDATE GESTION_PERSONAS.GP_USER SET name = ?, email = ? WHERE id = ?";
        try {
            jdbcTemplate.update(query, user.getName(), user.getEmail(), user.getId());
            System.out.println("Usuario actualizado exitosamente");
        }catch (Exception e){
            System.out.println("UserRepositoryImplH2-Update-Error: " + e.getMessage());
        }
    }

    @Override
    public void DeleteById(int id) {
        String query = "DELETE FROM GESTION_PERSONAS.GP_USER WHERE id = ?";
        try {
            jdbcTemplate.update(query, id);
            System.out.println("Usuario borrado exitosamente");
        }catch (Exception e){
            System.out.println("UserRepositoryImplH2-DeleteById-Error: " + e.getMessage());
        }
    }
}
 */
