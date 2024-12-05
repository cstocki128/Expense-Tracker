package com.proyecto.finanzasPersonales.service.impl;

import com.proyecto.finanzasPersonales.repository.ExpenseRepository;
import com.proyecto.finanzasPersonales.service.ExpenseService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ExpenseServiceTest {
    @MockBean
    private ExpenseRepository expenseRepositoryMock;

    @Autowired
    private ExpenseServiceImpl expenseService;

    @Test
    @DisplayName("ExpenseService.GetExpenses() - Test")
    public void addExpenseTest() throws Exception {
        //GIVEN
        long id = 0;
        ExpenseService expenseServiceMock = Mockito.mock(ExpenseServiceImpl.class);
        Mockito.when(expenseServiceMock.getTotalAmountExpenses(id)).thenReturn(1000.0);

        //WHEN

        //THEN
        Assertions.assertEquals(1000.0,expenseServiceMock.getTotalAmountExpenses(id));
        Mockito.verify(expenseServiceMock,Mockito.atLeastOnce()).getTotalAmountExpenses(id);


    }

    @Test
    @DisplayName("getExpensesTest.GetExpenses() - Test")
    @Disabled
    public void getExpensesTest() throws Exception {
        //GIVEN

        //WHEN

        //THEN

    }


    @Test
    @DisplayName("updateExpenseTest.GetExpenses() - Test")
    @Disabled
    public void updateExpenseTest() throws Exception {
        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    @DisplayName("deleteExpenseTest.GetExpenses() - Test")
    @Disabled
    public void deleteExpenseTest() throws Exception {
        //GIVEN

        //WHEN

        //THEN
    }
}
