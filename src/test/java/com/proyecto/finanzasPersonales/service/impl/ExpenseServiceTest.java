package com.proyecto.finanzasPersonales.service.impl;

import com.proyecto.finanzasPersonales.controller.dto.ResponseExpenseDTO;
import com.proyecto.finanzasPersonales.entity.Category;
import com.proyecto.finanzasPersonales.entity.Expense;
import com.proyecto.finanzasPersonales.entity.User;
import com.proyecto.finanzasPersonales.repository.ExpenseRepository;
import com.proyecto.finanzasPersonales.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ExpenseServiceTest {

    @MockBean
    private ExpenseRepository expenseRepositoryMock;

    @Autowired
    private ExpenseServiceImpl expenseService;

    private Expense expense1;
    private Expense expense2;

    @BeforeEach
    void init() {
        Category cat = Category.builder().name("Cat1").description("cat1Desc").build();
        User user = User.builder().name("matias").email("email@email.com").build();
        expense1 = Expense.builder().id(1L).amount(1000.0).description("expense1").date(LocalDate.now()).user(user).category(cat).build();
        expense2 = Expense.builder().id(2L).amount(2000.0).description("expense2").date(LocalDate.now()).user(user).category(cat).build();
    }

    @Test
    @DisplayName("ExpenseService.GetExpenses() - Test")
    public void getExpensesTest() throws Exception {
        //GIVEN
        List<Expense> expenses = new ArrayList<>();
        expenses.add(expense1);
        expenses.add(expense2);

        //WHEN
        when(expenseRepositoryMock.findAll()).thenReturn(expenses);
        List<ResponseExpenseDTO> expenseDTOS = expenseService.getExpenses();

        //THEN
        assertEquals(expenseDTOS.size(),expenses.size());
        assertEquals(expenseDTOS.get(0).getAmount(),expense1.getAmount());
        assertEquals(expenseDTOS.get(1).getAmount(),expense2.getAmount());
        assertEquals(expenseDTOS.get(0).getDescription(),expense1.getDescription());
        assertEquals(expenseDTOS.get(1).getDescription(),expense2.getDescription());
        verify(expenseRepositoryMock,atMostOnce()).findAll();
    }


    @Test
    @DisplayName("ExpenseService.getTotalAmountExpenses() - Test")
    public void addExpenseTest() throws Exception { //STUBBING
        //GIVEN
        long id = 0;
        ExpenseService expenseServiceMock = mock(ExpenseServiceImpl.class);
        when(expenseServiceMock.getTotalAmountExpenses(id)).thenReturn(1000.0);

        //WHEN

        //THEN
        assertEquals(1000.0,expenseServiceMock.getTotalAmountExpenses(id));
        verify(expenseServiceMock,atLeastOnce()).getTotalAmountExpenses(id);


    }
}
