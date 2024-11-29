package com.example.petshop.rest;

import com.example.petshop.entity.Voucher;
import com.example.petshop.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/voucher")
public class RestVoucherController {
    @Autowired
    private VoucherService voucherService;

    @GetMapping
    public List<Voucher> getVoucher() {
        return voucherService.getAllVoucher();
    }

    @GetMapping("/{id}")
    public Voucher getVoucherById(@PathVariable("id") String id) {
        return voucherService.getVoucherById(id);
    }

    @PostMapping
    public Voucher addVoucher(@RequestBody Voucher voucher) {
        return voucherService.addVoucher(voucher);
    }

//    @PutMapping("/{id}")
//    public Voucher updateVoucher(@PathVariable("id") String id, @RequestBody Voucher voucher) {
//        voucher.setId(id);
//        return voucherService.updateVoucher(voucher);
//    }

    @DeleteMapping("/{id}")
    public void deleteVoucher(@PathVariable("id") String id) {
        voucherService.deleteVoucher(id);
    }

    @DeleteMapping("/delete-all")
    public void deleteVoucher() {
        voucherService.deleteAllVoucher();
    }

    @PutMapping("/{id}")
    public Voucher updateVoucher(@PathVariable("id") String id, @RequestBody Voucher voucher) {
        voucher.setExpiryDate(voucher.getExpiryDate().plusDays(1));
        voucher.setVoucherID(id);
        return voucherService.updateVoucher(voucher);
    }
}
