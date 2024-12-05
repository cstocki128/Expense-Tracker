//SE COMENTA POR IMPLEMENTACION DE SPRING DATA JPA

/* package com.proyecto.finanzasPersonales.repository.impl;

import com.proyecto.finanzasPersonales.entity.Category;
import com.proyecto.finanzasPersonales.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Slf4j
@Repository
public class CategoryRepositoryImplH2 implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepositoryImplH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Category category) {
        String query = "INSERT INTO GESTION_PERSONAS.GP_CATEGORY (name, description) values (?, ?)";
        try {
            jdbcTemplate.update(query, category.getName(), category.getDescription());
            System.out.println("Categoría creada exitosamente");
        }catch (Exception e){
            System.out.println("CategoryRepositoryImplH2-create-Error: " + e.getMessage());
        }
    }

    @Override
    public Category getById(int id) {
        String query = "SELECT * FROM GESTION_PERSONAS.GP_CATEGORY WHERE id = ?";
        try {
            List<Category> categories = jdbcTemplate.query(query,new Object[]{id},new BeanPropertyRowMapper(Category.class));
            return categories.get(0);
        } catch (Exception e) {
            System.out.println("CategoryRepositoryImplH2-getById-Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Category> GetAll() {
        String query = "SELECT * FROM GESTION_PERSONAS.GP_CATEGORY";
        try {
            return jdbcTemplate.query(query,new BeanPropertyRowMapper(Category.class));
        } catch (Exception e) {
            System.out.println("CategoryRepositoryImplH2-GetAll-Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void Update(Category category) {
        String query = "UPDATE GESTION_PERSONAS.GP_CATEGORY SET name = ?, description = ? WHERE id = ?";
        try {
            jdbcTemplate.update(query, category.getName(), category.getDescription(), category.getId());
            System.out.println("Categoría actualizada exitosamente");
        }catch (Exception e){
            System.out.println("CategoryRepositoryImplH2-Update-Error: " + e.getMessage());
        }
    }

    @Override
    public void DeleteById(int id) {
        String query = "DELETE FROM GESTION_PERSONAS.GP_CATEGORY WHERE id = ?";
        try {
            jdbcTemplate.update(query, id);
            System.out.println("Categoría borrada exitosamente");
        }catch (Exception e){
            System.out.println("CategoryRepositoryImplH2-DeleteById-Error: " + e.getMessage());
        }
    }
}
 */
