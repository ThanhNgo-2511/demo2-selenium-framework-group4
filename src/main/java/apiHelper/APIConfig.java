package apiHelper;

public class APIConfig {
    public  static final String TOKEN_CYBERSOFT = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5Mb3AiOiJUcmFpbmluZyBnaeG6o25nIHZpw6puIGN5YmVyc29mdCAyMDIyIiwiSGV0SGFuU3RyaW5nIjoiMzAvMTEvMjAyOCIsIkhldEhhblRpbWUiOiIxODU5MTU1MjAwMDAwIiwibmJmIjoxNTg0MjkxNjAwLCJleHAiOjE4NTkzMDI4MDB9.9nOWAOoO7NtipuO-A-4_8kwzVp7j5HSdXjEegqTgcXI";
    public static final String BASE_URI = "https://elearningnew.cybersoft.edu.vn";

    // Endpoint cho quan ly khoa hoc
    public static final String CATEGORY_COURSE_MANAGER = "/api/QuanLyKhoaHoc";
    public static final String COURSE_LIST_ENDPOINT = CATEGORY_COURSE_MANAGER + "/LayDanhSachKhoaHoc_PhanTrang";
    public static final String COURSE_DETAIL_ENDPOINT = CATEGORY_COURSE_MANAGER + "/LayThongTinKhoaHoc";
    public static final String ENROLL_COURSE_ENDPOINT = CATEGORY_COURSE_MANAGER +"/DangKyKhoaHoc";
    public static final String LEAVE_COURSE_ENDPOINT = CATEGORY_COURSE_MANAGER +"/HuyGhiDanh";

    // Endpoint cho quan ly nguoi dung
    public static final String CATEGORY_USER_MANAGER = "/api/QuanLyNguoiDung";
    public static final String LOGIN_ENDPOINT = CATEGORY_USER_MANAGER + "/DangNhap";
    public static final String SIGNIN_ENDPOINT = CATEGORY_USER_MANAGER + "/DangKy";
    public static final String USER_INFOR_ENDPOINT = CATEGORY_USER_MANAGER + "/ThongTinTaiKhoan";

}
