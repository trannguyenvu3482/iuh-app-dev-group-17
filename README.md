# iuh-app-dev-group-17

## Nhánh code
Nhánh `code` được dành riêng để lưu trữ tất cả các file source code và các biểu đồ diagrams của nhóm.

## Ngôn ngữ
Nhóm 17 chọn **Java** làm ngôn ngữ lập trình chính cho dự án

## Cấu trúc cây thư mục
```
diagrams/ (Chứa các diagrams như Use case, Class,...)
src/
└── nhom17.quanlykaraoke.app
    ├── dto (Chứa các lớp thực thể)
    │   └── NhanVienDTO.java
    ├── dao (Chứa các lớp DAO)
    │   └── NhanVienDAO.java
    ├── bus (Chứa các lớp BLL (BUS) xử lí logic)
    │   └── NhanVienBUS.java
    ├── ui (Chứa các lớp liên quan đến UI)
    │   └── NhanVien.java
    ├── database (Chứa các lớp liên quan đến kết nối DB và các file query)
    │   ├── ConnectDatabase.java
    │   ├── sql
    │   │   ├── KhoiTao.sql
    │   │   └── ...
    ├── utils (Chứa các lớp để xử lý dữ liệu như chuỗi, ngày,...)
    │   ├── StringUtils.java
    │   ├── DateUtils.java
    │   └── ...
    ├── resources (Chứa các tài nguyên như hình ảnh, file pdf,...)
    │   ├── images
    │   │   ├── icon.png
    │   │   ├── logo.png
    │   │   └── ...
    │   └── ...
    └── Main.java
libs/ (Chứa các file thư viện ngoài như JDBC)
```
## Cập nhật
Nhánh `code` sẽ được cập nhật trong các tuần thực hiện coding. Tất cả file code đều tuân thủ coding convention chuẩn của Java  
Tham khảo thêm: https://gpcoder.com/1775-tieu-chuan-coding-trong-java-coding-standards

## Sử dụng
Để truy cập vào tài liệu, hãy clone repo này về bằng lệnh `git clone`.
