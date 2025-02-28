USE [master]
GO
/****** Object:  Database [PetShop]    Script Date: 27/11/2024 7:12:54 CH ******/
CREATE DATABASE [PetShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PetShop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\PetShop.mdf' , SIZE = 73728KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PetShop_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\PetShop_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [PetShop] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PetShop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PetShop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PetShop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PetShop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PetShop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PetShop] SET ARITHABORT OFF 
GO
ALTER DATABASE [PetShop] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PetShop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PetShop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PetShop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PetShop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PetShop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PetShop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PetShop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PetShop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PetShop] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PetShop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PetShop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PetShop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PetShop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PetShop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PetShop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PetShop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PetShop] SET RECOVERY FULL 
GO
ALTER DATABASE [PetShop] SET  MULTI_USER 
GO
ALTER DATABASE [PetShop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PetShop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PetShop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PetShop] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PetShop] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [PetShop] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [PetShop] SET QUERY_STORE = ON
GO
ALTER DATABASE [PetShop] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [PetShop]
GO
/****** Object:  Table [dbo].[Authorities]    Script Date: 27/11/2024 7:12:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Authorities](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[UserName] [varchar](50) NULL,
	[RoleId] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderProductDetails]    Script Date: 27/11/2024 7:12:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderProductDetails](
	[OrderProductDetailID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Price] [int] NOT NULL,
	[ReviewStatus] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderProductDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 27/11/2024 7:12:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[OrderDate] [date] NOT NULL,
	[ShippingAddress] [nvarchar](255) NULL,
	[TotalAmount] [int] NOT NULL,
	[UserName] [varchar](50) NOT NULL,
	[Enable] [bit] NOT NULL,
	[OrderStatusID] [int] NOT NULL,
	[PaymentStatusID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderStatus]    Script Date: 27/11/2024 7:12:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderStatus](
	[OrderStatusID] [int] IDENTITY(1,1) NOT NULL,
	[StatusName] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[OrderStatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PaymentStatus]    Script Date: 27/11/2024 7:12:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PaymentStatus](
	[PaymentStatusID] [int] IDENTITY(1,1) NOT NULL,
	[StatusPayment] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[PaymentStatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PetCategory]    Script Date: 27/11/2024 7:12:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PetCategory](
	[PetCategoryID] [int] IDENTITY(1,1) NOT NULL,
	[PetCategoryName] [nvarchar](255) NOT NULL,
	[Photo] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[PetCategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pets]    Script Date: 27/11/2024 7:12:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pets](
	[PetID] [varchar](50) NOT NULL,
	[Breed] [nvarchar](255) NOT NULL,
	[Age] [int] NOT NULL,
	[Gender] [bit] NOT NULL,
	[Price] [int] NOT NULL,
	[PetDescription] [nvarchar](255) NOT NULL,
	[Photo] [varchar](255) NOT NULL,
	[Famous] [nvarchar](255) NOT NULL,
	[Hair] [nvarchar](255) NOT NULL,
	[Enable] [bit] NOT NULL,
	[Available] [bit] NOT NULL,
	[PetCategoryID] [int] NOT NULL,
	[CreateDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[PetID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductCategory]    Script Date: 27/11/2024 7:12:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductCategory](
	[ProductCategoryID] [int] IDENTITY(1,1) NOT NULL,
	[ProductCategoryName] [nvarchar](255) NOT NULL,
	[Photo] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ProductCategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 27/11/2024 7:12:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[ProductID] [int] IDENTITY(1,1) NOT NULL,
	[ProductName] [nvarchar](255) NOT NULL,
	[Price] [int] NOT NULL,
	[Photo] [varchar](255) NOT NULL,
	[Available] [bit] NOT NULL,
	[Quantity] [int] NOT NULL,
	[ProductDescription] [nvarchar](255) NOT NULL,
	[ProductCategoryID] [int] NOT NULL,
	[CreateDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Reviews]    Script Date: 27/11/2024 7:12:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Reviews](
	[ReviewID] [int] IDENTITY(1,1) NOT NULL,
	[Comment] [nvarchar](255) NOT NULL,
	[UserName] [varchar](50) NOT NULL,
	[ProductID] [int] NOT NULL,
	[Rating] [int] NULL,
	[ReviewDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[ReviewID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 27/11/2024 7:12:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Roles](
	[Id] [varchar](50) NOT NULL,
	[Name] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SliderBar]    Script Date: 27/11/2024 7:12:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SliderBar](
	[SliderId] [int] IDENTITY(1,1) NOT NULL,
	[SliderImage] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[SliderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subscription]    Script Date: 27/11/2024 7:12:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subscription](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](255) NOT NULL,
	[Email] [nvarchar](255) NOT NULL,
	[phoneNumber] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 27/11/2024 7:12:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserName] [varchar](50) NOT NULL,
	[UserPassword] [nvarchar](100) NOT NULL,
	[FullName] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[PhoneNumber] [nvarchar](20) NOT NULL,
	[UserAddress] [nvarchar](255) NOT NULL,
	[Enable] [bit] NOT NULL,
	[ActiveToken] [varchar](200) NOT NULL,
	[DateCreated] [datetime] NOT NULL,
	[TemporaryGUID] [varchar](225) NULL,
	[TempGuidExpir] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[UserName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Voucher]    Script Date: 27/11/2024 7:12:54 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Voucher](
	[VoucherID] [varchar](50) NOT NULL,
	[Discount] [int] NOT NULL,
	[ExpiryDate] [date] NOT NULL,
	[Enable] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[VoucherID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Authorities] ON 

INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (33, N'nhathoang', N'ADMIN')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (34, N'vietanh', N'ADMIN')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (35, N'trongphuc', N'ADMIN')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (36, N'minhtam', N'STAFF')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (37, N'test01', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (38, N'test02', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (39, N'hoanghien', N'STAFF')
SET IDENTITY_INSERT [dbo].[Authorities] OFF
GO
SET IDENTITY_INSERT [dbo].[OrderStatus] ON 

INSERT [dbo].[OrderStatus] ([OrderStatusID], [StatusName]) VALUES (1, N'Đang xử lý')
INSERT [dbo].[OrderStatus] ([OrderStatusID], [StatusName]) VALUES (2, N'Đã xác nhận')
INSERT [dbo].[OrderStatus] ([OrderStatusID], [StatusName]) VALUES (3, N'Đang vận chuyển')
INSERT [dbo].[OrderStatus] ([OrderStatusID], [StatusName]) VALUES (4, N'Đã giao')
INSERT [dbo].[OrderStatus] ([OrderStatusID], [StatusName]) VALUES (5, N'Hủy đơn')
SET IDENTITY_INSERT [dbo].[OrderStatus] OFF
GO
SET IDENTITY_INSERT [dbo].[PaymentStatus] ON 

INSERT [dbo].[PaymentStatus] ([PaymentStatusID], [StatusPayment]) VALUES (1, N'Chưa thanh toán')
INSERT [dbo].[PaymentStatus] ([PaymentStatusID], [StatusPayment]) VALUES (2, N'Thanh toán thành công')
SET IDENTITY_INSERT [dbo].[PaymentStatus] OFF
GO
SET IDENTITY_INSERT [dbo].[PetCategory] ON 

INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (22, N'Loài chó', N'dogs.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (23, N'Loài Mèo', N'cats.webp')
SET IDENTITY_INSERT [dbo].[PetCategory] OFF
GO
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'Cats-Golden', N'Golden', 2, 0, 5500000, N'Mèo Golden, hay còn gọi là mèo Anh lông ngắn Golden, nổi bật với bộ lông vàng óng, đôi mắt to tròn và vẻ ngoài dễ thương. Chúng hiền lành, thân thiện, thích gần gũi con người, và rất dễ chăm sóc, trở thành lựa chọn lý tưởng cho các gia đình yêu mèo.', N'Golden.webp', N'Vàng', N'Ngắn', 0, 0, 23, CAST(N'2024-11-27T09:51:57.053' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'Cats-Munchkin', N'Munchkin', 2, 0, 4500000, N'Munchkin là giống mèo nổi tiếng với đôi chân ngắn, dáng vẻ dễ thương và lém lỉnh. Chúng thông minh, nghịch ngợm, thân thiện và rất thích chơi đùa, là bạn đồng hành tuyệt vời cho gia đình.', N'Munchkin.webp', N'Trắng', N'Ngắn', 0, 0, 23, CAST(N'2024-11-27T09:55:36.517' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'Cats-Ragdoll', N'Ragdoll', 3, 0, 5500000, N'Ragdoll là giống mèo lớn, lông dài, mềm mại và có tính cách dịu dàng, thân thiện. Chúng rất thích được ôm ấp, dễ gần và thường đi theo chủ, là bạn đồng hành lý tưởng cho gia đình.', N'Ragdoll.webp', N'Trắng', N'Dài', 0, 0, 23, CAST(N'2024-11-27T09:56:15.537' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'Cats-Shorthair', N'British Shorthair', 3, 0, 7500000, N'Mèo Anh lông ngắn có bộ lông dày, mềm. Chúng có tính cách bình tĩnh, thân thiện và rất độc lập, thích được cưng nựng nhưng cũng có thể tự chơi một mình, là bạn đồng hành lý tưởng cho gia đình.', N'Shorthair.webp', N'Xám', N'Ngắn', 0, 0, 23, CAST(N'2024-11-27T09:54:26.310' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'Dogs-Corgi', N'Corgi', 3, 1, 3000000, N'Corgi là giống chó nhỏ, chân ngắn, tai to dựng, thông minh và thân thiện. Chúng năng động, dễ mến, phù hợp làm thú cưng trong mọi gia đình.', N'Corgi.webp', N'Vàng', N'Dài', 0, 0, 22, CAST(N'2024-11-27T09:46:05.817' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'Dogs-Dachshund', N'Dachshund', 2, 0, 3000000, N'Dachshund là giống chó nhỏ với thân dài, chân ngắn, thông minh và trung thành. Chúng hoạt bát, đáng yêu và là bạn đồng hành lý tưởng cho mọi gia đình.', N'Dachshund.webp', N'Nâu', N'Ngắn', 1, 0, 22, CAST(N'2024-11-27T09:47:37.137' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'Dogs-French Bulldog', N'French Bulldog', 4, 1, 4000000, N'French Bulldog là giống chó nhỏ, cơ bắp, với đôi tai dựng đặc trưng. Chúng thân thiện, tình cảm, dễ chăm sóc, và là bạn đồng hành lý tưởng cho cuộc sống đô thị.', N'French Bulldog.webp', N'Xám', N'Ngắn', 0, 0, 22, CAST(N'2024-11-27T09:48:47.207' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'Dogs-Poodle', N'Poodle', 3, 0, 4500000, N'Poodle là giống chó thông minh, lông xoăn đặc trưng, và dễ huấn luyện. Chúng thân thiện, linh hoạt, phù hợp với cả gia đình và người yêu chó cảnh.', N'Poodle.webp', N'Nâu', N'Xoăn', 0, 0, 22, CAST(N'2024-11-27T09:50:03.983' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[ProductCategory] ON 

INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (21, N'Sữa tắm | Dầu gội', N'suaTamDauGoi.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (23, N'Pate | Snack | Súp thưởng', N'pateSnackSupThuong.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (24, N'Thức ăn hạt', N'thucAnHat.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (25, N'Dụng cụ ăn uống', N'dungCuAnUong.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (26, N'Dụng cụ vệ sinh', N'dungCuVeSinh.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (27, N'Vòng vổ', N'vongCo.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (28, N'Túi sách | Balo', N'tuiSachBalo.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (29, N'Chuồng | Lồng', N'chuongLong.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (30, N'Đồ chơi', N'doChoi.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (31, N'Ổ đệm', N'oDem.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (32, N'Thời trang', N'thoiTrang.webp')
SET IDENTITY_INSERT [dbo].[ProductCategory] OFF
GO
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (50, N'Vòng Cổ Cho Chó Lớn Bằng Da PAW', 30000, N'vongCo01.webp', 1, 10, N'Vòng cổ da bền chắc, thiết kế sang trọng, phù hợp cho chó lớn, mang lại sự thoải mái và phong cách.', 27, CAST(N'2024-11-27T13:38:31.073' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (51, N'Vòng Cổ Cho Chó Mèo Hình Dâu Tây', 32000, N'vongCo04.webp', 1, 10, N'Vòng cổ đáng yêu, họa tiết dâu tây ngộ nghĩnh, nhẹ nhàng và dễ điều chỉnh, phù hợp cho cả chó và mèo.', 27, CAST(N'2024-11-27T13:38:58.280' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (52, N'Vòng Cổ Cho Chó Mèo Có Thể Sạc Lại, Kết Nối Bluetooth, Chạy Chữ Đèn LED', 55000, N'vongCo02.webp', 1, 10, N'Vòng cổ hiện đại, tích hợp đèn LED chạy chữ, kết nối Bluetooth, có thể sạc lại, tiện lợi và nổi bật.', 27, CAST(N'2024-11-27T13:39:21.570' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (53, N'Vòng Cổ Chó Mèo Hình Ngôi Sao', 55000, N'vongCo05.webp', 1, 10, N'Vòng cổ với họa tiết ngôi sao lấp lánh, nhẹ nhàng, dễ đeo, mang lại vẻ đáng yêu cho chó mèo.', 27, CAST(N'2024-11-27T13:39:54.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (54, N'Vòng Cổ Xanh Đỏ Đen Dành Cho Chó Mèo', 35000, N'vongCo03.webp', 1, 10, N'Vòng cổ đơn giản, ba màu cơ bản xanh, đỏ, đen, chắc chắn và thoải mái, phù hợp cho mọi loại chó mèo.', 27, CAST(N'2024-11-27T13:40:18.963' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (55, N'Balo đựng chó mèo dáng hộp mặt lưới LOFFE', 250000, N'balo01.webp', 1, 10, N'Balo thiết kế dáng hộp, mặt lưới thoáng khí, tiện lợi và an toàn khi di chuyển chó mèo.', 28, CAST(N'2024-11-27T13:46:17.907' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (56, N'Túi balo cho chó vận chuyển K&H Pet', 280000, N'balo03.webp', 1, 10, N'Túi balo chắc chắn, thoải mái, phù hợp để vận chuyển chó với thiết kế hiện đại từ K&H Pet.', 28, CAST(N'2024-11-27T13:46:45.177' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (57, N'Túi vận chuyển balo du lịch cho chó mèo', 320000, N'balo04.webp', 1, 10, N'Balo đa năng, tiện dụng, giúp bạn dễ dàng mang chó mèo đi du lịch hoặc dạo phố.', 28, CAST(N'2024-11-27T13:47:10.840' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (58, N'Balo PengPai nhiều màu cho chó mèo', 320000, N'balo02.webp', 1, 10, N'Balo PengPai nổi bật với nhiều màu sắc, thiết kế chắc chắn, thoáng khí, lý tưởng cho thú cưng.', 28, CAST(N'2024-11-27T13:47:27.533' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (59, N'Túi Xách Cho Chó Mèo Trong Suốt', 310000, N'balo05.webp', 1, 10, N'Túi xách trong suốt, nhẹ nhàng, giúp bạn quan sát thú cưng và mang lại cảm giác thoải mái khi di chuyển.', 28, CAST(N'2024-11-27T13:47:50.180' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (60, N'Thức Ăn Hạt Kitcat Cho Mèo', 250000, N'hat07.webp', 1, 10, N'Thức ăn hạt Kitcat, dinh dưỡng cân đối, hỗ trợ sức khỏe và lông mượt cho mèo.', 24, CAST(N'2024-11-27T13:52:45.393' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (61, N'Thức Ăn Hạt Whiskat Cho Mèo', 250000, N'hat09.webp', 1, 10, N'Hạt Whiskat giàu dinh dưỡng, dễ tiêu hóa, phù hợp cho mèo ở mọi độ tuổi.', 24, CAST(N'2024-11-27T13:53:13.950' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (62, N'Catpy - Hạt Thức Ăn Cho Mèo Con', 250000, N'hat01.webp', 1, 10, N'Hạt Catpy chuyên biệt cho mèo con, cung cấp dưỡng chất giúp phát triển khỏe mạnh.', 24, CAST(N'2024-11-27T13:53:34.013' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (63, N'Thức Ăn Hạt Cho Mèo Trưởng Thành Me-O', 270000, N'hat04.webp', 1, 10, N'Hạt Me-O dinh dưỡng, thơm ngon, đáp ứng nhu cầu năng lượng của mèo trưởng thành.', 24, CAST(N'2024-11-27T13:54:23.637' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (64, N'Thức Ăn Hạt Cho Mèo CATSRANG', 279000, N'hat03.webp', 1, 10, N'Catsrang chất lượng cao, hạt thơm ngon, bổ sung dưỡng chất toàn diện cho mèo.', 24, CAST(N'2024-11-27T13:55:18.387' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (65, N'Thức Ăn Hạt Ganador Dành Cho Chó', 289000, N'hat05.webp', 1, 10, N'Hạt Ganador, giàu dinh dưỡng, hỗ trợ tăng cường sức khỏe và phát triển cho chó.', 24, CAST(N'2024-11-27T13:56:12.923' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (66, N'THỨC ĂN KHÔ CLASSIC CHO CHÓ', 229000, N'hat10.webp', 1, 10, N'Thức ăn khô Classic, dễ sử dụng, cung cấp đầy đủ dưỡng chất thiết yếu cho chó.', 24, CAST(N'2024-11-27T13:56:39.247' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (67, N'Thức Ăn Hạt Smartheart Dành Cho Chó', 229000, N'hat08.webp', 1, 10, N'Hạt Smartheart, thơm ngon, bổ dưỡng, hỗ trợ sức khỏe và năng lượng cho chó.', 24, CAST(N'2024-11-27T13:56:57.393' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (68, N'Thức Ăn Hạt Hoàn Chỉnh Cho Chó Mọi Lứa Tuổi', 339000, N'hat06.webp', 1, 10, N'Hạt dinh dưỡng hoàn chỉnh, phù hợp cho chó ở mọi lứa tuổi, dễ tiêu hóa và ngon miệng.', 24, CAST(N'2024-11-27T13:57:31.980' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (69, N'Thức Ăn Hạt Cho Chó Smartheart Adult', 339000, N'hat02.webp', 1, 10, N'Smartheart Adult, công thức đặc biệt dành cho chó trưởng thành, bổ dưỡng và cân bằng.', 24, CAST(N'2024-11-27T13:57:50.723' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (70, N'Bộ ăn uống tự động', 150000, N'dungCuAnUong01.webp', 1, 10, N'Hệ thống khay ăn uống tự động, cung cấp thức ăn và nước đều đặn, tiện lợi cho thú cưng.', 25, CAST(N'2024-11-27T14:06:04.453' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (71, N'Khay ăn uống cho mèo và chó con', 150000, N'dungCuAnUong02.webp', 1, 10, N'Khay thiết kế tiện dụng, nhỏ gọn, phù hợp với mèo và chó con, dễ dàng vệ sinh.', 25, CAST(N'2024-11-27T14:06:27.323' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (72, N'Khay ăn uống cho mèo con', 89000, N'dungCuAnUong03.webp', 1, 10, N'Khay dành riêng cho mèo con, chất liệu an toàn, chống trượt, và dễ dàng sử dụng.', 25, CAST(N'2024-11-27T14:06:45.040' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (73, N'Bộ khay ăn uống cho mèo con và chó con', 109000, N'dungCuAnUong04.webp', 1, 10, N'Bộ khay đa năng, giúp sắp xếp gọn gàng khu vực ăn uống cho thú cưng nhỏ.', 25, CAST(N'2024-11-27T14:07:08.473' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (74, N'Khay ăn hình cute cho mèo', 99000, N'dungCuAnUong05.webp', 1, 10, N'Khay ăn thiết kế đáng yêu, màu sắc bắt mắt, mang lại niềm vui trong bữa ăn của mèo.', 25, CAST(N'2024-11-27T14:07:22.877' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (75, N'Xẻng xúc cát vệ sinh cho chó mèo', 20000, N'veSinh05.webp', 1, 10, N'Dụng cụ xúc cát tiện lợi, chắc chắn, giúp vệ sinh khay cát cho thú cưng sạch sẽ.', 26, CAST(N'2024-11-27T14:14:46.983' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (76, N'Lược chải lông cho chó mèo', 29000, N'veSinh03.webp', 1, 10, N'Lược chuyên dụng, dễ dàng loại bỏ lông rụng, giữ bộ lông thú cưng mượt mà và gọn gàng.', 26, CAST(N'2024-11-27T14:15:10.283' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (77, N'Cây lăn lông chó mèo', 49000, N'veSinh01.webp', 1, 10, N'Cây lăn hiệu quả, giúp loại bỏ lông thú cưng trên quần áo, sofa và sàn nhà nhanh chóng.', 26, CAST(N'2024-11-27T14:15:32.403' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (78, N'Miếng lót cho chó mèo', 79000, N'veSinh04.webp', 1, 10, N'Miếng lót thấm hút tốt, dùng để huấn luyện chó mèo đi vệ sinh đúng chỗ, tiện lợi và vệ sinh.', 26, CAST(N'2024-11-27T14:15:50.020' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (79, N'Khay vệ sinh cho chó Makar', 129000, N'veSinh02.webp', 1, 10, N'Khay thiết kế thông minh, hỗ trợ huấn luyện chó mèo đi vệ sinh đúng chỗ, dễ vệ sinh và sử dụng.', 26, CAST(N'2024-11-27T14:16:10.877' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (80, N'Sữa Tắm Cho Chó Mèo SOS', 59000, N'suaTamDauGoi01.webp', 1, 10, N'Sữa tắm dịu nhẹ, phù hợp cho cả chó và mèo, giúp làm sạch, khử mùi và giữ lông mượt mà.', 21, CAST(N'2024-11-27T14:27:29.230' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (81, N'Sữa Tắm Trixie Cho Thú Cưng', 39000, N'suaTamDauGoi02.webp', 1, 10, N'Công thức an toàn, giúp làm sạch sâu, dưỡng da và lông khỏe mạnh cho thú cưng.', 21, CAST(N'2024-11-27T14:27:42.520' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (82, N'Sữa Tắm Bio Care Khử Mùi Cho Chó Mèo', 69000, N'suaTamDauGoi03.webp', 1, 10, N'Sữa tắm Bio Care chuyên khử mùi, ngăn ngừa vi khuẩn và dưỡng ẩm da cho thú cưng.', 21, CAST(N'2024-11-27T14:27:54.653' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (83, N'Dầu Gội Hương Thảo Mộc Cho Chó Mèo', 54000, N'suaTamDauGoi04.webp', 1, 10, N'Dầu gội thiên nhiên, an toàn, giúp làm sạch và mang lại hương thơm dễ chịu cho thú cưng.', 21, CAST(N'2024-11-27T14:28:13.617' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (84, N'Sữa Tắm Trị Ve Rận Cho Chó Mèo Fay', 74000, N'suaTamDauGoi05.webp', 1, 10, N'Sữa tắm Fay chuyên trị ve rận, hỗ trợ làm sạch lông và bảo vệ da thú cưng hiệu quả.', 21, CAST(N'2024-11-27T14:28:30.663' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (85, N'Pate Whiskas Cho Mèo', 39000, N'Pate01.webp', 1, 20, N'Pate dinh dưỡng, hương vị thơm ngon, cung cấp dưỡng chất cần thiết cho mèo.', 23, CAST(N'2024-11-27T14:42:13.210' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (86, N'Pate Royal Canin Cho Mèo Con', 33000, N'Pate02.webp', 1, 20, N'Công thức đặc biệt, giàu protein, hỗ trợ sự phát triển toàn diện cho mèo con.', 23, CAST(N'2024-11-27T14:42:27.383' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (87, N'Pate Sheba Thượng Hạng', 49000, N'Pate03.webp', 1, 20, N'Pate cao cấp, vị ngon tự nhiên, phù hợp cho mèo ở mọi độ tuổi.', 23, CAST(N'2024-11-27T14:42:43.607' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (88, N'Pate Pedigree Cho Chó', 49000, N'Pate04.webp', 1, 20, N'Pate Pedigree bổ sung dinh dưỡng, giúp chó khỏe mạnh và phát triển tốt.', 23, CAST(N'2024-11-27T14:42:59.127' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (89, N'Pate Ganador Cho Chó Lớn', 79000, N'Pate05.webp', 1, 20, N'Công thức giàu dưỡng chất, giúp duy trì năng lượng và sức khỏe cho chó lớn.', 23, CAST(N'2024-11-27T14:43:11.263' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (90, N'Snack JerHigh Cho Chó', 39000, N'Snack01.webp', 1, 20, N'Snack mềm thơm, giàu protein, là món ăn vặt hấp dẫn cho chó.', 23, CAST(N'2024-11-27T14:43:24.387' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (91, N'Snack Cho Mèo Me-O Creamy Treats', 69000, N'Snack02.webp', 1, 20, N'Snack dạng kem ngon miệng, bổ sung vitamin và dưỡng chất cho mèo.', 23, CAST(N'2024-11-27T14:43:39.570' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (92, N'Súp thưởng Ciao Cho Mèo', 29000, N'SupThuong01.webp', 1, 20, N'Súp mềm mịn, thơm ngon, kích thích vị giác, phù hợp cho mèo biếng ăn.', 23, CAST(N'2024-11-27T14:43:55.363' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (93, N'Súp Wanpy Cho Chó', 19000, N'SupThuong02.webp', 1, 20, N'Súp dinh dưỡng cao, dễ tiêu hóa, là món thưởng lý tưởng cho chó.', 23, CAST(N'2024-11-27T14:44:07.973' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (94, N'Súp Thưởng Jinny Cho Mèo', 49000, N'SupThuong03..webp', 1, 20, N'Súp giàu vitamin và khoáng chất, giúp mèo khỏe mạnh và lông mượt.', 23, CAST(N'2024-11-27T14:44:24.683' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (95, N'Chuồng Chó Mèo Bằng Sắt', 129000, N'chuongLong01.webp', 1, 20, N'Chuồng chắc chắn, lưới sắt bền bỉ, không gian thông thoáng, phù hợp cho chó mèo nuôi trong nhà.', 29, CAST(N'2024-11-27T14:52:58.097' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (96, N'Lồng Du Lịch Cho Chó Mèo', 109000, N'chuongLong02.webp', 1, 20, N'Lồng nhẹ nhàng, dễ mang theo, thiết kế thoải mái và an toàn cho thú cưng khi di chuyển.', 29, CAST(N'2024-11-27T14:53:14.337' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (97, N'Chuồng Chó Mèo Bằng Nhựa', 119000, N'chuongLong03.webp', 1, 20, N'Chuồng nhựa dễ vệ sinh, có cửa mở dễ dàng, thích hợp cho việc giữ chó mèo trong nhà hoặc khi đi ra ngoài.', 29, CAST(N'2024-11-27T14:53:31.943' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (98, N'Lồng Lưới Xếp Gọn', 129000, N'chuongLong04.webp', 1, 20, N'Lồng lưới gọn nhẹ, dễ gấp gọn, thích hợp cho việc vận chuyển chó mèo đi chơi hoặc du lịch.', 29, CAST(N'2024-11-27T14:54:19.367' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (99, N'Chuồng Cỡ Lớn Cho Chó', 239000, N'chuongLong05.webp', 1, 20, N'Chuồng rộng rãi, chất liệu chắc chắn, mang lại không gian thoải mái và an toàn cho chó lớn.', 29, CAST(N'2024-11-27T14:54:34.390' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (100, N'Bóng Chơi Cho Chó', 29000, N'doChoi01.webp', 1, 20, N'Bóng cao su bền, dễ ném và đuổi, giúp chó vận động và giải trí.', 30, CAST(N'2024-11-27T15:13:55.657' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (101, N'Chuỗi Đồ Chơi Mèo Gắn Lông', 29000, N'doChoi02.webp', 1, 20, N'Đồ chơi treo lông mềm, kích thích mèo săn bắt và chơi đùa.', 30, CAST(N'2024-11-27T15:14:10.853' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (102, N'Dây Kéo Thú Cưng', 89000, N'doChoi03.webp', 1, 20, N'Dây kéo bền, thích hợp để chơi kéo co cùng chó, tăng cường sự gắn bó.', 30, CAST(N'2024-11-27T15:14:25.780' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (103, N'Móc Chơi Câu Cho Mèo', 49000, N'doChoi04.webp', 1, 20, N'Móc câu với lông và đồ chơi nhỏ, dễ dàng treo lên cửa hoặc khung cửa sổ để mèo vui chơi.', 30, CAST(N'2024-11-27T15:14:37.697' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (104, N'Đồ Chơi Kẹo Dẻo Cho Chó', 99000, N'doChoi06.webp', 1, 20, N'Kẹo dẻo chơi cho chó, giúp làm sạch răng miệng và giải trí.', 30, CAST(N'2024-11-27T15:15:10.750' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (105, N'Ổ Đệm Nhồi Bông Cho Chó Mèo', 99000, N'oDem01.webp', 1, 20, N'Ổ đệm êm ái, lót bông mềm, giúp thú cưng có giấc ngủ thoải mái và dễ chịu.', 31, CAST(N'2024-11-27T15:29:49.417' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (106, N'Ổ Đệm Vải Canvas Chắc Chắn', 99000, N'oDem02.webp', 1, 20, N'Ổ đệm vải bền, thiết kế chống thấm, dễ vệ sinh và phù hợp cho chó mèo trong nhà.', 31, CAST(N'2024-11-27T15:30:00.687' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (107, N'Ổ Đệm Nhỏ Gọn Cho Chó Mèo Con', 99000, N'oDem04.webp', 1, 20, N'Ổ đệm nhỏ, nhẹ nhàng, thiết kế dễ di chuyển, thích hợp cho chó mèo con.', 31, CAST(N'2024-11-27T15:30:10.913' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (109, N'Áo Len Dễ Thương Cho Chó Mèo', 89000, N'thoiTrang01.webp', 1, 20, N'Áo len ấm áp, thiết kế dễ thương, phù hợp cho mùa đông, giúp thú cưng giữ ấm và phong cách.', 32, CAST(N'2024-11-27T15:38:21.977' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (110, N'Váy Công Chúa Cho Mèo', 89000, N'thoiTrang02.webp', 1, 20, N'Váy thiết kế lộng lẫy với chi tiết ren và đính hạt, tạo vẻ xinh xắn và sang trọng cho mèo.', 32, CAST(N'2024-11-27T15:38:29.717' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (111, N'Áo Phông Thoáng Mát Cho Chó', 89000, N'thoiTrang03.webp', 1, 20, N'Áo phông cotton nhẹ nhàng, thấm hút mồ hôi, lý tưởng cho chó trong những ngày hè oi bức.', 32, CAST(N'2024-11-27T15:38:39.920' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (112, N'Giáp Thể Thao Cho Chó', 89000, N'thoiTrang04.webp', 1, 20, N'Giáp thể thao năng động, thiết kế thoáng khí và chắc chắn, giúp chó thêm phần phong cách khi chạy nhảy.', 32, CAST(N'2024-11-27T15:38:48.100' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (113, N'Áo Sơ Mi Kẻ Cho Mèo', 89000, N'thoiTrang05.webp', 1, 20, N'Áo sơ mi kẻ phong cách, dễ mặc, tạo nên vẻ ngoài lịch lãm và dễ thương cho mèo.', 32, CAST(N'2024-11-27T15:39:01.193' AS DateTime))
SET IDENTITY_INSERT [dbo].[Products] OFF
GO
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'ADMIN', N'ROLE_ADMIN')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'STAFF', N'ROLE_STAFF')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'USER', N'ROLE_USER')
GO
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'hoanghien', N'$2a$10$47CffrvVemaG63Mif2wTxO8GJw4rg0p7y5D/rpgrF3t2WTV8ZRTYO', N'Lê Hoàng Hiền', N'hienlhpsps31008@fpt.edu.vn', N'0303123123', N'Quận 2, Thành phố Hồ Chí Minh', 1, N'9fd39819-ff37-447e-9e60-efcd48cb6712', CAST(N'2024-11-16T19:40:20.467' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'minhtam', N'$2a$10$HzcYYPrajQmXBhGMKCizV.YrI1uDrCFhSMCpVS3N3cLkweiSkCZ4y', N'Đỗ Minh Tâm', N'tamdmps36365@fpt.edu.vn', N'0808123123', N'Quận 12, Thành phố Hồ Chí Minh', 1, N'9fd39819-ff37-447e-9e60-efcd48cb6712', CAST(N'2024-11-16T19:40:20.467' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'nhathoang', N'$2a$10$ka7kZ20/x3oy6wH0iSs.befCq6GIcv0XSvQC33kFeshj2eCCNE4Vm', N'Phạm Nhật Hoàng', N'hoangpnps30696@fpt.edu.vn', N'0365068759', N'Quận Gò Vấp, Thành phố Hồ Chí Minh', 1, N'9fd39819-ff37-447e-9e60-efcd48cb6712', CAST(N'2024-11-16T19:40:20.467' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'test01', N'$2a$10$Lr2OrSj4JJ.H8Q.cCNK6CeQix3Y1gwNv/Jdn0hg735ZfNtyHO/oDm', N'Test 01', N'test01@gmail.com', N'0909789789', N'test', 1, N'9fd39819-ff37-447e-9e60-efcd48cb6712', CAST(N'2024-11-16T19:40:20.467' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'test02', N'$2a$10$K7k19RWBs3rP9CTanCS6DeF6uyGA1LDJNV/i.2Vbr1H2iWIEuNs6e', N'Test 02', N'test02@gmail.com', N'0303456456', N'test', 1, N'9fd39819-ff37-447e-9e60-efcd48cb6712', CAST(N'2024-11-16T19:40:20.467' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'trongphuc', N'$2a$10$NxlEioOAE85dP.Dzet1VLOXXFVMMh2Q8n2LdSnjeu1IaEvxfmJHoS', N'Nguyễn Trọng Phúc', N'phucntps30804@fpt.edu.vn', N'0707123123', N'Quận 2, Thành phố Hồ Chí Minh', 1, N'9fd39819-ff37-447e-9e60-efcd48cb6712', CAST(N'2024-11-16T19:40:20.467' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh', N'$2a$10$blGsGS.IHqaH2G/jrTfj1uxFjcL9VvOAek0ARYIhani8OnFFu40Ry', N'Ngô Việt Anh', N'anhnvps30934@fpt.edu.vn', N'0303789789', N'Quận 8, Thành phố Hồ Chí Minh', 1, N'9fd39819-ff37-447e-9e60-efcd48cb6712', CAST(N'2024-11-16T19:40:20.467' AS DateTime), NULL, NULL)
GO
ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD FOREIGN KEY([RoleId])
REFERENCES [dbo].[Roles] ([Id])
GO
ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD FOREIGN KEY([UserName])
REFERENCES [dbo].[Users] ([UserName])
GO
ALTER TABLE [dbo].[OrderProductDetails]  WITH CHECK ADD FOREIGN KEY([OrderID])
REFERENCES [dbo].[Orders] ([OrderID])
GO
ALTER TABLE [dbo].[OrderProductDetails]  WITH CHECK ADD FOREIGN KEY([ProductID])
REFERENCES [dbo].[Products] ([ProductID])
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([OrderStatusID])
REFERENCES [dbo].[OrderStatus] ([OrderStatusID])
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([PaymentStatusID])
REFERENCES [dbo].[PaymentStatus] ([PaymentStatusID])
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([UserName])
REFERENCES [dbo].[Users] ([UserName])
GO
ALTER TABLE [dbo].[Pets]  WITH CHECK ADD FOREIGN KEY([PetCategoryID])
REFERENCES [dbo].[PetCategory] ([PetCategoryID])
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD FOREIGN KEY([ProductCategoryID])
REFERENCES [dbo].[ProductCategory] ([ProductCategoryID])
GO
ALTER TABLE [dbo].[Reviews]  WITH CHECK ADD FOREIGN KEY([ProductID])
REFERENCES [dbo].[Products] ([ProductID])
GO
ALTER TABLE [dbo].[Reviews]  WITH CHECK ADD FOREIGN KEY([UserName])
REFERENCES [dbo].[Users] ([UserName])
GO
USE [master]
GO
ALTER DATABASE [PetShop] SET  READ_WRITE 
GO
