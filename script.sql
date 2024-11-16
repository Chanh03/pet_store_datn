USE [master]
GO
/****** Object:  Database [PetShop]    Script Date: 11/15/2024 6:43:27 PM ******/
CREATE DATABASE [PetShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PetShop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\PetShop.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
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
EXEC sys.sp_db_vardecimal_storage_format N'PetShop', N'ON'
GO
ALTER DATABASE [PetShop] SET QUERY_STORE = ON
GO
ALTER DATABASE [PetShop] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [PetShop]
GO
/****** Object:  Table [dbo].[Authorities]    Script Date: 11/15/2024 6:43:27 PM ******/
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
/****** Object:  Table [dbo].[BookingService]    Script Date: 11/15/2024 6:43:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BookingService](
	[BookServiceID] [int] IDENTITY(1,1) NOT NULL,
	[NumberPhone] [nvarchar](20) NOT NULL,
	[BookDate] [date] NOT NULL,
	[BookStatus] [nvarchar](50) NOT NULL,
	[Note] [nvarchar](255) NOT NULL,
	[UserName] [varchar](50) NOT NULL,
	[ServiceID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[BookServiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderProductDetails]    Script Date: 11/15/2024 6:43:27 PM ******/
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
PRIMARY KEY CLUSTERED 
(
	[OrderProductDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 11/15/2024 6:43:27 PM ******/
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
/****** Object:  Table [dbo].[OrderStatus]    Script Date: 11/15/2024 6:43:27 PM ******/
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
/****** Object:  Table [dbo].[PaymentStatus]    Script Date: 11/15/2024 6:43:27 PM ******/
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
/****** Object:  Table [dbo].[PetCategory]    Script Date: 11/15/2024 6:43:27 PM ******/
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
/****** Object:  Table [dbo].[Pets]    Script Date: 11/15/2024 6:43:27 PM ******/
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
/****** Object:  Table [dbo].[ProductCategory]    Script Date: 11/15/2024 6:43:27 PM ******/
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
/****** Object:  Table [dbo].[Products]    Script Date: 11/15/2024 6:43:27 PM ******/
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
/****** Object:  Table [dbo].[Reviews]    Script Date: 11/15/2024 6:43:27 PM ******/
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
/****** Object:  Table [dbo].[Roles]    Script Date: 11/15/2024 6:43:27 PM ******/
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
/****** Object:  Table [dbo].[Service]    Script Date: 11/15/2024 6:43:27 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Service](
	[ServiceID] [int] IDENTITY(1,1) NOT NULL,
	[ServiceName] [nvarchar](255) NOT NULL,
	[Price] [int] NOT NULL,
	[Description] [nvarchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ServiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SliderBar]    Script Date: 11/15/2024 6:43:27 PM ******/
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
/****** Object:  Table [dbo].[Users]    Script Date: 11/15/2024 6:43:27 PM ******/
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
/****** Object:  Table [dbo].[Voucher]    Script Date: 11/15/2024 6:43:27 PM ******/
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

INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (1, N'vietanh', N'ADMIN')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (2, N'phuc', N'STAFF')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (6, N'hien', N'STAFF')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (7, N'118351832271174317706', N'STAFF')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (8, N'vietanh1', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (9, N'vietanh10', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (10, N'vietanh11', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (11, N'vietanh12', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (12, N'vietanh13', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (13, N'vietanh14', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (14, N'vietanh15', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (15, N'vietanh16', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (16, N'vietanh17', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (17, N'vietanh18', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (18, N'vietanh19', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (19, N'vietanh2', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (20, N'vietanh20', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (21, N'vietanh3', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (22, N'vietanh4', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (23, N'vietanh5', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (24, N'vietanh6', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (25, N'vietanh7', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (26, N'vietanh8', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (27, N'vietanh9', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (28, N'longndh', N'USER')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (29, N'vietanh1999', N'ADMIN')
INSERT [dbo].[Authorities] ([Id], [UserName], [RoleId]) VALUES (31, N'105688808844996435335', N'USER')
SET IDENTITY_INSERT [dbo].[Authorities] OFF
GO
SET IDENTITY_INSERT [dbo].[OrderProductDetails] ON 

INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (1, 1, 3, 10, 20)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (2, 1, 5, 10, 20)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (3, 1, 6, 10, 20)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (4, 1, 7, 10, 20)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (5, 1, 8, 10, 20000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (6, 1, 9, 10, 20000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (7, 1, 10, 10, 20000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (8, 1, 11, 10, 20000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (9, 1, 12, 10, 20000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (10, 1, 13, 10, 20000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (11, 1, 14, 10, 20000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (12, 1, 15, 10, 20000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (13, 1, 16, 10, 20000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (14, 1, 17, 10, 20000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (15, 1, 10, 10, 20)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (16, 26, 8, 3, 10000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (17, 27, 8, 4, 10000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (18, 27, 6, 4, 10000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (19, 27, 7, 1, 10000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (20, 27, 10, 4, 10000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (21, 27, 5, 4, 10000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (22, 27, 9, 3, 10000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (23, 35, 47, 2, 1900000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (24, 35, 44, 1, 280000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (25, 35, 45, 1, 45000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (26, 35, 9, 1, 70000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (27, 35, 46, 2, 99999999)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (28, 35, 10, 1, 30000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (29, 36, 46, 1, 99999999)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (30, 37, 46, 1, 99999999)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (31, 38, 47, 1, 1900000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (32, 39, 9, 1, 70000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (33, 40, 9, 1, 70000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (34, 41, 45, 1, 45000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (35, 42, 47, 1, 1900000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (36, 43, 44, 1, 280000)
INSERT [dbo].[OrderProductDetails] ([OrderProductDetailID], [OrderID], [ProductID], [Quantity], [Price]) VALUES (37, 44, 47, 1, 1900000)
SET IDENTITY_INSERT [dbo].[OrderProductDetails] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (1, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 99999, N'vietanh', 1, 5, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (2, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 99999, N'vietanh', 1, 2, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (3, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 99999, N'vietanh', 1, 2, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (4, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 99999, N'vietanh', 1, 2, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (5, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 99999, N'vietanh', 1, 2, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (6, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 99999, N'vietanh', 1, 2, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (7, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 99999, N'vietanh', 1, 2, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (8, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 99999, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (9, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 99999, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (10, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 99999, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (11, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 99999, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (12, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 99999, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (13, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 123445, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (14, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 5846747, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (15, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 99999, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (16, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 99999, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (17, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 99999, N'vietanh', 1, 3, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (18, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 400000, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (19, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 350000, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (20, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 280000, N'vietanh', 1, 4, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (21, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 0, N'vietanh', 1, 5, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (22, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 0, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (23, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 0, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (24, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 0, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (25, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 30000, N'vietanh', 1, 4, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (26, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 30000, N'118351832271174317706', 1, 5, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (27, CAST(N'2024-11-03' AS Date), N'Nguyễn Văn A, Số 123, Đường Nguyễn Trãi, Phường 5, Quận 1, Thành phố Hồ Chí Minh, Việt Nam', 200000, N'118351832271174317706', 1, 5, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (28, CAST(N'2024-11-03' AS Date), N'vietanh', 1040000, N'vietanh', 1, 5, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (29, CAST(N'2024-11-03' AS Date), N'Hello con cu', 3160000, N'vietanh', 1, 5, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (30, CAST(N'2024-11-03' AS Date), N'vietanh', 0, N'vietanh', 1, 5, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (31, CAST(N'2024-11-03' AS Date), N'vietanh', 0, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (32, CAST(N'2024-11-03' AS Date), N'vietanh', 345000, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (33, CAST(N'2024-11-03' AS Date), N'vietanh', 1900000, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (34, CAST(N'2024-11-03' AS Date), N'vietanh', 204224998, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (35, CAST(N'2024-11-03' AS Date), N'vietanh', 204224998, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (36, CAST(N'2024-11-03' AS Date), N'vietanh', 99999999, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (37, CAST(N'2024-11-03' AS Date), N'vietanh', 99999999, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (38, CAST(N'2024-11-03' AS Date), N'vietanh', 1950000, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (39, CAST(N'2024-11-03' AS Date), N'vietanh', 70000, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (40, CAST(N'2024-11-03' AS Date), N'vietanh', 120000, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (41, CAST(N'2024-11-03' AS Date), N'vietanh', 95000, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (42, CAST(N'2024-11-14' AS Date), N'vietanh', 1900000, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (43, CAST(N'2024-11-14' AS Date), N'vietanh', 330000, N'vietanh', 1, 1, 1)
INSERT [dbo].[Orders] ([OrderID], [OrderDate], [ShippingAddress], [TotalAmount], [UserName], [Enable], [OrderStatusID], [PaymentStatusID]) VALUES (44, CAST(N'2024-11-14' AS Date), N'vietanh', 1950000, N'vietanh', 1, 1, 1)
SET IDENTITY_INSERT [dbo].[Orders] OFF
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

INSERT [dbo].[PaymentStatus] ([PaymentStatusID], [StatusPayment]) VALUES (1, N'Đang chờ thanh toán')
SET IDENTITY_INSERT [dbo].[PaymentStatus] OFF
GO
SET IDENTITY_INSERT [dbo].[PetCategory] ON 

INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (1, N'Chó', N'cho.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (2, N'Chim cảnh', N'chim.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (3, N'Cá cảnh', N'ca.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (4, N'Hamster', N'hamster.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (5, N'Thỏ', N'tho.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (6, N'Rùa cảnh', N'rua.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (7, N'Nhím kiểng', N'nhim.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (8, N'Bò sát', N'bosat.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (9, N'Chuột lang', N'chuotlang.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (10, N'Sóc cảnh', N'soc.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (11, N'Cá heo cảnh', N'caheo.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (12, N'Vẹt', N'vet.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (13, N'Chồn sương', N'chon.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (14, N'Cua cảnh', N'cua.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (15, N'Ốc cảnh', N'oc.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (16, N'Cá Betta', N'betta.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (17, N'Cá Koi', N'koi.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (18, N'Nhện kiểng', N'nhen.webp')
INSERT [dbo].[PetCategory] ([PetCategoryID], [PetCategoryName], [Photo]) VALUES (19, N'Kỳ nhông', N'kynhong.webp')
SET IDENTITY_INSERT [dbo].[PetCategory] OFF
GO
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'12', N'BRUH', 2, 1, 299000, N'afaf', N'meo_Sphynx.webp', N'Lông đen', N'lông dài', 1, 1, 5, CAST(N'2024-11-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'12223233', N'Hello Các Cháu', 1, 0, 399000, N'232323232', N'meo_Ragdoll.webp', N'Lông đen', N'lông dài', 0, 0, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'38b9dd23-68cd-40dc-b8ee-2da8dd2c356a', N'Hello Các Cháu 123', 2, 1, 564646, N'dfgdgdggggggfddđf', N'meo_Bengal.webp', N'Lông đen12121', N'Lông mịn', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'46fe4767-4550-4061-b451-e1c4f2fe0c91', N'Hello Các Cháu 123', 2, 1, 564646, N'dfgdgdggggggfddđf', N'meo_ba_tu.webp', N'Lông đen12121', N'Lông mịn', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'5df3ec70-c757-41e4-9a39-193307689e89', N'Hello Các Cháu 123', 2, 1, 564646, N'dfgdgdggggggfddđf', N'meo_Anh.webp', N'Lông đen12121', N'Lông mịn', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'969771a5-4418-4919-b3e8-6e07f0b2abf4', N'Hello Các Cháu 123', 2, 1, 564646, N'dfgdgdggggggfddđf', N'cho_Shiba.webp', N'Lông đen12121', N'Lông mịn', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'ABC XYZ 123', N'Hello Các Cháu 123', 2, 1, 564646, N'sdfsfsfsdf', N'cho_Poodle.webp', N'Lông đen12121', N'Lông mịn', 1, 0, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'BAC', N'Cocker Spaniel', 5, 1, 1500000, N',senfksnfknskg', N'cho_Husky.webp', N'Lông đen', N'lông dài', 0, 0, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'BAC1', N'Thỏ đen', 1, 0, 120000, N',senfksnfknskg', N'cho_Chihuahua.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'BAC13', N'Beagle', 3, 0, 500000, N',senfksnfknskg', N'cho_Alaska.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'BAC134', N'Boxer', 2, 0, 600000, N',senfksnfknskg', N'meo_Sphynx.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'BUNNY-085565', N'Dalmatian', 4, 1, 800000, N',senfksnfknskg', N'meo_Ragdoll.webp', N'Lông đen', N'lông dài', 0, 1, 5, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'BUNNY-11212221', N'Rottweiler', 5, 1, 1200000, N',senfksnfknskg', N'meo_Bengal.webp', N'Lông đen', N'lông dài', 0, 1, 5, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'BUNNY-222233', N'Chihuahua', 1, 1, 400000, N',senfksnfknskg', N'meo_ba_tu.webp', N'Lông đen', N'lông dài', 0, 1, 5, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'BUNNY-355333', N'Pomeranian', 1, 1, 450000, N',senfksnfknskg', N'meo_Anh.webp', N'Lông đen', N'lông dài', 0, 1, 5, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'BUNNY-566654', N'Doberman', 5, 1, 900000, N'dfgdgfdfg', N'cho_Shiba.webp', N'dfgdgfdg', N'Lông mịn', 1, 0, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'BUNNY-5753355', N'Greyhound', 3, 1, 700000, N'Lông mịnLông mịnLông mịnLông mịnLông mịnLông', N'cho_Poodle.webp', N'Lông đen', N'Lông mịn', 0, 0, 5, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'c8f5a558-780b-4fc9-97fe-d177b11545a8', N'Hello Các Cháu 123', 2, 1, 564646, N'dfgdgdggggggfddđf', N'cho_Husky.webp', N'Lông đen12121', N'Lông mịn', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'CAT-1222', N'Border Collie', 2, 1, 750000, N',senfksnfknskg', N'cho_Chihuahua.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'CAT-1224', N'Saint Bernard', 4, 1, 1300000, N',senfksnfknskg', N'cho_Alaska.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'CAT-1226', N'Maltese', 1, 1, 650000, N',senfksnfknskg', N'meo_Sphynx.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'CAT-1233', N'Australian Shepherd', 2, 1, 850000, N',senfksnfknskg', N'meo_Ragdoll.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'CAT-123313', N'Papillon', 3, 1, 700000, N',senfksnfknskg', N'meo_Bengal.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'CAT-1312313', N'Weimaraner', 4, 1, 900000, N',senfksnfknskg', N'meo_ba_tu.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'CAT-1999', N'Akita', 3, 1, 1100000, N',senfksnfknskg', N'meo_Anh.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'CAT-19992', N'Samoyed', 4, 1, 950000, N',senfksnfknskg', N'cho_Shiba.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'CAT-2003', N'Whippet', 2, 1, 550000, N',senfksnfknskg', N'cho_Poodle.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'CAT-223444', N'Bichon Frise', 2, 1, 700000, N',senfksnfknskg', N'cho_Husky.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'CAT-23424', N'Great Dane', 5, 1, 1400000, N',senfksnfknskg', N'cho_Chihuahua.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'CAT-2342424', N'Shiba Inu', 3, 1, 850000, N',senfksnfknskg', N'cho_Alaska.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'CAT-33232', N'Boston Terrier', 1, 1, 600000, N',senfksnfknskg', N'meo_Sphynx.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'CAT-42444', N'Pekingese', 2, 1, 500000, N',senfksnfknskg', N'meo_Ragdoll.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'CAT-4646', N'Airedale Terrier', 3, 1, 550000, N',senfksnfknskg', N'meo_Bengal.webp', N'Lông đen', N'lông dài', 0, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'dffc7a9b-74ed-4fbc-b655-a33b15a146bd', N'Hello Các Cháu 123', 2, 1, 564646, N'dfgdgdggggggfddđf', N'meo_ba_tu.webp', N'Lông đen12121', N'Lông mịn', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1216', N'Saluki', 3, 1, 800000, N',senfksnfknskg', N'meo_Anh.webp', N'Lông đen', N'lông dài', 1, 0, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1217', N'Basenji', 2, 1, 700000, N',senfksnfknskg', N'cho_Shiba.webp', N'Lông đen', N'lông dài', 1, 0, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1218', N'Newfoundland', 5, 1, 1350000, N',senfksnfknskg', N'cho_Poodle.webp', N'Lông đen', N'lông dài', 1, 0, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1219', N'Vizsla', 4, 1, 850000, N',senfksnfknskg', N'cho_Husky.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1220', N'Irish Setter', 3, 1, 800000, N',senfksnfknskg', N'cho_Chihuahua.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1221', N'Belgian Malinois', 3, 1, 1100000, N',senfksnfknskg', N'cho_Alaska.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1222', N'Scottish Terrier', 2, 1, 500000, N',senfksnfknskg', N'meo_Sphynx.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1223', N'Jack Russell Terrier', 1, 1, 450000, N',senfksnfknskg', N'meo_Ragdoll.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1224', N'Bullmastiff', 5, 1, 1250000, N',senfksnfknskg', N'meo_Bengal.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1225', N'Bloodhound', 4, 1, 1000000, N',senfksnfknskg', N'meo_ba_tu.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1226', N'Alaskan Malamute', 4, 1, 1200000, N',senfksnfknskg', N'meo_Anh.webp', N'Lông đen', N'lông dài', 1, 1, 4, CAST(N'2024-12-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1227', N'Cavalier King Charles Spaniel', 3, 1, 900000, N',senfksnfknskg', N'cho_Shiba.webp', N'Lông đen', N'lông dài', 1, 1, 4, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1228', N'Havanese', 1, 1, 600000, N',senfksnfknskg', N'cho_Poodle.webp', N'Lông đen', N'lông dài', 1, 1, 4, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1229', N'Rhodesian Ridgeback', 4, 1, 950000, N',senfksnfknskg', N'cho_Husky.webp', N'Lông đen', N'lông dài', 1, 1, 4, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1230', N'Welsh Corgi', 2, 1, 800000, N',senfksnfknskg', N'cho_Chihuahua.webp', N'Lông đen', N'lông dài', 1, 1, 4, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1231', N'English Bulldog', 5, 1, 1100000, N',senfksnfknskg', N'cho_Alaska.webp', N'Lông đen', N'lông dài', 1, 1, 4, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1232', N'Lhasa Apso', 2, 1, 450000, N',senfksnfknskg', N'meo_Sphynx.webp', N'Lông đen', N'lông dài', 1, 1, 4, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1241', N'Leonberger', 4, 1, 1300000, N',senfksnfknskg', N'meo_Ragdoll.webp', N'Lông đen', N'lông dài', 1, 1, 4, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1242', N'Miniature Schnauzer', 3, 1, 750000, N',senfksnfknskg', N'meo_Bengal.webp', N'Lông đen', N'lông dài', 1, 1, 4, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1243', N'Staffordshire Bull Terrier', 3, 1, 800000, N',senfksnfknskg', N'meo_ba_tu.webp', N'Lông đen', N'lông dài', 1, 1, 4, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1244', N'West Highland White Terrier', 1, 1, 500000, N',senfksnfknskg', N'meo_Anh.webp', N'Lông đen', N'lông dài', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1245', N'Pharaoh Hound', 4, 1, 900000, N',senfksnfknskg', N'cho_Shiba.webp', N'Lông đen', N'lông dài', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1246', N'Tibetan Mastiff', 5, 1, 1400000, N',senfksnfknskg', N'cho_Poodle.webp', N'Lông đen', N'lông dài', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1247', N'Cane Corso', 4, 1, 1250000, N',senfksnfknskg', N'cho_Husky.webp', N'Lông đen', N'lông dài', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1248', N'Keeshond', 2, 1, 700000, N',senfksnfknskg', N'cho_Chihuahua.webp', N'Lông đen', N'lông dài', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1249', N'Pug', 1, 1, 500000, N',senfksnfknskg', N'cho_Alaska.webp', N'Lông đen', N'lông dài', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1250', N'Italian Greyhound', 2, 1, 650000, N',senfksnfknskg', N'meo_Sphynx.webp', N'Lông đen', N'lông dài', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1251', N'Manchester Terrier', 1, 1, 600000, N',senfksnfknskg', N'meo_Ragdoll.webp', N'Lông đen', N'lông dài', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1252', N'Toy Poodle', 1, 1, 550000, N',senfksnfknskg', N'meo_Bengal.webp', N'Lông đen', N'lông dài', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1253', N'Schipperke', 3, 1, 700000, N',senfksnfknskg', N'meo_ba_tu.webp', N'Lông đen', N'lông dài', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1254', N'Alaska', 4, 1, 1200000, N',senfksnfknskg', N'meo_Anh.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1255', N'Bullmastiff', 3, 1, 987887893, N',senfksnfknskg', N'cho_Shiba.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1256', N'Bloodhound', 3, 1, 987887894, N',senfksnfknskg', N'cho_Poodle.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1257', N'Alaskan Malamute', 2, 1, 987887895, N',senfksnfknskg', N'cho_Husky.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1258', N'Cavalier King Charles Spaniel', 2, 1, 987887896, N',senfksnfknskg', N'cho_Chihuahua.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1259', N'Havanese', 2, 1, 987887897, N',senfksnfknskg', N'cho_Alaska.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1260', N'Rhodesian Ridgeback', 1, 1, 987887898, N',senfksnfknskg', N'meo_Sphynx.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1261', N'Welsh Corgi', 1, 1, 987887899, N',senfksnfknskg', N'meo_Ragdoll.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1262', N'English Bulldog', 1, 1, 987887900, N',senfksnfknskg', N'meo_Bengal.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1263', N'Lhasa Apso', 4, 1, 987887901, N',senfksnfknskg', N'meo_ba_tu.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1264', N'Leonberger', 4, 1, 987887902, N',senfksnfknskg', N'meo_Anh.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1265', N'Miniature Schnauzer', 4, 1, 987887903, N',senfksnfknskg', N'cho_Shiba.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1266', N'Staffordshire Bull Terrier', 4, 1, 987887904, N',senfksnfknskg', N'cho_Poodle.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1267', N'West Highland White Terrier', 4, 1, 987887905, N',senfksnfknskg', N'cho_Husky.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1268', N'Pharaoh Hound', 4, 1, 987887906, N',senfksnfknskg', N'cho_Chihuahua.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1269', N'Tibetan Mastiff', 2, 1, 987887907, N',senfksnfknskg', N'cho_Alaska.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1270', N'Cane Corso', 2, 1, 987887908, N',senfksnfknskg', N'meo_Sphynx.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1271', N'Keeshond', 2, 1, 987887909, N',senfksnfknskg', N'meo_Ragdoll.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1272', N'Pug', 2, 1, 987887910, N',senfksnfknskg', N'meo_Bengal.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1273', N'Italian Greyhound', 2, 1, 987887911, N',senfksnfknskg', N'meo_ba_tu.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1274', N'Manchester Terrier', 2, 1, 987887912, N',senfksnfknskg', N'meo_Anh.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1275', N'Toy Poodle', 2, 1, 987887913, N',senfksnfknskg', N'cho_Shiba.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1276', N'Schipperke', 4, 1, 987887914, N',senfksnfknskg', N'cho_Poodle.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1277', N' alaska', 4, 1, 987887915, N',senfksnfknskg', N'cho_Husky.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1278', N' alaska', 4, 1, 987887916, N',senfksnfknskg', N'cho_Chihuahua.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1279', N' alaska', 4, 1, 987887917, N',senfksnfknskg', N'cho_Alaska.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1280', N' alaska', 4, 1, 987887918, N',senfksnfknskg', N'meo_Sphynx.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1281', N' alaska', 1, 1, 987887919, N',senfksnfknskg', N'meo_Ragdoll.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1282', N' alaska', 1, 1, 987887920, N',senfksnfknskg', N'meo_Bengal.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1283', N' alaska', 1, 1, 987887921, N',senfksnfknskg', N'meo_ba_tu.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1284', N' alaska', 3, 1, 987887922, N',senfksnfknskg', N'meo_Anh.webp', N'Lông đen', N'lông dài', 1, 1, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1285', N' alaska', 3, 1, 987887923, N',senfksnfknskg', N'cho_Shiba.webp', N'Lông đen', N'lông dài', 1, 1, 2, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1286', N' alaska', 3, 1, 987887924, N',senfksnfknskg', N'cho_Poodle.webp', N'Lông đen', N'lông dài', 1, 1, 2, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1287', N' alaska', 3, 1, 987887925, N',senfksnfknskg', N'cho_Husky.webp', N'Lông đen', N'lông dài', 1, 1, 2, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1288', N' alaska', 2, 1, 987887926, N',senfksnfknskg', N'cho_Chihuahua.webp', N'Lông đen', N'lông dài', 1, 1, 2, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1289', N' alaska', 2, 0, 987887928, N',senfksnfknskg', N'cho_Alaska.webp', N'Lông đen', N'lông dài', 0, 0, 2, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
GO
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'DOG-1290', N' alaska', 1, 0, 987887928, N',senfksnfknskg', N'pet-placeholder.webp', N'Lông đen', N'lông dài', 0, 0, 2, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'ea40277f-b688-4dcf-9937-5002cdc4e0cc', N'BRUH', 2, 1, 987887928, N'sfsfef', N'pet-placeholder.webp', N'Lông đen', N'lông dài', 1, 1, 5, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'fd9e0349-d123-4a1e-a43c-d47fa557451f', N'Hello Các Cháu 123', 2, 1, 564646, N'dfgdgdggggggfddđf', N'pet-placeholder.webp', N'Lông đen12121', N'Lông mịn', 1, 1, 3, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
INSERT [dbo].[Pets] ([PetID], [Breed], [Age], [Gender], [Price], [PetDescription], [Photo], [Famous], [Hair], [Enable], [Available], [PetCategoryID], [CreateDate]) VALUES (N'TEST ADD', N'Thỏ đen', 2, 1, 120000, N'ưadwdadwawdwadwad', N'pet-placeholder.webp', N'Lông đen', N'Lông mịn', 1, 0, 1, CAST(N'2024-08-14T00:16:04.000' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[ProductCategory] ON 

INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (1, N'Thức ăn cho chó', N'thucanhat.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (2, N'Thức ăn cho mèo', N'thucanhat.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (3, N'Đồ chơi cho chó', N'do_choi.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (4, N'Đồ chơi cho mèo', N'do_choi.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (5, N'Phụ kiện cho thú cưng', N'thoi_trang.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (6, N'Khay vệ sinh cho mèo', N'suatam_daugoi.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (7, N'Dây dắt cho chó', N'dungcuvesinh.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (8, N'Bát ăn cho thú cưng', N'thucanhat.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (9, N'Thú nhồi bông cho chó', N'suatam_daugoi.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (10, N'Thú nhồi bông cho mèo', N'dungcuvesinh.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (11, N'Bộ đồ tắm cho chó', N'thoi_trang.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (12, N'Sữa tắm cho mèo', N'suatam_daugoi.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (13, N'Dụng cụ chăm sóc móng cho chó', N'thucanhat.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (14, N'Dụng cụ chải lông cho mèo', N'dungcuvesinh.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (15, N'Mũ bảo hiểm cho chó', N'thoi_trang.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (16, N'Giường nằm cho thú cưng', N'dungcuvesinh.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (17, N'Thảm chơi cho mèo', N'thoi_trang.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (18, N'Cái nơ cho chó', N'suatam_daugoi.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (19, N'Băng rôn cho thú cưng', N'thoi_trang.webp')
INSERT [dbo].[ProductCategory] ([ProductCategoryID], [ProductCategoryName], [Photo]) VALUES (20, N'Gối cho thú cưng', N'dungcuvesinh.webp')
SET IDENTITY_INSERT [dbo].[ProductCategory] OFF
GO
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (3, N'Giường cho chó lớn', 450000, N'o_dem.webp', 1, 1, N'Giường rộng rãi và êm ái', 1, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (5, N'Áo cho mèo', 120000, N'product_ao_chomeo.webp', 1, 2, N'Áo quần dễ thương và thời trang', 2, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (6, N'Áo khoác cho chó', 150000, N'product_ao_khoac_cho.webp', 1, 1, N'Áo khoác ấm áp', 3, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (7, N'Ba lô cho thú cưng', 250000, N'product_balo.webp', 1, 1, N'Ba lô tiện lợi giúp mang theo thú cưng trong các chuyến du lịch', 4, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (8, N'Bát ăn cho thú cưng', 50000, N'product_bat_an.webp', 1, 5, N'Bát ăn thiết kế vững chắc', 5, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (9, N'Bình nước cho thú cưng', 70000, N'product_binh_nuoc.webp', 1, 3, N'Bình nước dễ sử dụng', 6, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (10, N'Bóng đồ chơi cho mèo', 30000, N'product_bong_cho_meo.webp', 1, 10, N'Bóng đồ chơi nhỏ nhắn giúp thú cưng vận động và giải trí', 7, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (11, N'Thức ăn cho mèo', 90000, N'product_cat.webp', 1, 2, N'Thức ăn dinh dưỡng', 2, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (12, N'Chuồng cho chó', 600000, N'product_chuong_cho.webp', 1, 1, N'Chuồng chắc chắn và an toàn', 9, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (13, N'Dầu gội cho chó', 80000, N'product_dau_goi_cho.webp', 1, 3, N'Dầu gội giúp làm sạch và chăm sóc lông cho chó', 10, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (14, N'Dầu gội cho mèo', 75000, N'product_dau_goi_meo.webp', 1, 2, N'Dầu gội cho mèo', 11, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (15, N'Giường ngủ cho chó', 400000, N'product_giuong_cho.webp', 1, 1, N'Giường ngủ êm ái', 16, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (16, N'Giường ngủ cho mèo', 350000, N'product_giuong_meo.webp', 1, 1, N'Giường ngủ mềm mại giúp mèo có không gian nghỉ ngơi và thư giãn thoải mái.', 16, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (17, N'Lược chải lông cho chó', 45000, N'product_long_cho.webp', 1, 4, N'Lược chải giúp loại bỏ lông rụng và làm mượt bộ lông của chó', 13, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (18, N'Lược chải lông cho mèo', 45000, N'product_long_meo.webp', 1, 4, N'Lược chải cho mèo giúp lông luôn mượt mà và không bị rối', 14, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (19, N'Mũ cho chó', 60000, N'product_non_cho.webp', 1, 2, N'Mũ bảo vệ và trang trí cho chó', 15, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (20, N'Nệm nằm cho chó', 500000, N'product_o_dem_meo.webp', 1, 1, N'Nệm nằm êm ái giúp chó có nơi nghỉ ngơi thoải mái', 16, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (21, N'Nệm nằm cho mèo', 450000, N'product_o_dem_meo.webp', 1, 1, N'Nệm nằm mềm mại giúp mèo thư giãn và ngủ ngon', 16, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (22, N'Pate cho chó', 25000, N'product_pate_cho.webp', 1, 8, N'Pate thơm ngon và bổ dưỡng giúp chó có bữa ăn hấp dẫn và bổ sung chất dinh dưỡng.', 1, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (24, N'Pate cho mèo', 20000, N'product_pate_meo.webp', 1, 10, N'Pate cho mèo dễ ăn và đầy đủ chất dinh dưỡng', 2, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (25, N'Snack cho chó', 15000, N'product_Snack_cho.webp', 1, 15, N'Snack ngon miệng giúp chó vui vẻ và bổ sung thêm năng lượng', 1, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (26, N'Snack cho mèo', 15000, N'product_Snack_meo.webp', 1, 15, N'Snack cho mèo giúp chúng thưởng thức món ăn ngon và bổ sung thêm dưỡng chất.', 2, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (27, N'Sữa tắm cho chó', 90000, N'product_sua_tam_cho.webp', 1, 2, N'Sữa tắm giúp làm sạch lông chó', 10, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (28, N'Sữa tắm khô cho thú cưng', 85000, N'product_sua_tam_kho.webp', 1, 1, N'Sữa tắm khô tiện lợi giúp làm sạch thú cưng mà không cần tắm nước', 12, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (29, N'Sữa tắm cho mèo', 80000, N'product_sua_tam_meo.webp', 1, 3, N'Sữa tắm dành cho mèo giúp làm sạch và dưỡng lông', 11, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (30, N'Súp ăn cho thú cưng', 20000, N'product_sup_cho.webp', 1, 5, N'Súp bổ dưỡng cung cấp chất lỏng và dinh dưỡng cho thú cưng', 12, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (31, N'Tã lót cho thú cưng', 35000, N'product_ta.webp', 1, 10, N'Tã lót giúp giữ vệ sinh cho thú cưng', 6, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (32, N'Thức ăn cho chó Beagle', 100000, N'product_thuc_an_Beagle.webp', 1, 1, N'Thức ăn đặc biệt dành cho chó Beagle', 1, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (33, N'Thức ăn cho chó Corgi', 110000, N'product_thuc_an_Corgi.webp', 1, 2, N'Thức ăn phù hợp cho chó Corgi', 1, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (34, N'Thức ăn cho chó Labrador', 120000, N'product_thuc_an_Labrador.webp', 1, 1, N'Thức ăn dành cho chó Labrador', 1, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (35, N'Thức ăn hạt cho thú cưng', 90000, N'product_thuc_an_Hat.webp', 1, 3, N'Thức ăn hạt tiện lợi', 2, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (36, N'Thức ăn cho chó Poodle', 115000, N'product_thuc_an_Poodle.webp', 1, 2, N'Thức ăn dành cho chó Poodle', 1, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (37, N'Túi xách cho chó mèo', 300000, N'product_tui_xach_chomeo.webp', 1, 1, N'Túi xách tiện lợi giúp bạn mang theo chó hoặc mèo khi đi chơi hoặc du lịch', 4, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (38, N'Váy cho mèo', 80000, N'product_vay_meo.webp', 1, 5, N'Váy dễ thương và thoải mái cho mèo', 2, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (39, N'Xích cho thú cưng', 70000, N'product_xich.webp', 1, 3, N'Xích chắc chắn giúp bạn kiểm soát thú cưng khi đi dạo', 5, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (40, N'Vòng cổ cho chó', 50000, N'product_vong_co.webp', 1, 5, N'Vòng cổ thời trang và chắc chắn giúp bạn dễ dàng nhận diện và kiểm soát chó', 5, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (41, N'Sữa tắm và dầu gội cho thú cưng', 95000, N'suatam_daugoi.webp', 1, 2, N'Bộ sản phẩm bao gồm sữa tắm và dầu gội dành cho thú cưng', 12, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (42, N'Thời trang cho thú cưng', 100000, N'thoi_trang.webp', 1, 1, N'Các bộ đồ thời trang cho thú cưng giúp chúng trở nên xinh đẹp và nổi bật hơn', 3, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (43, N'Thức ăn cho chó nhỏ', 85000, N'thucanhat.webp', 1, 1, N'Thức ăn cho chó nhỏ cung cấp đầy đủ dưỡng chất để chúng phát triển nhanh chóng và khỏe mạnh.', 1, CAST(N'2024-11-14T02:14:30.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (44, N'Túi xách và ba lô cho thú cưng', 280000, N'tuixach_balo.webp', 1, 1, N'Túi xách và ba lô tiện lợi giúp bạn dễ dàng mang theo thú cưng khi di chuyển hoặc đi du lịch.', 4, CAST(N'2024-11-14T02:14:33.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (45, N'Vòng cổ cho mèo', 45000, N'vongco.webp', 1, 6, N'Vòng cổ xinh xắn dành cho mèo', 5, CAST(N'2024-11-14T02:14:33.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (46, N'Placeholder sản phẩm', 99999999, N'product-placeholder.webp', 1, 0, N'Placeholder cho sản phẩm chưa có mô tả chi tiết', 6, CAST(N'2024-12-14T02:14:33.000' AS DateTime))
INSERT [dbo].[Products] ([ProductID], [ProductName], [Price], [Photo], [Available], [Quantity], [ProductDescription], [ProductCategoryID], [CreateDate]) VALUES (47, N'ưddddwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww', 1900000, N'product_chuong_cho.webp', 1, 999, N'K có nhìn cái gì', 3, CAST(N'2024-11-14T02:18:34.460' AS DateTime))
SET IDENTITY_INSERT [dbo].[Products] OFF
GO
SET IDENTITY_INSERT [dbo].[Reviews] ON 

INSERT [dbo].[Reviews] ([ReviewID], [Comment], [UserName], [ProductID], [Rating], [ReviewDate]) VALUES (1, N'Chào em cô gái mở đường', N'vietanh', 3, 4, CAST(N'2024-11-13T23:28:01.000' AS DateTime))
INSERT [dbo].[Reviews] ([ReviewID], [Comment], [UserName], [ProductID], [Rating], [ReviewDate]) VALUES (2, N'Chào em cô gái mở đường', N'vietanh', 3, 4, CAST(N'2024-11-13T23:28:01.000' AS DateTime))
INSERT [dbo].[Reviews] ([ReviewID], [Comment], [UserName], [ProductID], [Rating], [ReviewDate]) VALUES (3, N'Chào em cô gái mở đường', N'vietanh', 3, 4, CAST(N'2024-11-13T23:28:01.000' AS DateTime))
INSERT [dbo].[Reviews] ([ReviewID], [Comment], [UserName], [ProductID], [Rating], [ReviewDate]) VALUES (4, N'Chào em cô gái mở đường', N'vietanh', 3, 4, CAST(N'2024-11-13T23:28:01.000' AS DateTime))
INSERT [dbo].[Reviews] ([ReviewID], [Comment], [UserName], [ProductID], [Rating], [ReviewDate]) VALUES (5, N'Chào em cô gái mở đường', N'vietanh', 3, 4, CAST(N'2024-11-13T23:28:01.000' AS DateTime))
INSERT [dbo].[Reviews] ([ReviewID], [Comment], [UserName], [ProductID], [Rating], [ReviewDate]) VALUES (6, N'Chào em cô gái mở đường', N'vietanh', 3, 3, CAST(N'2024-11-13T23:28:01.000' AS DateTime))
INSERT [dbo].[Reviews] ([ReviewID], [Comment], [UserName], [ProductID], [Rating], [ReviewDate]) VALUES (7, N'Chào em cô gái mở đường', N'vietanh', 3, 3, CAST(N'2024-11-13T23:28:01.000' AS DateTime))
INSERT [dbo].[Reviews] ([ReviewID], [Comment], [UserName], [ProductID], [Rating], [ReviewDate]) VALUES (8, N'Chào em cô gái mở đường', N'vietanh', 3, 3, CAST(N'2024-11-13T23:28:01.000' AS DateTime))
INSERT [dbo].[Reviews] ([ReviewID], [Comment], [UserName], [ProductID], [Rating], [ReviewDate]) VALUES (9, N'Chào em cô gái mở đường', N'vietanh', 3, 3, CAST(N'2024-11-13T23:28:01.000' AS DateTime))
INSERT [dbo].[Reviews] ([ReviewID], [Comment], [UserName], [ProductID], [Rating], [ReviewDate]) VALUES (10, N'Chào em cô gái mở đường', N'vietanh', 3, 3, CAST(N'2024-11-13T23:28:01.000' AS DateTime))
INSERT [dbo].[Reviews] ([ReviewID], [Comment], [UserName], [ProductID], [Rating], [ReviewDate]) VALUES (11, N'Chào em cô gái mở đường', N'vietanh', 3, 3, CAST(N'2024-11-13T23:28:01.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Reviews] OFF
GO
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'ADMIN', N'ROLE_ADMIN')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'STAFF', N'ROLE_STAFF')
INSERT [dbo].[Roles] ([Id], [Name]) VALUES (N'USER', N'ROLE_USER')
GO
SET IDENTITY_INSERT [dbo].[SliderBar] ON 

INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (1, N'slider1.jpg')
INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (2, N'slider4.jpg')
INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (3, N'slider1.jpg')
INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (4, N'slider4.jpg')
INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (5, N'slider1.jpg')
INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (6, N'slider4.jpg')
INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (7, N'slider1.jpg')
INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (8, N'slider4.jpg')
INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (9, N'slider3.jpg')
INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (10, N'slider2.jpg')
INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (11, N'slider1.jpg')
INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (12, N'slider4.jpg')
INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (13, N'slider2.jpg')
INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (14, N'slider1.jpg')
INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (15, N'slider1.jpg')
INSERT [dbo].[SliderBar] ([SliderId], [SliderImage]) VALUES (16, N'slider4.jpg')
SET IDENTITY_INSERT [dbo].[SliderBar] OFF
GO
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'105688808844996435335', N'$2a$10$8W/9EqTZakXM6SEXw4NBSet3vTPQK9HKeIO9C4/JSVBePvqrVJj.2', N'Ngo Viet Anh (FPL HCM)', N'anhnvps30934@fpt.edu.vn', N'None', N'None', 1, N'd79ecdfa-42f8-4fdb-9135-df921ef28b2e', CAST(N'2024-11-09T17:16:14.533' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'118351832271174317706', N'$2a$10$GNfr72CIGVZ/MgRV8zSwWuOlprFcET7M3oE/qwHgm2p4f8Au.H8Fe', N'Ngô Anh (Vanh)', N'hello', N'0567567575', N'464645666666666464444', 1, N'8ca929ee-db17-4270-a92e-d5a4477bf44f', CAST(N'2024-11-03T16:38:16.703' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'hien', N'$2a$10$SzQ2QZxe7EuslUYJefVTPevFfmJ1rNg/wWAc5xeFCigpn6KXxQlG6', N'Lê haongf hiền', N'hello', N'3534535352', N'hien 121i21212121dư', 1, N'4545645645', CAST(N'2024-10-05T21:43:01.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'longndh', N'$2a$10$0u93JWBGLRBU9dltZ9YzH.Z6nePHlt.mcK4v7ZS3LhvBQg.yixh3y', N'Anh Anh', N'hello', N'0374156548', N'con cá vàng màu đen. Xã Phong Nẫm. Thành phố Phan Thiết. Tỉnh Bình Thuận', 1, N'3817ed5a-bbdd-49db-a01e-b9fcc01f279a', CAST(N'2024-11-09T16:41:38.570' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'phuc', N'$2a$10$SzQ2QZxe7EuslUYJefVTPevFfmJ1rNg/wWAc5xeFCigpn6KXxQlG6', N'phuc', N'hello', N'0909090909', N'phuc', 1, N'4554646', CAST(N'2024-10-05T21:28:28.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'VIỆT ANH TOP 1 SERVER', N'hello', N'4687458967', N'vietanh', 1, N'4674586', CAST(N'1999-10-05T21:28:02.000' AS DateTime), N'f4af1c3b-8336-4195-9e2d-5d6eca93e2e0', NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh1', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'4444444444', N'msfnkjsnjdddddd', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh10', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh11', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh12', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh13', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh14', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh15', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh16', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh17', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh18', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh19', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh1999', N'$2a$10$rdlx05muQnox3Byyf7.G6.RpHifZTdE5XvwlhcIxpbc1aa70Ggx7i', N'Anh Anh', N'ngovietanh121@gmail.com', N'0374156548', N'con cá vàng màu đen. Xã Phú Lạc. Huyện Tuy Phong. Tỉnh Bình Thuận', 1, N'a30674fd-7be7-48b5-9d0f-841907da64eb', CAST(N'2024-11-09T16:42:59.843' AS DateTime), N'f6802ea8-3909-4b7e-acf8-f98dea5d0184', CAST(N'2024-11-09T17:38:07.603' AS DateTime))
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh2', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh20', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh3', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh4', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh5', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh6', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh7', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh8', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
INSERT [dbo].[Users] ([UserName], [UserPassword], [FullName], [Email], [PhoneNumber], [UserAddress], [Enable], [ActiveToken], [DateCreated], [TemporaryGUID], [TempGuidExpir]) VALUES (N'vietanh9', N'$2a$10$lb3Ri6Iggu15PGICM9iKKe699s4N/DnTg/oWYOb1bQchEdvGz/Oeq', N'viet anh', N'hello', N'9358934895', N'msfnkjsnj', 1, N'3487587345', CAST(N'2024-10-09T17:36:53.000' AS DateTime), NULL, NULL)
GO
INSERT [dbo].[Voucher] ([VoucherID], [Discount], [ExpiryDate], [Enable]) VALUES (N'ANHVANHDEPTRAI', 50000, CAST(N'2024-11-03' AS Date), 1)
INSERT [dbo].[Voucher] ([VoucherID], [Discount], [ExpiryDate], [Enable]) VALUES (N'ANHVANHDEPTRAI1', 100000, CAST(N'2024-11-10' AS Date), 0)
INSERT [dbo].[Voucher] ([VoucherID], [Discount], [ExpiryDate], [Enable]) VALUES (N'ANHVANHDEPTRAI2', 1000000, CAST(N'2024-11-10' AS Date), 0)
INSERT [dbo].[Voucher] ([VoucherID], [Discount], [ExpiryDate], [Enable]) VALUES (N'ANHVANHDEPTRAI3', 100000, CAST(N'2024-11-10' AS Date), 0)
INSERT [dbo].[Voucher] ([VoucherID], [Discount], [ExpiryDate], [Enable]) VALUES (N'ANHVANHDEPTRAI4', 100000, CAST(N'2024-11-10' AS Date), 0)
INSERT [dbo].[Voucher] ([VoucherID], [Discount], [ExpiryDate], [Enable]) VALUES (N'ANHVANHDEPTRAI5', 100000, CAST(N'2024-11-10' AS Date), 0)
GO
ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD FOREIGN KEY([RoleId])
REFERENCES [dbo].[Roles] ([Id])
GO
ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD FOREIGN KEY([UserName])
REFERENCES [dbo].[Users] ([UserName])
GO
ALTER TABLE [dbo].[BookingService]  WITH CHECK ADD FOREIGN KEY([ServiceID])
REFERENCES [dbo].[Service] ([ServiceID])
GO
ALTER TABLE [dbo].[BookingService]  WITH CHECK ADD FOREIGN KEY([UserName])
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
