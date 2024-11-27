package com.example.petshop.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.mail.Session;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.petshop.entity.Order;
import com.example.petshop.entity.OrderProductDetail;
import com.example.petshop.entity.OrderStatus;
import com.example.petshop.entity.User;
import com.example.petshop.entity.DTO.OrderProductDetailDTO;
import com.example.petshop.entity.DTO.PaymentDTO;
import com.example.petshop.service.OrderProductDetailService;
import com.example.petshop.service.OrderService;
import com.example.petshop.service.OrderStatusService;
import com.example.petshop.service.PaymentService;
import com.example.petshop.service.PaymentStatusService;
import com.example.petshop.service.ProductService;
import com.example.petshop.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderProductDetailService orderProductDetailService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    OrderStatusService orderStatusService;

    @Autowired
    PaymentStatusService paymentStatusService;

    //	@GetMapping("/vn-pay")
//    public ResponseEntity<Object> pay(@RequestParam("amount") String amount) {
//        String paymentUrl = paymentService.createVnPayPayment(amount);
//        PaymentDTO dto = new PaymentDTO();
//        dto.setCode("200");
//        dto.setMessage("Success");
//        dto.setPaymentUrl(paymentUrl);
//        return ResponseEntity.ok(dto); // Trả về URL thanh toán cho người dùng
//    }
    @PostMapping("/vn-pay")
    public ResponseEntity<Object> pay(@RequestBody PaymentDTO paymentRequest) {
        // Dữ liệu giỏ hàng từ client
        String username = paymentRequest.getUserName();
        System.out.println(username);
        long totalAmount = paymentRequest.getAmount();
        System.out.println(totalAmount);
        List<OrderProductDetailDTO> items = paymentRequest.getProductDetails();
        String address = paymentRequest.getShippingAddress();
        // Xử lý dữ liệu giỏ hàng (nếu cần lưu vào database, thêm logic ở đây)
        System.out.println(address);
        // Tạo URL thanh toán với tổng tiền
        String paymentUrl = paymentService.createVnPayPayment(String.valueOf(totalAmount), username, items, address);

        // Tạo DTO phản hồi
        PaymentDTO dto = new PaymentDTO();
        dto.setUserName(username);
        dto.setCode("200");
        dto.setMessage("Success");
        dto.setPaymentUrl(paymentUrl);

        return ResponseEntity.ok(dto); // Trả về URL thanh toán
    }

    @GetMapping("/vn-pay-callback")
    public ModelAndView payCallbackHandler(HttpServletRequest request) throws ParseException {
        // Lấy các tham số từ VNPay gửi về
        String status = request.getParameter("vnp_ResponseCode");
        String maDonHang = request.getParameter("vnp_TxnRef");
        String amount = request.getParameter("vnp_Amount");
        String date = request.getParameter("vnp_PayDate");
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date parsedDate = originalFormat.parse(date);
        String orderInfo = request.getParameter("vnp_OrderInfo");

        Order order = new Order();
        // Chuyển đổi số tiền từ kiểu String sang Integer (lưu ý rằng VNPay trả số tiền với đơn vị 100 đồng)
        long amountInt = Long.parseLong(amount) / 100;

        // Tạo DTO để trả về
        PaymentDTO dto = new PaymentDTO();
        dto.setProductDetails(new ArrayList<>());  // Khởi tạo danh sách sản phẩm nếu chưa có

        // Nếu trạng thái thanh toán thành công (ResponseCode = "00")
        if ("00".equals(status)) {
            // Gán giá trị vào DTO
            String username = extractUsernameFromOrderInfo(orderInfo);
            dto.setCode("200");
            dto.setAmount(String.valueOf(amountInt));
            dto.setMessage("Success");
            dto.setMaDonHang(maDonHang);
            dto.setOrderDate(parsedDate);
            dto.setShippingAddress(extractAddressFromOrderInfo(orderInfo));
            order.setOrderDate(dto.getOrderDate());
            order.setShippingAddress(extractAddressFromOrderInfo(orderInfo));
            order.setTotalAmount(amountInt);
            order.setUserName(userService.findByUsername(username));
            order.setEnable(true);
            order.setOrderStatusID(orderStatusService.findById(1));
            order.setPaymentStatusID(paymentStatusService.findById(2));
            System.out.println("HELOOOOOOOOOOOOOOOO" + order.getPaymentStatusID().getStatusPayment());
            orderService.save(order);
            // Xử lý thêm nếu có thông tin sản phẩm trong orderInfo
            if (orderInfo != null && !orderInfo.isEmpty()) {
                String[] orderInfoParts = orderInfo.split(";");  // Phân tách theo dấu ';'

                // Duyệt qua từng phần tử trong orderInfo
                for (String part : orderInfoParts) {
                    // In ra để kiểm tra thông tin
                    System.out.println("Order Part: " + part);

                    // Khởi tạo đối tượng OrderProductDetailDTO cho mỗi sản phẩm
                    OrderProductDetailDTO productDetail = new OrderProductDetailDTO();
                    OrderProductDetail orDetail = new OrderProductDetail();
                    // Tách thông tin chi tiết sản phẩm (Id, price, quantity)
                    String[] productDetails = part.split(" ");  // Tách theo dấu cách

                    // Kiểm tra số phần tử trước khi truy cập chúng
                    for (String detail : productDetails) {

                        productDetail.setOrderID(Integer.parseInt(maDonHang));
                        orDetail.setOrderID(order);
                        // Kiểm tra nếu phần tử có chứa "Id:"
                        if (detail.contains("Id:")) {
                            String[] idParts = detail.split(":");
                            if (idParts.length > 1) {
                                String productId = idParts[1].trim();
                                productDetail.setProductID(productId);  // Gán ID sản phẩm
                                orDetail.setProductID(productService.getById(Integer.parseInt(productId)));
                            }
                        }
                        // Kiểm tra nếu phần tử có chứa "price:"
                        else if (detail.contains("price:")) {
                            String[] priceParts = detail.split(":");
                            if (priceParts.length > 1) {
                                String price = priceParts[1].trim();
                                if (price != null && !price.isEmpty()) {
                                    productDetail.setPrice(Integer.parseInt(price));  // Gán giá sản phẩm
                                    orDetail.setPrice(Integer.parseInt(price));
                                }
                            }
                        }
                        // Kiểm tra nếu phần tử có chứa "quantity:"
                        else if (detail.contains("quantity:")) {
                            String[] quantityParts = detail.split(":");
                            if (quantityParts.length > 1) {
                                String quantity = quantityParts[1].trim();
                                if (quantity != null && !quantity.isEmpty()) {
                                    productDetail.setQuantity(Integer.parseInt(quantity));  // Gán số lượng sản phẩm
                                    orDetail.setQuantity(Integer.parseInt(quantity));
                                }
                            }
                        }

                    }
                    if (productDetail.getProductID() != null && productDetail.getPrice() != null && productDetail.getQuantity() != null) {
                        dto.getProductDetails().add(productDetail);
                        orderProductDetailService.save(orDetail);
                    }
                }

            }
            return new ModelAndView("redirect:/successVnpay/" + maDonHang);
        } else {
            // Trường hợp thanh toán thất bại
            dto.setCode("400");
            dto.setMessage("Fail");
            return new ModelAndView("redirect:/failVnpay");
        }
    }

    private String extractUsernameFromOrderInfo(String orderInfo) {
        if (orderInfo != null && orderInfo.contains("Username:")) {
            String[] parts = orderInfo.split(";");
            for (String part : parts) {
                if (part.contains("Username:")) {
                    return part.split(":")[1].trim();
                }
            }
        }
        return null;
    }

    private String extractAddressFromOrderInfo(String orderInfo) {
        if (orderInfo != null && orderInfo.contains("Address:")) {
            String[] parts = orderInfo.split(";");
            for (String part : parts) {
                if (part.contains("Address:")) {
                    String encodedAddress = part.split(":")[1].trim();
                    try {
                        // Giải mã địa chỉ
                        return URLDecoder.decode(encodedAddress, StandardCharsets.UTF_8.name());
                    } catch (Exception e) {
                        e.printStackTrace();  // In lỗi nếu có
                    }
                }
            }
        }
        return null;
    }


}
