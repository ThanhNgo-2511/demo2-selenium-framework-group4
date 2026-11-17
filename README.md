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
├── .gitignore                                # Các file/thư mục không đưa vào Git
├── build.gradle                              # Dependencies và cấu hình các task test Gradle
├── settings.gradle                           # Tên project Gradle
├── gradlew                                   # Gradle Wrapper cho Linux/macOS
├── gradlew.bat                               # Gradle Wrapper cho Windows
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar                # Thành phần chạy Gradle Wrapper
│       └── gradle-wrapper.properties         # Phiên bản/cấu hình Gradle Wrapper
├── src/
    ├── main/
    │   └── java/                             # Mã nguồn framework dùng chung
    │       ├── apiHelper/
    │       │   ├── APIAction.java            # Các thao tác gọi API phục vụ test
    │       │   └── APIConfig.java            # Cấu hình API
    │       ├── base/
    │       │   ├── BasePage.java             # Hành vi dùng chung cho Page Object
    │       │   └── BaseTest.java             # Khởi tạo/đóng WebDriver và lifecycle test
    │       ├── config/
    │       │   └── urlConfig.java             # Cấu hình URL
    │       ├── constants/
    │       │   └── TimeoutConstant.java       # Các giá trị timeout dùng chung
    │       ├── drivers/
    │       │   ├── ChromeDriverManager.java   # Khởi tạo Chrome WebDriver
    │       │   ├── DriverManager.java         # Quản lý WebDriver theo luồng
    │       │   ├── DriverManagerFactory.java  # Chọn DriverManager theo trình duyệt
    │       │   ├── EdgeDriverManager.java     # Khởi tạo Edge WebDriver
    │       │   ├── FireFoxDriverManager.java  # Khởi tạo Firefox WebDriver
    │       │   └── SafariDriverManager.java   # Khởi tạo Safari WebDriver
    │       ├── helper/
    │       │   ├── DataHelper.java            # Hỗ trợ dữ liệu test
    │       │   ├── PropertyHelper.java        # Đọc cấu hình properties
    │       │   └── TestCaseHelper.java        # Các thao tác hỗ trợ test case
    │       ├── models/
    │       │   ├── CourseModel.java           # Model dữ liệu khóa học
    │       │   └── UserModel.java             # Model dữ liệu người dùng
    │       ├── pages/
    │       │   ├── CommonPage.java            # Hành vi dùng chung cho các trang
    │       │   ├── CourseDetailPage.java     # Page Object trang chi tiết khóa học
    │       │   ├── CourseListPage.java       # Page Object danh sách khóa học
    │       │   ├── HomePage.java             # Page Object trang chủ
    │       │   ├── LoginPage.java            # Page Object đăng nhập/đăng xuất
    │       │   ├── ProfilePage.java          # Page Object hồ sơ cá nhân
    │       │   ├── RegisterPage.java         # Page Object đăng ký
    │       │   ├── components/                # Page Object cho thành phần tái sử dụng
    │       │   │   ├── Banner.java            # Thành phần banner
    │       │   │   ├── BaseCourseCard.java    # Hành vi chung của thẻ khóa học
    │       │   │   ├── FeaturedCourseCard.java # Thẻ khóa học nổi bật
    │       │   │   ├── RegisteredCourseCard.java # Thẻ khóa học đã đăng ký
    │       │   │   ├── StandardCourseCard.java # Thẻ khóa học thông thường
    │       │   │   └── TopNavigation.java     # Thanh điều hướng trên cùng
    │       │   └── modals/
    │       │       └── CommonModal.java       # Hành vi dùng chung cho hộp thoại
    │       └── reports/
    │           └── ExtentReportManager.java   # Tạo và quản lý ExtentReports
    └── test/
        ├── java/testcases/                    # Các test case theo nghiệp vụ
        │   ├── authentication/
        │   │   ├── LoginTest.java             # Kiểm thử đăng nhập
        │   │   ├── LogoutTest.java            # Kiểm thử đăng xuất
        │   │   └── RegisterTest.java          # Kiểm thử đăng ký
        │   ├── course/
        │   │   ├── CancelCourseTest.java      # Kiểm thử hủy khóa học
        │   │   ├── CourseDetailTest.java      # Kiểm thử chi tiết khóa học
        │   │   ├── CourseListTest.java        # Kiểm thử danh sách khóa học
        │   │   └── EnrollCourseTest.java      # Kiểm thử đăng ký khóa học
        │   └── personalProfile/
        │       ├── EditPersonalProfileTest.java  # Kiểm thử chỉnh sửa hồ sơ
        │       └── ViewPersonalProfileTest.java  # Kiểm thử xem hồ sơ
        └── resources/
            ├── data.example.properties       # Mẫu cấu hình; sao chép thành data.properties
            ├── data.properties               # Cấu hình local, bị git-ignore
            ├── extent.properties             # Cấu hình ExtentReports
            ├── extent-config.xml             # Giao diện/cấu hình báo cáo Extent
            ├── extent-pdf-config.yml         # Cấu hình báo cáo PDF
            ├── log4j2.xml                    # Cấu hình logging
            ├── testng-authentication.xml      # Suite TestNG authentication
            ├── testng-course.xml              # Suite TestNG course
            └── testng-profile.xml             # Suite TestNG personal profile
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
