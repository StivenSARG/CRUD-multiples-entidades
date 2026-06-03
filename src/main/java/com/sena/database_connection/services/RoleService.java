package com.sena.database_connection.services;

import org.springframework.stereotype.Service;

import com.sena.database_connection.repositories.RoleRepository;

@Service
public class RoleService {

    private RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }   

    
}
