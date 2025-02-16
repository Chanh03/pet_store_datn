USE PetShop;
GO

INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated]) VALUES (N'hien', N'$2a$10$SzQ2QZxe7EuslUYJefVTPevFfmJ1rNg/wWAc5xeFCigpn6KXxQlG6', N'Hoàng Hiền', N'hien', N'353453535', N'hien', 1, N'4545645645', CAST(N'2024-10-05T21:43:01.000' AS DateTime))
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated]) VALUES (N'phuc', N'$2a$10$SzQ2QZxe7EuslUYJefVTPevFfmJ1rNg/wWAc5xeFCigpn6KXxQlG6', N'Phúc', N'phuc', N'4564', N'phuc', 1, N'4554646', CAST(N'2024-10-05T21:28:28.000' AS DateTime))
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated]) VALUES (N'vietanh', N'$2a$10$SzQ2QZxe7EuslUYJefVTPevFfmJ1rNg/wWAc5xeFCigpn6KXxQlG6', N'Việt Anh', N'vietanh', N'4687458967', N'vietanh', 1, N'4674586', CAST(N'2024-10-05T21:28:02.000' AS DateTime))
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated]) VALUES (N'tam', N'$2a$10$eNWEJ08kxzMi5r1OD9nl1O0gMnnvUJHVsHqCQshB7Zl2UhV4IsmPG', N'Minh Tâm', N'tam', N'4687458967', N'tam', 1, N'4674586', CAST(N'2024-10-05T21:28:02.000' AS DateTime))
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated]) VALUES (N'hoang', N'$2a$10$JH4DRgqYWhiQAssV5uOfVu2IzlG91QIPfc9M9T1bClhTig133TtMe', N'Nhật Hoàng', N'hoang', N'4687458967', N'hoang', 1, N'4674586', CAST(N'2024-10-05T21:28:02.000' AS DateTime))
GO

INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'ADMIN', N'ROLE_ADMIN')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'STAFF', N'ROLE_STAFF')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'USER', N'ROLE_USER')
GO

INSERT [dbo].[Authorities] ([UserName], [RoleId]) VALUES (N'vietanh', N'ADMIN')
INSERT [dbo].[Authorities] ([UserName], [RoleId]) VALUES (N'phuc', N'STAFF')
INSERT [dbo].[Authorities] ([UserName], [RoleId]) VALUES (N'hien', N'USER')
INSERT [dbo].[Authorities] ([UserName], [RoleId]) VALUES (N'hoang', N'ADMIN')
INSERT [dbo].[Authorities] ([UserName], [RoleId]) VALUES (N'tam', N'USER')
Go

INSERT INTO PetCategory (PetCategoryName, Photo)
VALUES 
('Chó', 'cho.jpg'),
('Mèo', 'meo.jpg');

INSERT INTO ProductCategory (ProductCategoryName, Photo)
VALUES
(N'Thức ăn hạt', 'thucanhat.png'),
(N'Pate', 'pate.jpg'),
(N'Sữa tắm, dầu gội', 'suatam_daugoi.jpg'),
(N'Dụng cụ ăn uống', 'dungcuanuong.jpg'),
(N'Dụng cụ vệ sinh', 'dungcuvesinh.jpg'),
(N'Vòng cổ', 'vongco.jpg'),
(N'Túi xách|Balo', 'tuixach_balo.jpg'),
(N'Chuồng|Lồng', 'chuong_long.jpg'),
(N'Đồ chơi', 'do_choi.jpg'),
(N'Ổ đệm', 'o_dem.jpg'),
(N'Thời trang', 'thoi_trang.jpg');

INSERT INTO Service (ServiceName, Price, Description)
VALUES
(N'Khách sạn thú cưng', 500000, N'Dịch vụ khách sạn cho thú cưng với đầy đủ tiện nghi.'),
(N'Spa cho thú cưng', 300000, N'Dịch vụ Spa chăm sóc toàn diện cho thú cưng.'),
(N'Cắt tỉa lông thú cưng', 200000, N'Dịch vụ cắt tỉa lông chuyên nghiệp cho thú cưng.'),
(N'Huấn luyện thú cưng', 400000, N'Dịch vụ huấn luyện kỹ năng cho thú cưng.');

INSERT INTO Pets (PetID, Breed, Age, Gender, Price, PetDescription, Photo, Famous, Hair, Enable, Available, PetCategoryID)
VALUES
('P001', N'Chó Alaska', 2, 1, 5000000, N'Chó Alaska đáng yêu, thân thiện', 'cho_Alaska.jpg', N'Nổi tiếng', N'Dày', 1, 1, 1),
('P002', N'Chó Husky', 3, 0, 4500000, N'Chó Husky thông minh, năng động', 'cho_Husky.jpg', N'Nổi tiếng', N'Dày', 1, 1, 1),
('P003', N'Chó Poodle', 1, 1, 3000000, N'Chó Poodle nhỏ nhắn, dễ thương', 'cho_Poodle.jpg', N'Nổi tiếng', N'Xoăn', 1, 1, 1),
('P004', N'Mèo Anh lông ngắn', 2, 1, 3500000, N'Mèo Anh lông ngắn dễ chăm sóc', 'meo_Anh.jpg', N'Nổi tiếng', N'Ngắn', 1, 1, 2),
('P005', N'Mèo Ba Tư', 3, 0, 4000000, N'Mèo Ba Tư lông dài, sang trọng', 'meo_ba_tu.jpg', N'Nổi tiếng', N'Dài', 1, 1, 2),
('P006', N'Mèo Sphynx', 4, 1, 8000000, N'Mèo Sphynx không lông, độc đáo', 'meo_Sphynx.jpg', N'Nổi tiếng', N'Không có', 1, 1, 2),
('P007', N'Chó Chihuahua', 2, 0, 2500000, N'Chó Chihuahua nhỏ gọn, dũng cảm', 'cho_Chihuahua.jpg', N'Nổi tiếng', N'Ngắn', 1, 1, 1),
('P008', N'Chó Shiba Inu', 3, 1, 5500000, N'Chó Shiba Inu nhanh nhẹn, trung thành', 'cho_Shiba.jpg', N'Nổi tiếng', N'Dày', 1, 1, 1),
('P009', N'Mèo Ragdoll', 2, 0, 6000000, N'Mèo Ragdoll lông mềm mại, tính cách dễ chịu', 'meo_Ragdoll.jpg', N'Nổi tiếng', N'Dài', 1, 1, 2),
('P010', N'Mèo Bengal', 3, 1, 7000000, N'Mèo Bengal lông vằn, năng động', 'meo_Bengal.jpg', N'Nổi tiếng', N'Ngắn', 1, 1, 2);

-- Sản phẩm cho Thức ăn hạt
INSERT INTO Products (ProductName, Price, Photo, Available, Quantity, ProductDescription, ProductCategoryID)
VALUES
(N'Thức ăn hạt cho chó Alaska', 120000, 'product_thuc_an_hat.jpg', 1, 100, N'Thức ăn hạt dành riêng cho chó Alaska.', 1),
(N'Thức ăn hạt cho chó Labrador', 110000, 'product_thuc_an_Labrador.jpg', 1, 100, N'Thức ăn hạt cho chó Labrador, dinh dưỡng cao.', 1),
(N'Thức ăn hạt cho chó Beagle', 100000, 'product_thuc_an_Beagle.jpg', 1, 100, N'Thức ăn hạt dành cho chó Beagle.', 1),
(N'Thức ăn hạt cho chó Poodle', 130000, 'product_thuc_an_Poodle.jpg', 1, 100, N'Thức ăn hạt cho chó Poodle chất lượng.', 1),
(N'Thức ăn hạt cho chó Corgi', 115000, 'product_thuc_an_Corgi.jpg', 1, 100, N'Thức ăn hạt dành cho chó Corgi.', 1);

-- Sản phẩm cho Pate|Snack|Súp thưởng
INSERT INTO Products (ProductName, Price, Photo, Available, Quantity, ProductDescription, ProductCategoryID)
VALUES
(N'Pate cho chó', 70000, 'product_pate_cho.jpg', 1, 150, N'Pate dinh dưỡng cho chó.', 2),
(N'Pate cho mèo', 65000, 'product_pate_meo.jpg', 1, 140, N'Pate thơm ngon cho mèo.', 2),
(N'Snack thưởng cho chó', 80000, 'product_Snack_cho.jpg', 1, 130, N'Snack ngon cho chó.', 2),
(N'Snack thưởng cho mèo', 75000, 'product_Snack_meo.jpg', 1, 120, N'Snack thơm ngon cho mèo.', 2),
(N'Súp thưởng cho chó', 90000, 'product_sup_cho.jpg', 1, 110, N'Súp dinh dưỡng cho chó.', 2);

-- Sản phẩm cho Sữa tắm, dầu gội
INSERT INTO Products (ProductName, Price, Photo, Available, Quantity, ProductDescription, ProductCategoryID)
VALUES
(N'Sữa tắm cho chó', 50000, 'product_sua_tam_cho.jpg', 1, 90, N'Sữa tắm an toàn cho chó.', 3),
(N'Sữa tắm cho mèo', 55000, 'product_sua_tam_meo.jpg', 1, 85, N'Sữa tắm dành riêng cho mèo.', 3),
(N'Dầu gội cho chó', 60000, 'product_dau_goi_cho.jpg', 1, 80, N'Dầu gội giúp lông chó bóng mượt.', 3),
(N'Dầu gội cho mèo', 65000, 'product_dau_goi_meo.jpg', 1, 75, N'Dầu gội giữ cho lông mèo mềm mại.', 3),
(N'Sữa tắm khô cho chó', 70000, 'product_sua_tam_kho.jpg', 1, 70, N'Sữa tắm khô tiện lợi cho chó.', 3);

-- Sản phẩm cho Dụng cụ ăn uống
INSERT INTO Products (ProductName, Price, Photo, Available, Quantity, ProductDescription, ProductCategoryID)
VALUES
(N'Bát ăn cho chó', 30000, 'product_bat_an.jpg', 1, 200, N'Bát ăn chất liệu an toàn cho chó.', 4),
(N'Bát ăn cho mèo', 25000, 'product_bat_an.jpg', 1, 220, N'Bát ăn dành riêng cho mèo.', 4),
(N'Bình uống nước cho chó', 40000, 'product_binh_nuoc.jpg', 1, 180, N'Bình uống nước cho chó tiện lợi.', 4),
(N'Bình uống nước cho mèo', 35000, 'product_binh_nuoc.jpg', 1, 190, N'Bình uống nước dành cho mèo.', 4);

-- Sản phẩm cho Vệ sinh
INSERT INTO Products (ProductName, Price, Photo, Available, Quantity, ProductDescription, ProductCategoryID)
VALUES
(N'Tã cho chó', 60000, 'product_ta.jpg', 1, 160, N'Tã cho chó tiện lợi khi đi ra ngoài.', 5),
(N'Tã cho mèo', 55000, 'product_ta.jpg', 1, 150, N'Tã cho mèo dễ dàng sử dụng.', 5),
(N'Cát vệ sinh cho mèo', 70000, 'product_cat.jpg', 1, 140, N'Cát vệ sinh chất lượng cho mèo.', 5),
(N'Cát vệ sinh cho cho', 30000, 'product_cat.jpg', 1, 130, N'Khăn lau cho chó tiện dụng.', 5),
(N'Thảm vệ sinh ', 40000, 'product_tham_vs.jpg', 1, 120, N'Thảm vệ sinh cho chó dễ dàng vệ sinh.', 5);

-- Sản phẩm cho Vòng cổ
INSERT INTO Products (ProductName, Price, Photo, Available, Quantity, ProductDescription, ProductCategoryID)
VALUES
(N'Vòng cổ chó', 80000, 'product_vong_co.jpg', 1, 110, N'Vòng cổ dành cho chó lớn.', 6),
(N'Vòng cổ mèo', 75000, 'product_vong_co.jpg', 1, 100, N'Vòng cổ cho mèo, có thể điều chỉnh.', 6),
(N'Dây xích chó', 90000, 'product_xich.jpg', 1, 90, N'Dây xích cho chó lớn, chắc chắn.', 6),
(N'Dây xích mèo', 85000, 'product_xich.jpg', 1, 80, N'Dây xích dành cho mèo, dễ sử dụng.', 6);


-- Sản phẩm cho Túi xách|Balo
INSERT INTO Products (ProductName, Price, Photo, Available, Quantity, ProductDescription, ProductCategoryID)
VALUES
(N'Túi xách cho chó', 150000, 'product_tui_xach_chomeo.jpg', 1, 60, N'Túi xách cho chó nhỏ gọn.', 7),
(N'Túi xách cho mèo', 140000, 'product_tui_xach_chomeo.jpg', 1, 55, N'Túi xách cho mèo tiện lợi.', 7),
(N'Balo cho chó', 160000, 'product_balo.jpg', 1, 50, N'Balo cho chó, thích hợp đi dạo.', 7),
(N'Balo cho mèo', 155000, 'product_balo.jpg', 1, 45, N'Balo cho mèo, dễ dàng mang theo.', 7);

-- Sản phẩm cho Chuồng|Lồng
INSERT INTO Products (ProductName, Price, Photo, Available, Quantity, ProductDescription, ProductCategoryID)
VALUES
(N'Chuồng cho chó', 200000, 'product_chuong_cho.jpg', 1, 30, N'Chuồng cho chó lớn, tiện dụng.', 8),
(N'Lồng cho mèo', 180000, 'product_long_meo.jpg', 1, 25, N'Lồng cho mèo, an toàn và chắc chắn.', 8),
(N'Lồng cho thú cưng nhỏ', 240000, 'product_long_nho.jpg', 1, 10, N'Lồng cho thú cưng nhỏ, an toàn.', 8);

-- Sản phẩm cho Đồ chơi
INSERT INTO Products (ProductName, Price, Photo, Available, Quantity, ProductDescription, ProductCategoryID)
VALUES
(N'Đồ chơi cho chó', 50000, 'product_do_choi_cho_meo.jpg', 1, 100, N'Đồ chơi bền cho chó.', 9),
(N'Đồ chơi cho mèo', 45000, 'product_do_choi_cho_meo.jpg', 1, 90, N'Đồ chơi cho mèo, vui nhộn.', 9),
(N'Bóng cho chó', 60000, 'product_bong_cho_meo.jpg', 1, 80, N'Bóng cho chó, kích thích vận động.', 9),
(N'Bóng cho mèo', 55000, 'product_bong_cho_meo.jpg', 1, 70, N'Búp bê cho mèo, dễ thương.', 9);

-- Sản phẩm cho Ổ đệm
INSERT INTO Products (ProductName, Price, Photo, Available, Quantity, ProductDescription, ProductCategoryID)
VALUES
(N'Ổ đệm cho chó', 250000, 'prooduct_o_dem_cho.jpg', 1, 50, N'Ổ đệm cho chó, thoải mái.', 10),
(N'Ổ đệm cho mèo', 230000, 'product_o_dem_meo.jpg', 1, 40, N'Ổ đệm cho mèo, mềm mại.', 10),
(N'Giường cho chó', 350000, 'product_giuong_cho.jpg', 1, 20,N'Giường cho chó, êm ái.', 10),
(N'Giường cho mèo', 320000, 'product_giuong_meo.jpg', 1, 10, N'Giường cho mèo, êm ái.', 10);

-- Sản phẩm cho Thời trang
INSERT INTO Products (ProductName, Price, Photo, Available, Quantity, ProductDescription, ProductCategoryID)
VALUES
(N'Áo khoác cho chó', 150000, 'product_ao_khoac_cho.jpg', 1, 50, N'Áo khoác ấm cho chó.', 11),
(N'Váy cho mèo', 120000, 'product_vay_meo.jpg', 1, 45, N'Váy dễ thương cho mèo.', 11),
(N'Nón cho chó', 80000, 'product_non_cho.jpg', 1, 40, N'Nón thời trang cho chó.', 11),
(N'Nón cho mèo', 80000, 'product_non_cho.jpg', 1, 40, N'Nón thời trang cho chó.', 11),
(N'Áo thun cho thú cưng', 90000, 'product_ao_chomeo.jpg', 1, 30, N'Áo thun dễ thương cho thú cưng.', 11);

INSERT INTO OrderStatus (StatusName)
VALUES
(N'Đang chuẩn bị hàng'),
(N'Đang giao'),
(N'Đã giao');

INSERT INTO PaymentStatus (StatusPayment)
VALUES
(N'Chưa thanh toán'),
(N'Đã thanh toán');

INSERT INTO Orders (OrderDate, ShippingAddress, TotalAmount, UserName, Enable, OrderStatusID, PaymentStatusID)
VALUES
(GETDATE(), N'12 Cống Lỡ', 650000, 'hien', 1, 1, 1),			-- Đơn hàng 1
(GETDATE(), N'76 Lê Quý Đôn', 410000, 'phuc', 1, 2, 2),		-- Đơn hàng 2
(GETDATE(), N'22 Nguyễn Văn Cừ', 410000, 'vietanh', 1, 1, 1);		-- Đơn hàng 3

INSERT INTO OrderProductDetails (OrderID, ProductID, Quantity, Price)
VALUES
(1, 1, 2, 240000),				-- Đơn hàng 1, 2 Thức ăn hạt cho chó Alaska
(1, 2, 1, 110000),				-- Đơn hàng 1, 1 Thức ăn hạt cho chó Labrador
(1, 3, 3, 300000),				-- Đơn hàng 1, 3 Thức ăn hạt cho chó Beagle
(2, 5, 1, 130000),		-- Đơn hàng 2, 1 Thức ăn hạt cho chó Corgi
(2, 6, 2, 200000),		-- Đơn hàng 2, 2 Pate cho chó
(2, 7, 1, 80000),		-- Đơn hàng 2, 1 Snack thưởng cho chó
(3, 9, 1, 50000),				-- Đơn hàng 3, 1 Đồ chơi gặm cho chó
(3, 10, 2, 300000),				-- Đơn hàng 3, 2 Ổ đệm cho chó nhỏ
(3, 4, 1, 60000);				-- Đơn hàng 3, 1 Sữa tắm cho chó

INSERT INTO Voucher (Code, Discount, ExpiryDate, Enable)
VALUES
('VOUCHER10', 10, '2024-12-31', 1),
('VOUCHER20', 20, '2024-11-30', 0),
('VOUCHER15', 15, '2024-10-31', 0);

INSERT INTO SliderBar (SliderImage)
VALUES
('slider1.jpg'),
('slider2.jpg'),
('slider3.jpg'),
('slider4.jpg');

INSERT INTO Reviews (Comment, UserName, ProductID)
VALUES
(N'Sản phẩm rất tốt, thú cưng nhà mình rất thích!', 'hien', 1),
(N'Chất lượng khá ổn, giá hợp lý', 'hoang', 2),
(N'Dịch vụ rất nhanh chóng và tận tình', 'phuc', 3),
(N'Mèo nhà mình thích món này lắm', 'vietanh', 4),
(N'Đánh giá cao sản phẩm này, đáng tiền!', 'tam', 5);

INSERT INTO Rating (Number, ProductID) 
VALUES 
(5, 1),		-- Đánh giá 5 cho sản phẩm có ProductID là 1
(4, 1),		-- Đánh giá 4 cho sản phẩm có ProductID là 1
(3, 2),		-- Đánh giá 3 cho sản phẩm có ProductID là 2
(5, 2),		-- Đánh giá 5 cho sản phẩm có ProductID là 2
(2, 3),		-- Đánh giá 2 cho sản phẩm có ProductID là 3
(1, 3);		-- Đánh giá 1 cho sản phẩm có ProductID là 3