package com.proyecto.finanzasPersonales.entity.exception;

public class ExpenseNotFoundException extends Exception{
    public ExpenseNotFoundException(String message) {
        super(message);
    }
}
