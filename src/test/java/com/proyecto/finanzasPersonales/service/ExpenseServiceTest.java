package com.proyecto.finanzasPersonales.service;

import com.proyecto.finanzasPersonales.repository.ExpenseRepository;
import com.proyecto.finanzasPersonales.service.impl.ExpenseServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;

public class ExpenseServiceTest {
    @Mock
    private ExpenseRepository expenseDaoMock;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); //INICIA MOCKS
    }

    @Test
    @DisplayName("addExpenseTest.GetExpenses() - Test")
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
