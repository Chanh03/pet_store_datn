package com.example.petshop.service_impl;

import com.example.petshop.entity.Voucher;
import com.example.petshop.repo.VoucherRepo;
import com.example.petshop.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherRepo voucherRepo;

    @Override
    public List<Voucher> getAllVoucher() {
        return voucherRepo.findAll();
    }

    @Override
    public Voucher getVoucherById(String id) {
        return voucherRepo.findById(id).orElse(null);
    }

    @Override
    public Voucher addVoucher(Voucher voucher) {
        return voucherRepo.save(voucher);
    }

    @Override
    public Voucher updateVoucher(Voucher voucher) {
        return voucherRepo.save(voucher);
    }

    @Override
    public void deleteVoucher(String id) {
        voucherRepo.deleteById(id);
    }

    @Override
    public void deleteAllVoucher() {
        voucherRepo.deleteAll();
    }
}
