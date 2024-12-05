package com.proyecto.finanzasPersonales.controller;

import com.proyecto.finanzasPersonales.controller.dto.CategoryDTO;
import com.proyecto.finanzasPersonales.controller.dto.RequestExpenseDTO;
import com.proyecto.finanzasPersonales.controller.dto.ResponseExpenseDTO;
import com.proyecto.finanzasPersonales.controller.dto.UserDTO;
import com.proyecto.finanzasPersonales.service.ExpenseService;

import com.proyecto.finanzasPersonales.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/expenseTracker")
public class ExpenseTrackerRESTController <T extends Object> { //ctrl + shift + T --> crear test
    ExpenseService expenseService;
    UserService userService;

    public ExpenseTrackerRESTController(ExpenseService expenseService, UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        log.info("Endpoint enabled: /api/v1/expenseTracker");
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<ResponseExpenseDTO>> getAll(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long categoryId) throws Exception {

            log.debug("GET - /api/v1/expenseTracker/expenses EXECUTED");
            log.debug("userId=" + userId);
            log.debug("categoryId=" + categoryId);

            if (userId != null) {
                return new ResponseEntity<>(expenseService.getExpensesByUser(userId), HttpStatus.OK);
            } else if (categoryId != null) {
                return new ResponseEntity<>(expenseService.getExpensesByCategory(categoryId), HttpStatus.OK);
            }

            return new ResponseEntity<>(expenseService.getExpenses(), HttpStatus.OK);

    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<ResponseExpenseDTO> getById(@PathVariable Long id) throws Exception {
        log.debug("GET - /api/v1/expenseTracker/expenses/"+id+" EXECUTED");
        ResponseExpenseDTO responseExpenseDTO = expenseService.getExpense(id);
        if (responseExpenseDTO == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(responseExpenseDTO, HttpStatus.OK);
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<ResponseExpenseDTO> update(@PathVariable Long id, @RequestBody RequestExpenseDTO expense) throws Exception {
        log.debug("PUT - /api/v1/expenseTracker/expenses/"+id+" EXECUTED");
        log.debug("Body= "+expense);
        ResponseExpenseDTO responseExpenseDTO = expenseService.updateExpense(id,expense);
        if (responseExpenseDTO == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(responseExpenseDTO, HttpStatus.OK);
    }

    @PostMapping("/expenses")
    public ResponseEntity<ResponseExpenseDTO> create(@RequestBody RequestExpenseDTO expense) throws Exception {
        log.debug("POST - /api/v1/expenseTracker/expenses/ EXECUTED");
        log.debug("Body= "+expense);
        ResponseExpenseDTO responseExpenseDTO = expenseService.addExpense(expense);
        if (responseExpenseDTO == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(responseExpenseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        log.debug("DELETE - /api/v1/expenseTracker/expenses/"+id+" EXECUTED");
        expenseService.deleteExpense(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    ///expenses/category

    @GetMapping("/expenses/category/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) throws Exception {
        log.debug("GET - /api/v1/expenseTracker/expenses/category/"+id+" EXECUTED");
        CategoryDTO categoryDTO = expenseService.getExpenseCategory(id);
        if (categoryDTO == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(categoryDTO, HttpStatus.OK);
    }

    @PostMapping("/expenses/category")
    public ResponseEntity<Void> createCategory(@RequestBody CategoryDTO categoryDTO) throws Exception {
        log.debug("POST - /api/v1/expenseTracker/expenses/category EXECUTED");
        log.debug("Body= "+categoryDTO);
        expenseService.createExpenseCategory(categoryDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/expenses/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) throws Exception {
        log.debug("DELETE - /api/v1/expenseTracker/expenses/category/"+id+" EXECUTED");
        expenseService.deleteExpenseCategory(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    ///user/

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) throws Exception {
        log.debug("GET - /api/v1/expenseTracker/user/"+id+" EXECUTED");
        UserDTO userDTO = userService.get(id);
        if (userDTO == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        return new ResponseEntity(userDTO, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) throws Exception {
        log.debug("POST - /api/v1/expenseTracker/user/ EXECUTED");
        log.debug("Body= "+ userDTO);
        userService.create(userDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Void> UpdateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) throws Exception {
        log.debug("PUT - /api/v1/expenseTracker/user/"+id+" EXECUTED");
        log.debug("Body= "+ userDTO);
        userService.update(id, userDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> userDelete(@PathVariable Long id) throws Exception {
        log.debug("DELETE - /api/v1/expenseTracker/user/"+id+" EXECUTED");
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
