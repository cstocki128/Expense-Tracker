//SE COMENTA POR IMPLEMENTACION DE SPRING DATA JPA

/* package com.proyecto.finanzasPersonales.repository.impl.mapper;

import com.proyecto.finanzasPersonales.entity.Expense;
import com.proyecto.finanzasPersonales.repository.CategoryRepository;
import com.proyecto.finanzasPersonales.repository.UserRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ExpenseMapper implements RowMapper<Expense> {
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;

    public ExpenseMapper(UserRepository userRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
        Expense expense = new Expense();
        expense.setId(rs.getInt("id"));
        expense.setAmount(rs.getDouble("amount"));
        expense.setDate(rs.getDate ("DATE").toLocalDate());
        expense.setCategory(categoryRepository.getById(rs.getInt("GP_CATEGORY")));
        expense.setDescription(rs.getString("description"));
        expense.setUser(userRepository.getById(rs.getInt("GP_USER")));
        return expense;
    }
}
*/