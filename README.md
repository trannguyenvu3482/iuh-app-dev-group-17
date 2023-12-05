# iuh-app-dev-group-17

## Nhánh code
Nhánh `code` được dành riêng để lưu trữ tất cả các file source code và các biểu đồ diagrams của nhóm.

## Ngôn ngữ
Nhóm 17 chọn **Java** làm ngôn ngữ lập trình chính cho dự án

## Cấu trúc cây thư mục
```
diagrams/ (Chứa các diagrams như Use case, Class,...)
src/main/
└── java
│   └── com.nhom17.quanlykaraoke
│       ├── common (Chứa các lớp thông dụng)
│       │   └── MyFrame.java
│       ├── entities (Chứa các lớp thực thể)
│       │   └── NhanVien.java
│       ├── dao (Chứa các lớp DAO)
│       │   └── NhanVienDAO.java
│       ├── bus (Chứa các lớp BLL (BUS) xử lí logic)
│       │   └── NhanVienBUS.java
│       ├── gui (Chứa các lớp liên quan đến UI)
│       │   └── QuanLyGUI.java
│       ├── database (Chứa các lớp liên quan đến kết nối DB và các file query)
│       │   └── backupDatabase.sql
│       ├── utils (Chứa các lớp để xử lý dữ liệu như chuỗi, ngày,...)
│       │   ├── StringUtils.java
|       │   ├── DateUtils.java
|       │   └── ...
|       └── Main.java
└── resources
    ├── images (Chứa các file hình)
    │   └── logo.png
    ├── pdf (Chứa các file PDF như hướng dẫn sử dụng, hóa đơn,...)
    │   └── HUONG-DAN-SU-DUNG.pdf
    └── hibernate.cfg.xml (File config của Hibernate)
diagrams/ (Chứa các file diagrams)
.env.example (Chứa ví dụ về file biến môi trường .env)
libs/ (Chứa các file thư viện khác)
pom.xml (File config dự án Maven)
...
```

## Danh sách các thư viện ngoài được sử dụng:
1. Hibernate 6.1.1
2. mssql-jdbc 9.5.0jre17
3. JFreeChart 1.5.4
4. JCalendar 1.4
5. iTextPDF 5.5.13
6. MigLayout 3.7.4
7. FlatLaf 3.2.2
8. jbcrypt 0.4
9. twilio 9.13.1
10. dotenv-java 3.0.0
11. ikonli-swing 12.3.1

## Các công cụ khác
1. maven-shade-plugin để xuất file .jar cho project Maven
2. Launch4j để tạo file .exe từ file .jar

## Cập nhật
Nhánh `code` sẽ được cập nhật trong các tuần thực hiện coding. Tất cả file code đều tuân thủ coding convention chuẩn của Java  
Tham khảo thêm: https://gpcoder.com/1775-tieu-chuan-coding-trong-java-coding-standards

## Sử dụng
Để truy cập vào tài liệu, hãy clone repo này về bằng lệnh `git clone`.
Để tải phần mềm, hãy xem tại phần Releases
