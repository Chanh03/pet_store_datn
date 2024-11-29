package com.example.petshop.service;

import com.example.petshop.entity.Voucher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VoucherService {
    List<Voucher> getAllVoucher();

    Voucher getVoucherById(String id);

    Voucher addVoucher(Voucher voucher);

    Voucher updateVoucher(Voucher voucher);

    void deleteVoucher(String id);

    void deleteAllVoucher();
}
