//SE COMENTA POR IMPLEMENTACION DE SPRING DATA JPA

/* package com.proyecto.finanzasPersonales.repository.impl;

import com.proyecto.finanzasPersonales.entity.Category;
import com.proyecto.finanzasPersonales.entity.Expense;
import com.proyecto.finanzasPersonales.repository.ExpenseRepository;
import com.proyecto.finanzasPersonales.repository.impl.mapper.ExpenseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Repository
public class ExpenseRepositoryImplH2 implements ExpenseRepository {
    private final JdbcTemplate jdbcTemplate;
    private ExpenseMapper expenseMapper;

    public ExpenseRepositoryImplH2(JdbcTemplate jdbcTemplate, ExpenseMapper expenseMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.expenseMapper = expenseMapper;
    }

    @Override
    public List<Expense> getAllExpenses() {
        String query = "SELECT * FROM GESTION_PERSONAS.GP_EXPENSE";
        try {
            return jdbcTemplate.query(query,expenseMapper);
        }catch (Exception e) {
            System.out.println("ExpenseRepositoryImplH2-getAllExpenses-Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Expense> getExpenses(int userId) {
        String query = "SELECT * FROM GESTION_PERSONAS.GP_EXPENSE WHERE GP_USER = ?";
        try {
            return jdbcTemplate.query(query,new Object[]{userId},expenseMapper);
        }catch (Exception e) {
            System.out.println("ExpenseRepositoryImplH2-getExpensesByUser-Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Expense getExpenseById(int id) {
        String query = "SELECT * FROM GESTION_PERSONAS.GP_EXPENSE WHERE ID = ?";
        try {
            List<Expense> expenses = jdbcTemplate.query(query,new Object[]{id},expenseMapper);
            return expenses.get(0);
        }catch (Exception e) {
            System.out.println("ExpenseRepositoryImplH2-getExpenseById-Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean addExpense(Expense expense) {
        String query = "INSERT INTO GESTION_PERSONAS.GP_EXPENSE (amount, date, GP_CATEGORY, description, GP_USER) values (?, ?, ?, ?, ?)";
        try {
            int result = jdbcTemplate.update(query, expense.getAmount(), expense.getDate(), expense.getCategory().getId(), expense.getDescription(), expense.getUser().getId());
            if (result > 0) return true;
        } catch (Exception e) {
            System.out.println("ExpenseRepositoryImplH2-addExpense-Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean updateExpense(Expense expense) {
        String query = "UPDATE GESTION_PERSONAS.GP_EXPENSE SET amount = ?, date = ?, GP_CATEGORY = ?, description = ?, GP_USER = ? WHERE id = ?";
        try {
            int result = jdbcTemplate.update(query, expense.getAmount(), expense.getDate(), expense.getCategory().getId(), expense.getDescription(), expense.getUser().getId(), expense.getId());
            if (result > 0) return true;
        } catch (Exception e) {
            System.out.println("ExpenseRepositoryImplH2-updateExpense-Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean deleteExpense(int id) {
        String query = "DELETE FROM GESTION_PERSONAS.GP_EXPENSE WHERE id = ?";
        try {
            int result = jdbcTemplate.update(query,id);
            if (result > 0) return true;
        } catch (Exception e) {
            System.out.println("ExpenseRepositoryImplH2-deleteExpense-Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Expense> getExpensesByDate(int userId, LocalDate date) {
        String query = "SELECT * FROM GESTION_PERSONAS.GP_EXPENSE WHERE GP_USER = ? and date = ?";
        try {
            return jdbcTemplate.query(query,new Object[]{userId,date},expenseMapper);
        }catch (Exception e) {
            System.out.println("ExpenseRepositoryImplH2-getExpensesByDate-Error: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Expense> getExpensesByCategory(int userId, Category category) {
        String query = "SELECT * FROM GESTION_PERSONAS.GP_EXPENSE WHERE id = ? and GP_CATEGORY = ?";
        try {
            return jdbcTemplate.query(query,new Object[]{userId, category.getId()},expenseMapper);
        }catch (Exception e) {
            System.out.println("ExpenseRepositoryImplH2-getExpensesByCategory-Error: " + e.getMessage());
            return null;
        }
    }
}

*/