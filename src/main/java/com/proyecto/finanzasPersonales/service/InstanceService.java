package com.proyecto.finanzasPersonales.service;

import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@FunctionalInterface
public interface InstanceService {
    public Type getLastInstance();
}
