package com.proyecto.finanzasPersonales.repository;

import com.proyecto.finanzasPersonales.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    public void create(T user);
//    public T getById(int id);
//    public List<T> GetAll();
//    public void Update(T user);
//    public void DeleteById(int id);
}
