# Demo 2 Selenium Framework Group 4

Dự án kiểm thử tự động giao diện web bằng Selenium, TestNG và Gradle. Các test tập trung vào đăng nhập, đăng ký, quản lý khóa học và hồ sơ cá nhân.

## Công nghệ

- Java
- Selenium WebDriver
- TestNG
- Gradle
- Log4j 2
- ExtentReports

## Cấu trúc dự án

```text
.
├── src/
│   ├── main/java/
│   │   ├── base/
│   │   ├── drivers/
│   │   ├── helper/
│   │   ├── pages/
│   │   └── reports/
│   └── test/
│       ├── java/testcases/
│       └── resources/
├── build.gradle
├── settings.gradle
├── gradlew
└── gradlew.bat
```

## Test suites

- **Authentication:** đăng nhập, đăng ký, đăng xuất
- **Course:** danh sách, chi tiết, đăng ký và hủy khóa học
- **Personal Profile:** xem và chỉnh sửa hồ sơ cá nhân

## Yêu cầu môi trường

- JDK 17 trở lên
- Google Chrome
- Truy cập được website đang được kiểm thử

Gradle Wrapper đã có trong repository, không cần cài Gradle riêng.

## Cấu hình dữ liệu

Ứng dụng đọc dữ liệu từ `src/test/resources/data.properties`. Tạo file local từ mẫu:

Windows PowerShell:

```powershell
Copy-Item src/test/resources/data.example.properties src/test/resources/data.properties
```

Linux/macOS:

```bash
cp src/test/resources/data.example.properties src/test/resources/data.properties
```

Sau đó thay các giá trị mẫu bằng dữ liệu phù hợp. `data.properties` được git-ignore để tránh commit thông tin tài khoản; không đưa credentials thật lên repository. Một số giá trị có thể truyền dưới dạng system property, ví dụ:

```bash
./gradlew testProfile -Dprofile.username=your_username -Dprofile.password=your_password
```

## Chạy test

Windows:

```powershell
.\gradlew.bat test
.\gradlew.bat testProfile
.\gradlew.bat testCourse
.\gradlew.bat testAuthen
```

Linux/macOS:

```bash
./gradlew test
./gradlew testProfile
./gradlew testCourse
./gradlew testAuthen
```

Các suite TestNG được cấu hình trong `src/test/resources/testng-*.xml`.

## Thành phần chính

- `base/BaseTest.java`: khởi tạo và đóng WebDriver, quản lý vòng đời test và báo cáo
- `drivers/`: tạo và quản lý WebDriver
- `pages/`: Page Object cho các màn hình
- `helper/`: các hàm hỗ trợ dùng chung
- `reports/`: cấu hình ExtentReports

## Báo cáo

Báo cáo HTML được tạo tại `testoutput/html`. Khi test thất bại, framework sẽ ghi log và cố gắng đính kèm ảnh chụp màn hình vào báo cáo.
