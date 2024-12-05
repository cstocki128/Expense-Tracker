package com.proyecto.finanzasPersonales.service.impl;

import com.proyecto.finanzasPersonales.controller.dto.RequestExpenseDTO;
import com.proyecto.finanzasPersonales.controller.dto.CategoryDTO;
import com.proyecto.finanzasPersonales.controller.dto.ResponseExpenseDTO;
import com.proyecto.finanzasPersonales.controller.dto.UserDTO;
import com.proyecto.finanzasPersonales.entity.Category;
import com.proyecto.finanzasPersonales.entity.Expense;
import com.proyecto.finanzasPersonales.entity.User;
import com.proyecto.finanzasPersonales.entity.exception.ExpenseNotFoundException;
import com.proyecto.finanzasPersonales.entity.exception.UserNotFoundException;
import com.proyecto.finanzasPersonales.repository.CategoryRepository;
import com.proyecto.finanzasPersonales.repository.ExpenseRepository;
import com.proyecto.finanzasPersonales.repository.UserRepository;
import com.proyecto.finanzasPersonales.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExpenseServiceImpl<T extends Expense> implements ExpenseService {
    ExpenseRepository expenseRepository;
    CategoryRepository categoryRepository;
    UserRepository userRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ResponseExpenseDTO> getExpensesByUser(Long userId) throws Exception{
        log.debug("ExpenseService-getExpensesByUser executed");
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        List<Expense> expenses = expenseRepository.findByUser(user);
        return expenses.stream()
                .map(this::fromExpenseToResponseExpenseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseExpenseDTO> getExpenses() throws Exception{
        log.debug("ExpenseService-getExpenses executed");
        List<Expense> expenses = expenseRepository.findAll();
        return expenses.stream()
                .map(this::fromExpenseToResponseExpenseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseExpenseDTO getExpense(Long expenseId) throws Exception{
        log.debug("ExpenseService-getExpense executed");
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));
        return fromExpenseToResponseExpenseDTO(expense);
    }

    @Override
    public ResponseExpenseDTO addExpense(RequestExpenseDTO expenseDTO) throws Exception {
        log.debug("ExpenseService-addExpense executed");
        Expense expense = expenseRepository.save(fromRequestExpenseDTOToExpense(expenseDTO));
        return fromExpenseToResponseExpenseDTO(expense);
    }

    @Override
    public ResponseExpenseDTO updateExpense(Long id,RequestExpenseDTO expenseDTO) throws Exception {
        log.debug("ExpenseService-updateExpense executed");
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));
        expense.setUser(userRepository.findById(expenseDTO.getUser()).orElseThrow(() -> new UserNotFoundException("User not found")));
        expense.setCategory(categoryRepository.findById(expenseDTO.getCategory()).orElseThrow(() -> new RuntimeException("Category not found")));
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setDate(expenseDTO.getDate());
        return fromExpenseToResponseExpenseDTO(expenseRepository.save(expense));
    }

    @Override
    public void deleteExpense(Long expenseId) throws Exception{
        log.debug("ExpenseService-deleteExpense executed");
        expenseRepository.findById(expenseId).orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));
        expenseRepository.deleteById(expenseId);
    }

    @Override
    public List<ResponseExpenseDTO> getExpensesByDate(LocalDate date) throws Exception{
        log.debug("ExpenseService-getExpensesByDate executed");
        return expenseRepository.findByDate(date).stream()
                .map(this::fromExpenseToResponseExpenseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseExpenseDTO> getExpensesByCategory(Long id) throws Exception{
        log.debug("ExpenseService-getExpensesByCategory executed");
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return expenseRepository.findByCategory(category).stream()
                .map(this::fromExpenseToResponseExpenseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Double getTotalAmountExpenses(Long userId) throws Exception{
        log.debug("ExpenseService-getTotalAmountExpenses executed");
        return this.getExpensesByUser(userId).stream()
                .mapToDouble(ResponseExpenseDTO::getAmount)
                .reduce(0, Double::sum);
    }

    @Override
    public CategoryDTO getExpenseCategory(long id) throws Exception {
        log.debug("ExpenseService-getExpenseCategory executed");
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return CategoryDTO.builder()
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    public void createExpenseCategory(CategoryDTO categoryDTO) throws Exception {
        log.debug("ExpenseService-createExpenseCategory executed");
        categoryRepository.save(Category.builder()
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .build()
        );
    }

    @Override
    public void deleteExpenseCategory(long id) throws Exception {
        log.debug("ExpenseService-deleteExpenseCategory executed");
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.deleteById(id);
    }

    private ResponseExpenseDTO fromExpenseToResponseExpenseDTO(Expense expense) {
        return ResponseExpenseDTO.builder()
                .amount(expense.getAmount())
                .date(expense.getDate())
                .category(CategoryDTO.builder()
                        .name(expense.getCategory().getName())
                        .description(expense.getCategory().getDescription())
                        .build())
                .description(expense.getDescription())
                .user(UserDTO.builder()
                        .name(expense.getUser().getName())
                        .email(expense.getUser().getEmail())
                        .build())
                .build();
    }

    private Expense fromRequestExpenseDTOToExpense(RequestExpenseDTO expenseDTO) throws Exception {
        return Expense.builder()
                .amount(expenseDTO.getAmount())
                .date(expenseDTO.getDate())
                .category(categoryRepository.findById(expenseDTO.getCategory()).orElseThrow(() -> new RuntimeException("Category not found")))
                .description(expenseDTO.getDescription())
                .user(userRepository.findById(expenseDTO.getUser()).orElseThrow(() -> new UserNotFoundException("User not found")))
                .build();
    }

}
