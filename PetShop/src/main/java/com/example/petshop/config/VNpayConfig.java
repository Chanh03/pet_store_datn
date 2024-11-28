package com.example.petshop.config;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;

import com.example.petshop.utils.VNPayUtil;

@Configuration
public class VNpayConfig {
    private String vnp_TmnCode = "FUFQ5H41";  // Mã Website (TmnCode)
    private String hashSecret = "UVJOQCU3ZTK0ZT8EGZFEQ42SAT1ZNFP8";  // Secret Key
    private String payUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";  // URL thanh toán
    private String apiUrl = "https://sandbox.vnpayment.vn/merchant_webapi/api/transaction";  // URL API
    private String vnp_ReturnUrl = "http://localhost:8080/api/v1/payment/vn-pay-callback";  // URL trả về
    private String vnp_Command = "pay";
    private String orderType = "other-type";
    private String vnp_Version = "2.1.0";

    // Getter và Setter
    public String getTmnCode() {
        return vnp_TmnCode;
    }

    public String getHashSecret() {
        return hashSecret;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getReturnUrl() {
        return vnp_ReturnUrl;
    }

    public Map<String, String> getVNPayConfig() {
        Map<String, String> vnpParamsMap = new HashMap<>();
        vnpParamsMap.put("vnp_Version", this.vnp_Version);
        vnpParamsMap.put("vnp_Command", this.vnp_Command);
        vnpParamsMap.put("vnp_TmnCode", this.vnp_TmnCode);
        vnpParamsMap.put("vnp_CurrCode", "VND");
        vnpParamsMap.put("vnp_TxnRef", VNPayUtil.getRandomNumber(8));
        vnpParamsMap.put("vnp_OrderInfo", "Thanh toán đơn hàng:" + VNPayUtil.getRandomNumber(8));
        vnpParamsMap.put("vnp_OrderType", this.orderType);
        vnpParamsMap.put("vnp_Locale", "vn");
        vnpParamsMap.put("vnp_ReturnUrl", this.vnp_ReturnUrl);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnpCreateDate = formatter.format(calendar.getTime());
        vnpParamsMap.put("vnp_CreateDate", vnpCreateDate);
        calendar.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(calendar.getTime());
        vnpParamsMap.put("vnp_ExpireDate", vnp_ExpireDate);
        return vnpParamsMap;
    }
}
