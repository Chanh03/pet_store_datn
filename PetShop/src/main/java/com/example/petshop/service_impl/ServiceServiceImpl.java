package com.example.petshop.service_impl;

import com.example.petshop.repo.ServiceRepo;
import com.example.petshop.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepo serviceRepo;
}
