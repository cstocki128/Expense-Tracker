package com.proyecto.finanzasPersonales.repository;

import com.proyecto.finanzasPersonales.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    public void create(Category category);
//    public T getById(int id);
//    public List<T> GetAll();
//    public void Update(Category category);
//    public void DeleteById(int id);
}
