package com.proyecto.finanzasPersonales.service;

import com.proyecto.finanzasPersonales.controller.dto.RequestExpenseDTO;
import com.proyecto.finanzasPersonales.controller.dto.CategoryDTO;
import com.proyecto.finanzasPersonales.controller.dto.ResponseExpenseDTO;
import com.proyecto.finanzasPersonales.entity.Category;

import java.time.LocalDate;
import java.util.ArrayList;


public interface ExpenseService {
    public ArrayList<ResponseExpenseDTO> getExpenses() throws Exception;
    public ArrayList<ResponseExpenseDTO> getExpensesByUser(Long id) throws Exception;
    public ResponseExpenseDTO getExpense(Long expenseId) throws Exception;
    public ResponseExpenseDTO addExpense(RequestExpenseDTO expense) throws Exception;
    public ResponseExpenseDTO updateExpense(Long id, RequestExpenseDTO expense) throws Exception;
    public void deleteExpense(Long expenseId) throws Exception;
    public CategoryDTO getExpenseCategory(long id) throws Exception;
    public void createExpenseCategory(CategoryDTO categoryDTO) throws Exception;
    public void deleteExpenseCategory(long id) throws Exception;
    public ArrayList<ResponseExpenseDTO> getExpensesByDate(LocalDate date) throws Exception;
    public ArrayList<ResponseExpenseDTO> getExpensesByCategory( Long id) throws Exception;
    public double getTotalAmountExpenses(Long userId) throws Exception;
}
