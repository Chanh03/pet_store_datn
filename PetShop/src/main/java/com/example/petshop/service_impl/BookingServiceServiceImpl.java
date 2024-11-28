package com.example.petshop.service_impl;

import com.example.petshop.repo.BookingServiceRepo;
import com.example.petshop.service.BookingServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceServiceImpl implements BookingServiceService {
    @Autowired
    private BookingServiceRepo bookingServiceRepo;
}
