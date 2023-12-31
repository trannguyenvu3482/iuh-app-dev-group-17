USE [master]
GO
/****** Object:  Database [quanlykaraoke]    Script Date: 03/10/2023 5:02:17 CH ******/
CREATE DATABASE [quanlykaraoke]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'quanlykaraoke', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\quanlykaraoke.mdf' , SIZE = 10240KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'quanlykaraoke_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\quanlykaraoke_log.ldf' , SIZE = 5120KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [quanlykaraoke] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [quanlykaraoke].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [quanlykaraoke] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [quanlykaraoke] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [quanlykaraoke] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [quanlykaraoke] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [quanlykaraoke] SET ARITHABORT OFF 
GO
ALTER DATABASE [quanlykaraoke] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [quanlykaraoke] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [quanlykaraoke] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [quanlykaraoke] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [quanlykaraoke] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [quanlykaraoke] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [quanlykaraoke] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [quanlykaraoke] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [quanlykaraoke] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [quanlykaraoke] SET  DISABLE_BROKER 
GO
ALTER DATABASE [quanlykaraoke] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [quanlykaraoke] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [quanlykaraoke] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [quanlykaraoke] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [quanlykaraoke] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [quanlykaraoke] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [quanlykaraoke] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [quanlykaraoke] SET RECOVERY FULL 
GO
ALTER DATABASE [quanlykaraoke] SET  MULTI_USER 
GO
ALTER DATABASE [quanlykaraoke] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [quanlykaraoke] SET DB_CHAINING OFF 
GO
ALTER DATABASE [quanlykaraoke] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [quanlykaraoke] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [quanlykaraoke] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [quanlykaraoke] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [quanlykaraoke] SET QUERY_STORE = OFF
GO
USE [quanlykaraoke]
GO
/****** Object:  Table [dbo].[ChiTietDichVu]    Script Date: 03/10/2023 5:02:17 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietDichVu](
	[maPhieuDatPhong] [char](7) NOT NULL,
	[maHangHoa] [char](5) NOT NULL,
	[soLuong] [int] NOT NULL,
 CONSTRAINT [PK_ChiTietDichVu] PRIMARY KEY CLUSTERED 
(
	[maHangHoa] ASC,
	[maPhieuDatPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietPhieuDatPhong]    Script Date: 03/10/2023 5:02:18 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietPhieuDatPhong](
	[maPhong] [char](4) NOT NULL,
	[maPhieuDatPhong] [char](7) NOT NULL,
	[thoiLuong] [int] NOT NULL,
 CONSTRAINT [PK_ChiTietPhieuDatPhong] PRIMARY KEY CLUSTERED 
(
	[maPhong] ASC,
	[maPhieuDatPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 03/10/2023 5:02:18 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucVu](
	[maChucVu] [char](5) NOT NULL,
	[tenChucVu] [nvarchar](30) NOT NULL,
 CONSTRAINT [PK_ChucVu_maChucVu] PRIMARY KEY CLUSTERED 
(
	[maChucVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HangHoa]    Script Date: 03/10/2023 5:02:18 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HangHoa](
	[maHangHoa] [char](5) NOT NULL,
	[tenHangHoa] [nvarchar](30) NOT NULL,
	[maLoaiHangHoa] [char](6) NOT NULL,
	[soLuongTon] [int] NOT NULL,
	[donGia] [money] NOT NULL,
	[trangThai] [bit] NOT NULL,
 CONSTRAINT [PK_HangHoa_maHangHoa] PRIMARY KEY CLUSTERED 
(
	[maHangHoa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 03/10/2023 5:02:18 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKhachHang] [char](5) NOT NULL,
	[hoTen] [nvarchar](30) NOT NULL,
	[soDienThoai] [varchar](10) NOT NULL,
	[CCCD] [varchar](12) NOT NULL,
 CONSTRAINT [PK_KhachHang_maKhachHang] PRIMARY KEY CLUSTERED 
(
	[maKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiHangHoa]    Script Date: 03/10/2023 5:02:18 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiHangHoa](
	[maLoaiHangHoa] [char](6) NOT NULL,
	[tenLoaiHangHoa] [nvarchar](30) NOT NULL,
 CONSTRAINT [PK_LoaiHangHoa_maLoaiHangHoa] PRIMARY KEY CLUSTERED 
(
	[maLoaiHangHoa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiPhong]    Script Date: 03/10/2023 5:02:18 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiPhong](
	[maLoaiPhong] [char](5) NOT NULL,
	[tenLoaiPhong] [nvarchar](30) NOT NULL,
	[kichThuoc] [int] NOT NULL,
	[phuPhi] [money] NOT NULL,
 CONSTRAINT [PK_LoaiPhong_maLoaiPhong] PRIMARY KEY CLUSTERED 
(
	[maLoaiPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 03/10/2023 5:02:18 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNhanVien] [char](6) NOT NULL,
	[hoTen] [nvarchar](30) NOT NULL,
	[gioiTinh] [int] NOT NULL,
	[matKhau] [varchar](60) NOT NULL,
	[ngaySinh] [date] NOT NULL,
	[maChucVu] [char](5) NOT NULL,
	[soDienThoai] [varchar](10) NOT NULL,
	[CCCD] [varchar](12) NOT NULL,
	[anhDaiDien] [varbinary](max) NOT NULL,
	[trangThai] [bit] NOT NULL,
 CONSTRAINT [PK_NhanVien_maNV] PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuDatPhong]    Script Date: 03/10/2023 5:02:18 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuDatPhong](
	[maPhieuDatPhong] [char](7) NOT NULL,
	[maNhanVien] [char](6) NOT NULL,
	[thoiGianBatDau] [datetime] NOT NULL,
	[thoiGianKetThuc] [datetime] NOT NULL,
	[trangThai] [bit] NOT NULL,
	[tongTien] [money] NOT NULL,
	[maKhachHang] [char](5) NOT NULL,
 CONSTRAINT [PK_PhieuDatPhong_maPhieuDatPhong] PRIMARY KEY CLUSTERED 
(
	[maPhieuDatPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Phong]    Script Date: 03/10/2023 5:02:18 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phong](
	[maPhong] [char](4) NOT NULL,
	[maLoaiPhong] [char](5) NOT NULL,
	[trangThai] [bit] NOT NULL,
 CONSTRAINT [PK_Phong_maPhong] PRIMARY KEY CLUSTERED 
(
	[maPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChiTietDichVu] ADD  DEFAULT ((0)) FOR [soLuong]
GO
ALTER TABLE [dbo].[ChiTietPhieuDatPhong] ADD  DEFAULT ((0)) FOR [thoiLuong]
GO
ALTER TABLE [dbo].[HangHoa] ADD  DEFAULT ((0)) FOR [soLuongTon]
GO
ALTER TABLE [dbo].[HangHoa] ADD  DEFAULT ((0)) FOR [donGia]
GO
ALTER TABLE [dbo].[HangHoa] ADD  DEFAULT ((0)) FOR [trangThai]
GO
ALTER TABLE [dbo].[LoaiPhong] ADD  DEFAULT ((0)) FOR [kichThuoc]
GO
ALTER TABLE [dbo].[LoaiPhong] ADD  DEFAULT ((0)) FOR [phuPhi]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT ((-1)) FOR [gioiTinh]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT ((0)) FOR [trangThai]
GO
ALTER TABLE [dbo].[PhieuDatPhong] ADD  DEFAULT ((0)) FOR [trangThai]
GO
ALTER TABLE [dbo].[PhieuDatPhong] ADD  DEFAULT ((0)) FOR [tongTien]
GO
ALTER TABLE [dbo].[Phong] ADD  DEFAULT ((0)) FOR [trangThai]
GO
ALTER TABLE [dbo].[ChiTietDichVu]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietDichVu_maHangHoa] FOREIGN KEY([maHangHoa])
REFERENCES [dbo].[HangHoa] ([maHangHoa])
GO
ALTER TABLE [dbo].[ChiTietDichVu] CHECK CONSTRAINT [FK_ChiTietDichVu_maHangHoa]
GO
ALTER TABLE [dbo].[ChiTietDichVu]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietDichVu_maPhieuDatPhong] FOREIGN KEY([maPhieuDatPhong])
REFERENCES [dbo].[PhieuDatPhong] ([maPhieuDatPhong])
GO
ALTER TABLE [dbo].[ChiTietDichVu] CHECK CONSTRAINT [FK_ChiTietDichVu_maPhieuDatPhong]
GO
ALTER TABLE [dbo].[ChiTietPhieuDatPhong]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietPhieuDatPhong_maPhieuDatPhong] FOREIGN KEY([maPhieuDatPhong])
REFERENCES [dbo].[PhieuDatPhong] ([maPhieuDatPhong])
GO
ALTER TABLE [dbo].[ChiTietPhieuDatPhong] CHECK CONSTRAINT [FK_ChiTietPhieuDatPhong_maPhieuDatPhong]
GO
ALTER TABLE [dbo].[ChiTietPhieuDatPhong]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietPhieuDatPhong_maPhong] FOREIGN KEY([maPhong])
REFERENCES [dbo].[Phong] ([maPhong])
GO
ALTER TABLE [dbo].[ChiTietPhieuDatPhong] CHECK CONSTRAINT [FK_ChiTietPhieuDatPhong_maPhong]
GO
ALTER TABLE [dbo].[HangHoa]  WITH CHECK ADD  CONSTRAINT [FK_HangHoa_maLoaiHangHoa] FOREIGN KEY([maLoaiHangHoa])
REFERENCES [dbo].[LoaiHangHoa] ([maLoaiHangHoa])
GO
ALTER TABLE [dbo].[HangHoa] CHECK CONSTRAINT [FK_HangHoa_maLoaiHangHoa]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_maChucVu] FOREIGN KEY([maChucVu])
REFERENCES [dbo].[ChucVu] ([maChucVu])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_maChucVu]
GO
ALTER TABLE [dbo].[PhieuDatPhong]  WITH CHECK ADD  CONSTRAINT [FK_PhieuDatPhong_maKhachHang] FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
GO
ALTER TABLE [dbo].[PhieuDatPhong] CHECK CONSTRAINT [FK_PhieuDatPhong_maKhachHang]
GO
ALTER TABLE [dbo].[PhieuDatPhong]  WITH CHECK ADD  CONSTRAINT [FK_PhieuDatPhong_maNhanVien] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[PhieuDatPhong] CHECK CONSTRAINT [FK_PhieuDatPhong_maNhanVien]
GO
ALTER TABLE [dbo].[Phong]  WITH CHECK ADD  CONSTRAINT [FK_Phong_maLoaiPhong] FOREIGN KEY([maLoaiPhong])
REFERENCES [dbo].[LoaiPhong] ([maLoaiPhong])
GO
ALTER TABLE [dbo].[Phong] CHECK CONSTRAINT [FK_Phong_maLoaiPhong]
GO
EXEC [quanlykaraoke].sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Database Quản lý karaoke Nnice' 
GO
USE [master]
GO
ALTER DATABASE [quanlykaraoke] SET  READ_WRITE 
GO
