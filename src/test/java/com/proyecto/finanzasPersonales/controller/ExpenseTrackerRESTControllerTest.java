package com.proyecto.finanzasPersonales.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.proyecto.finanzasPersonales.controller.dto.RequestExpenseDTO;
import com.proyecto.finanzasPersonales.controller.dto.ResponseExpenseDTO;
import com.proyecto.finanzasPersonales.controller.dto.UserDTO;
import com.proyecto.finanzasPersonales.service.ExpenseService;
import com.proyecto.finanzasPersonales.service.UserService;
import org.h2.command.dml.MergeUsing;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ExpenseTrackerRESTController.class)
class ExpenseTrackerRESTControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @MockBean
    private ExpenseService expenseServiceMock;
    @MockBean
    private UserService userServiceMock;

    private ResponseExpenseDTO sampleResponseExpDTO;
    private RequestExpenseDTO sampleRequestExpDTO;
    private UserDTO sampleUserDTO;

    @BeforeEach
    void init() {
        //Inicializo objectMapper para poder parsear a JSON.
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        sampleResponseExpDTO = ResponseExpenseDTO.builder()
                .amount(1000.0)
                .description("Prueba")
                .date(LocalDate.now())
                .build();

        sampleRequestExpDTO = RequestExpenseDTO.builder()
                .user(1l)
                .amount(sampleResponseExpDTO.getAmount())
                .category(1l)
                .date(sampleResponseExpDTO.getDate())
                .description(sampleResponseExpDTO.getDescription())
                .build();

        sampleUserDTO = UserDTO.builder().name("Matias").email("email@email.com").build();
    }


    @Test
    @DisplayName("GET - /api/v1/expenseTracker/expenses TEST")
    void  getAll_ReturnListOfExpenses() throws Exception {
        //GIVEN
        List<ResponseExpenseDTO> expenses = Collections.singletonList(sampleResponseExpDTO);

        //WHEN
        when(expenseServiceMock.getExpenses()).thenReturn(expenses).thenThrow();
        when(expenseServiceMock.getExpensesByUser(anyLong())).thenReturn(expenses);
        when(expenseServiceMock.getExpensesByCategory(anyLong())).thenReturn(expenses);

        //THEN
        mockMvc.perform(
                get("/api/v1/expenseTracker/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(sampleResponseExpDTO.getAmount())))
                .andExpect(jsonPath("$[0].description", is(sampleResponseExpDTO.getDescription())));

        mockMvc.perform(
                        get("/api/v1/expenseTracker/expenses?userId=1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(sampleResponseExpDTO.getAmount())))
                .andExpect(jsonPath("$[0].description", is(sampleResponseExpDTO.getDescription())));

        mockMvc.perform(
                        get("/api/v1/expenseTracker/expenses?categoryId=1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(sampleResponseExpDTO.getAmount())))
                .andExpect(jsonPath("$[0].description", is(sampleResponseExpDTO.getDescription())));
    }

    @Test
    @DisplayName("POST - /api/v1/expenseTracker/expenses TEST")
    void create() throws Exception {
        //GIVEN


        //WHEN
        when(expenseServiceMock.addExpense(any(RequestExpenseDTO.class))).thenReturn(sampleResponseExpDTO);

        //THEN
        mockMvc.perform(
                    post("/api/v1/expenseTracker/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleRequestExpDTO))
                )
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.amount",is(sampleRequestExpDTO.getAmount())))
                        .andExpect(jsonPath("$.description", is(sampleRequestExpDTO.getDescription())));
    }

    @Test
    void getUser() throws Exception {
        //GIVEN

        //WHEN
        when(userServiceMock.get(anyLong())).thenReturn(sampleUserDTO);

        //THEN
        mockMvc.perform(
                get("/api/v1/expenseTracker/user/1" )
                        .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(jsonPath("$.name",is(sampleUserDTO.getName())))
                .andExpect(jsonPath("$.email", is(sampleUserDTO.getEmail())));
    }
}