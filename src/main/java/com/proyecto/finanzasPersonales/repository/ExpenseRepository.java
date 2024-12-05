package com.proyecto.finanzasPersonales.repository;

import com.proyecto.finanzasPersonales.entity.Category;
import com.proyecto.finanzasPersonales.entity.Expense;
import com.proyecto.finanzasPersonales.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);
    List<Expense> findByDate(LocalDate date);
    List<Expense> findByCategory(Category category);

//    public List<T> getAllExpenses();
//    public List<T> getExpensesByUser(int userId);
//    public T getExpenseById(int Id);
//    public Boolean addExpense(Expense expense);
//    public Boolean updateExpense(Expense expense);
//    public Boolean deleteExpense(int id);
//    public List<T> getExpensesByDate(int userId, LocalDate date);
//    public List<T> getExpensesByCategory(int userId, Category category);
}
