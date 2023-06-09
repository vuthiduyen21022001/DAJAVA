USE [QLTT]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 16/05/2022 10:43:40 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [varchar](20) NOT NULL,
	[TenNV] [nvarchar](50) NOT NULL,
	[GT] [nvarchar](50) NOT NULL,
	[NS] [int] NOT NULL,
	[SDT] [varchar](50) NOT NULL,
	[DC] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhapVN]    Script Date: 16/05/2022 10:43:40 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhapVN](
	[MaVatNuoi] [varchar](20) NOT NULL,
	[LoaiVN] [nvarchar](50) NOT NULL,
	[TenVN] [nvarchar](50) NOT NULL,
	[SLNhap] [int] NOT NULL,
	[TLNhap] [int] NOT NULL,
	[GiaNhap] [float] NOT NULL,
	[NgayNhap] [date] NOT NULL,
	[GhiChu] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_NhapVN] PRIMARY KEY CLUSTERED 
(
	[MaVatNuoi] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QTNuoi]    Script Date: 16/05/2022 10:43:40 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QTNuoi](
	[MaQT] [varchar](20) NOT NULL,
	[MaVatNuoi] [varchar](20) NOT NULL,
	[MaTA] [varchar](20) NOT NULL,
	[MaThuoc] [varchar](20) NOT NULL,
	[MaNV] [varchar](20) NOT NULL,
	[ThoiGian] [date] NOT NULL,
	[TinhTrangSK] [nvarchar](50) NOT NULL,
	[MoTa] [nvarchar](50) NULL,
 CONSTRAINT [PK_QTNuoi] PRIMARY KEY CLUSTERED 
(
	[MaQT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 16/05/2022 10:43:40 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[TaiKhoan] [varchar](50) NOT NULL,
	[MatKhau] [nvarchar](100) NOT NULL,
	[SDT] [nvarchar](50) NULL,
	[CauHoi] [nvarchar](100) NULL,
	[CauTL] [nvarchar](100) NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[TaiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ThucAn]    Script Date: 16/05/2022 10:43:40 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThucAn](
	[MaTA] [varchar](20) NOT NULL,
	[TenTA] [nvarchar](500) NOT NULL,
	[SoLuongTA] [int] NOT NULL,
	[GiaTA] [float] NOT NULL,
	[NhaCC] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_ThucAn] PRIMARY KEY CLUSTERED 
(
	[MaTA] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Thuoc]    Script Date: 16/05/2022 10:43:40 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Thuoc](
	[MaThuoc] [varchar](20) NOT NULL,
	[TenThuoc] [nvarchar](500) NOT NULL,
	[TacDung] [nvarchar](500) NOT NULL,
	[SoLuongT] [int] NOT NULL,
	[GiaThuoc] [float] NOT NULL,
	[NhaCCT] [nvarchar](500) NOT NULL,
 CONSTRAINT [PK_Thuoc] PRIMARY KEY CLUSTERED 
(
	[MaThuoc] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[XuatBan]    Script Date: 16/05/2022 10:43:40 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[XuatBan](
	[MaXuat] [varchar](50) NOT NULL,
	[MaVatNuoi] [varchar](20) NOT NULL,
	[SLBan] [int] NOT NULL,
	[TLBan] [int] NOT NULL,
	[GiaBan] [float] NOT NULL,
	[NgayBan] [date] NOT NULL,
 CONSTRAINT [PK_XuatBan] PRIMARY KEY CLUSTERED 
(
	[MaXuat] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [GT], [NS], [SDT], [DC]) VALUES (N'NV01', N'Nguyễn Thị C', N'Nữ', 1997, N'0123456789', N'123, khu C, Phường B, Q.C, TP.HCM')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [GT], [NS], [SDT], [DC]) VALUES (N'NV02', N'Trần Văn A', N'Nam', 1999, N'0123456789', N'123, khu C, Phường B, Q.C, TP.HCM')
GO
INSERT [dbo].[NhapVN] ([MaVatNuoi], [LoaiVN], [TenVN], [SLNhap], [TLNhap], [GiaNhap], [NgayNhap], [GhiChu]) VALUES (N'1', N'Gia cầm', N'Gà Đông Tảo', 100, 50, 1000000, CAST(N'2022-05-02' AS Date), N'')
INSERT [dbo].[NhapVN] ([MaVatNuoi], [LoaiVN], [TenVN], [SLNhap], [TLNhap], [GiaNhap], [NgayNhap], [GhiChu]) VALUES (N'2', N'Gia cầm', N'Vịt', 200, 100, 2000000, CAST(N'2022-05-02' AS Date), N'')
INSERT [dbo].[NhapVN] ([MaVatNuoi], [LoaiVN], [TenVN], [SLNhap], [TLNhap], [GiaNhap], [NgayNhap], [GhiChu]) VALUES (N'3', N'Gia súc', N'Bò', 20, 400, 3000000, CAST(N'2022-05-03' AS Date), N'')
INSERT [dbo].[NhapVN] ([MaVatNuoi], [LoaiVN], [TenVN], [SLNhap], [TLNhap], [GiaNhap], [NgayNhap], [GhiChu]) VALUES (N'4', N'Gia súc', N'Heo', 50, 250, 250000, CAST(N'2022-05-03' AS Date), N'')
INSERT [dbo].[NhapVN] ([MaVatNuoi], [LoaiVN], [TenVN], [SLNhap], [TLNhap], [GiaNhap], [NgayNhap], [GhiChu]) VALUES (N'5', N'Gia cầm', N'Ngỗng', 15, 15, 2000000, CAST(N'2022-05-04' AS Date), N'')
INSERT [dbo].[NhapVN] ([MaVatNuoi], [LoaiVN], [TenVN], [SLNhap], [TLNhap], [GiaNhap], [NgayNhap], [GhiChu]) VALUES (N'6', N'Gia súc', N'Dê', 40, 400, 400000, CAST(N'2022-05-04' AS Date), N'')
GO
INSERT [dbo].[QTNuoi] ([MaQT], [MaVatNuoi], [MaTA], [MaThuoc], [MaNV], [ThoiGian], [TinhTrangSK], [MoTa]) VALUES (N'QT01', N'1', N'TA01', N'T01', N'NV01', CAST(N'2022-05-06' AS Date), N'Tốt', NULL)
INSERT [dbo].[QTNuoi] ([MaQT], [MaVatNuoi], [MaTA], [MaThuoc], [MaNV], [ThoiGian], [TinhTrangSK], [MoTa]) VALUES (N'QT02', N'5', N'TA03', N'T03', N'NV02', CAST(N'2022-05-06' AS Date), N'Tốt', NULL)
GO
INSERT [dbo].[TaiKhoan] ([TaiKhoan], [MatKhau], [SDT], [CauHoi], [CauTL]) VALUES (N'minhchien', N'123', N'0358455134', N'Loại động vật thích nhất là gì?', N'chó')
GO
INSERT [dbo].[ThucAn] ([MaTA], [TenTA], [SoLuongTA], [GiaTA], [NhaCC]) VALUES (N'TA01', N'Cám', 100, 1000000, N'Nhà máy B')
INSERT [dbo].[ThucAn] ([MaTA], [TenTA], [SoLuongTA], [GiaTA], [NhaCC]) VALUES (N'TA02', N'Thức ăn gia cầm', 50, 300000, N'Nhà máy C')
INSERT [dbo].[ThucAn] ([MaTA], [TenTA], [SoLuongTA], [GiaTA], [NhaCC]) VALUES (N'TA03', N'Bắp', 300, 4000000, N'Nhà máy B')
INSERT [dbo].[ThucAn] ([MaTA], [TenTA], [SoLuongTA], [GiaTA], [NhaCC]) VALUES (N'TA04', N'Thức ăn gia súc', 50, 6000000, N'Nhà máy C')
GO
INSERT [dbo].[Thuoc] ([MaThuoc], [TenThuoc], [TacDung], [SoLuongT], [GiaThuoc], [NhaCCT]) VALUES (N'T01', N'T01', N'Phòng chống cúm gia cầm', 220, 1500000, N'Trung tâm thú Y A')
INSERT [dbo].[Thuoc] ([MaThuoc], [TenThuoc], [TacDung], [SoLuongT], [GiaThuoc], [NhaCCT]) VALUES (N'T02', N'T02', N'Phòng chống tả', 100, 200000, N'Trung tâm thú Y A')
INSERT [dbo].[Thuoc] ([MaThuoc], [TenThuoc], [TacDung], [SoLuongT], [GiaThuoc], [NhaCCT]) VALUES (N'T03', N'T03', N'', 100, 200000, N'Trung tâm thú Y A')
INSERT [dbo].[Thuoc] ([MaThuoc], [TenThuoc], [TacDung], [SoLuongT], [GiaThuoc], [NhaCCT]) VALUES (N'T04', N'T04', N'', 500, 100000, N'Trung tâm thú Y A')
GO
INSERT [dbo].[XuatBan] ([MaXuat], [MaVatNuoi], [SLBan], [TLBan], [GiaBan], [NgayBan]) VALUES (N'B01', N'5', 15, 600, 6000000, CAST(N'2022-05-05' AS Date))
INSERT [dbo].[XuatBan] ([MaXuat], [MaVatNuoi], [SLBan], [TLBan], [GiaBan], [NgayBan]) VALUES (N'B02', N'6', 40, 1600, 8000000, CAST(N'2022-05-05' AS Date))
GO
ALTER TABLE [dbo].[QTNuoi]  WITH CHECK ADD  CONSTRAINT [FK_QTNuoi_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[QTNuoi] CHECK CONSTRAINT [FK_QTNuoi_NhanVien]
GO
ALTER TABLE [dbo].[QTNuoi]  WITH CHECK ADD  CONSTRAINT [FK_QTNuoi_NhapVN] FOREIGN KEY([MaVatNuoi])
REFERENCES [dbo].[NhapVN] ([MaVatNuoi])
GO
ALTER TABLE [dbo].[QTNuoi] CHECK CONSTRAINT [FK_QTNuoi_NhapVN]
GO
ALTER TABLE [dbo].[QTNuoi]  WITH CHECK ADD  CONSTRAINT [FK_QTNuoi_ThucAn] FOREIGN KEY([MaTA])
REFERENCES [dbo].[ThucAn] ([MaTA])
GO
ALTER TABLE [dbo].[QTNuoi] CHECK CONSTRAINT [FK_QTNuoi_ThucAn]
GO
ALTER TABLE [dbo].[QTNuoi]  WITH CHECK ADD  CONSTRAINT [FK_QTNuoi_Thuoc] FOREIGN KEY([MaThuoc])
REFERENCES [dbo].[Thuoc] ([MaThuoc])
GO
ALTER TABLE [dbo].[QTNuoi] CHECK CONSTRAINT [FK_QTNuoi_Thuoc]
GO
ALTER TABLE [dbo].[XuatBan]  WITH CHECK ADD  CONSTRAINT [FK_XuatBan_NhapVN1] FOREIGN KEY([MaVatNuoi])
REFERENCES [dbo].[NhapVN] ([MaVatNuoi])
GO
ALTER TABLE [dbo].[XuatBan] CHECK CONSTRAINT [FK_XuatBan_NhapVN1]
GO
