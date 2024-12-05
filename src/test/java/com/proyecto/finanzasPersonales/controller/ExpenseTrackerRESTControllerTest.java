package com.proyecto.finanzasPersonales.controller;

import com.proyecto.finanzasPersonales.controller.dto.ResponseExpenseDTO;
import com.proyecto.finanzasPersonales.service.ExpenseService;
import com.proyecto.finanzasPersonales.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ExpenseTrackerRESTController.class)
class ExpenseTrackerRESTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExpenseService expenseServiceMock;
    @MockBean
    private UserService userServiceMock;

    private ResponseExpenseDTO sampleExpenseDTO;

    @BeforeEach
    void init() {
        sampleExpenseDTO = ResponseExpenseDTO.builder()
                .amount(1000.0)
                .description("Prueba")
                .date(LocalDate.now())
                .build();
    }


    @Test
    @DisplayName("GET - /api/v1/expenseTracker/expenses TEST")
    void  getAll_ReturnListOfExpenses() throws Exception {
        //GIVEN
        List<ResponseExpenseDTO> expenses = Collections.singletonList(sampleExpenseDTO);
        when(expenseServiceMock.getExpenses()).thenReturn(expenses).thenThrow();
        when(expenseServiceMock.getExpensesByUser(anyLong())).thenReturn(expenses);
        when(expenseServiceMock.getExpensesByCategory(anyLong())).thenReturn(expenses);

        //WHEN

        //THEN
        mockMvc.perform(
                get("/api/v1/expenseTracker/expenses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(1000.0)))
                .andExpect(jsonPath("$[0].description", is("Prueba")));

        mockMvc.perform(
                        get("/api/v1/expenseTracker/expenses?userId=1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(1000.0)))
                .andExpect(jsonPath("$[0].description", is("Prueba")));

        mockMvc.perform(
                        get("/api/v1/expenseTracker/expenses?categoryId=1")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(1000.0)))
                .andExpect(jsonPath("$[0].description", is("Prueba")));
    }
}