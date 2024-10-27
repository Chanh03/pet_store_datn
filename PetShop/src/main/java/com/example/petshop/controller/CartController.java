//package com.example.petshop.controller;
//
//import com.example.petshop.entity.Voucher;
//import com.example.petshop.service.VoucherService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.List;
//
//@Controller
//public class CartController {
//    @Autowired
//    private VoucherService voucherService;
//
//    @RequestMapping("/cart-detail")
//    public String cart(Model model) {
//        List<Voucher> vocher = voucherService.getAllVoucher();
//        model.addAttribute("vouchers", vocher);
//
//        return "/layout/_cartDetail";
//    }
//
//    @PostMapping("/applyVoucher")
//    public String applyVoucher(@RequestParam("id") String id, RedirectAttributes redirectAttributes) {
//        System.out.println("Received voucher ID: " + id); // Để kiểm tra ID được nhận
//
//        Voucher voucher = voucherService.getVoucherById(id);
//
//        if (voucher != null) {
//            redirectAttributes.addFlashAttribute("successMessage", "Mã giảm giá đã được áp dụng thành công!");
//            redirectAttributes.addFlashAttribute("discountAmount", voucher.getDiscount());
//        } else {
//            redirectAttributes.addFlashAttribute("errorMessage", "Mã giảm giá không hợp lệ!");
//        }
//
//        return "redirect:/cart-detail";
//    }
//
//
//}
