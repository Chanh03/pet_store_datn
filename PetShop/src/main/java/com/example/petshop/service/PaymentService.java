package com.example.petshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.petshop.config.VNpayConfig;
import com.example.petshop.entity.DTO.OrderProductDetailDTO;
import com.example.petshop.utils.VNPayUtil;

@Service
public class PaymentService {
    @Autowired
    private VNpayConfig vnPayConfig;

    public String createVnPayPayment(String money, String username, List<OrderProductDetailDTO> productDetails, String address) {
        // Tính toán số tiền theo yêu cầu của VNPay (tính theo đơn vị 100 đồng)
        long amount = Long.parseLong(money) * 100L;
        // Mã ngân hàng mặc định

        // Lấy cấu hình VNPay từ VNpayConfig
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));

        StringBuilder orderInfo = new StringBuilder();
        orderInfo.append("Username: ").append(username).append(";");
        orderInfo.append("Address: ").append(address).append(";");
        for (int i = 0; i < productDetails.size(); i++) {
            OrderProductDetailDTO item = productDetails.get(i);
            orderInfo.append("Id:")
                    .append(item.getProductID())
                    .append(" price:")
                    .append(item.getPrice() * item.getQuantity())  // Tính tổng tiền cho sản phẩm
                    .append(" quantity:")
                    .append(item.getQuantity())
                    .append(" ; ");
            System.out.println(item.getProductID());
        }
        vnpParamsMap.put("vnp_OrderInfo", orderInfo.toString());
        // Kiểm tra và thêm mã ngân hàng nếu không rỗng


        // Đặt địa chỉ IP người dùng
        vnpParamsMap.put("vnp_IpAddr", "http://localhost:8080/api/v1/payment/vn-pay?amount=" + amount + "&bankCode=NCB");

        // Xây dựng URL truy vấn cho VNPay
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);

        // Tạo khóa bảo mật
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getHashSecret(), hashData);

        // Thêm mã băm bảo mật vào URL thanh toán
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;

        // Tạo URL thanh toán cuối cùng
        String paymentUrl = vnPayConfig.getPayUrl() + "?" + queryUrl;

        // Trả về URL thanh toán
        return paymentUrl;
    }
}
