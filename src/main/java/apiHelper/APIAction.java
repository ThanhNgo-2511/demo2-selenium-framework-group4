package apiHelper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.CourseModel;
import models.UserModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static apiHelper.APIConfig.*;

public class APIAction {

    protected RequestSpecification getBaseRequest() {
        return RestAssured.given()
                .baseUri(BASE_URI)
                .header("TokenCybersoft", TOKEN_CYBERSOFT)
                .header("Accept", "application/json")
                .contentType(ContentType.JSON);
    }

    public List<CourseModel> getCourseList(int page, int pageSize, String groudId) {
        Response response = getBaseRequest()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("MaNhom", groudId)
                .when()
                .get(COURSE_LIST_ENDPOINT);

        response.then().statusCode(200);

        return response.then()
                .extract()
                .jsonPath()
                .getList("items", CourseModel.class);
    }

    public int getCountPagination(int page, int pageSize, String groudId) {
        Response response = getBaseRequest()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("MaNhom", groudId)
                .when()
                .get(COURSE_LIST_ENDPOINT);

        response.then().statusCode(200);

        return response.then()
                .extract()
                .jsonPath().getInt("totalPages");
    }

    public CourseModel getCourseInforFromList(List<CourseModel> courselist, int index){
        return courselist.get(index-1);
    }

    public List<String> getCourseIdList(int page, int pageSize, String groudId) {
        List<CourseModel> courseList = getCourseList(page, pageSize, groudId);
        List<String> courseIdList = new ArrayList<>();
        ;
        for (CourseModel course : courseList) {
            courseIdList.add(course.getMaKhoaHoc());
        }
        return courseIdList;
    }

    public String getRandomCourseId(int page, int pageSize, String groupId) {
        List<String> courseIdList = getCourseIdList(page, pageSize, groupId);

        // Bảo vệ tránh lỗi IndexOutOfBoundsException nếu danh sách rỗng
        if (courseIdList.isEmpty()) {
            throw new IllegalStateException("Danh sách courseId từ API đang rỗng!");
        }

        // Lọc bỏ các courseId bị null hoặc rỗng/chỉ chứa khoảng trắng
        List<String> validCourseIds = courseIdList.stream()
                .filter(id -> id != null && !id.trim().isEmpty())
                .collect(Collectors.toList());

        // Guard Clause: Nếu sau khi lọc mà không còn courseId nào hợp lệ
        if (validCourseIds.isEmpty()) {
            throw new IllegalStateException("Không tìm thấy courseId hợp lệ nào (tất cả đều rỗng)!");
        }

        // Lấy random 1 chỉ số index từ 0 đến courseIdList.size() - 1
        int randomIndex = ThreadLocalRandom.current().nextInt(validCourseIds.size());

        return validCourseIds.get(randomIndex);
    }

    public CourseModel getCourseDetail(String courseId) {
        Response response = getBaseRequest()
                .queryParam("maKhoaHoc", courseId)
                .when()
                .get(COURSE_DETAIL_ENDPOINT);

        response.then().statusCode(200);

        return response.then()
                .extract()
                .as(CourseModel.class);
    }

    public UserModel loginViaAPI(String username, String password) {
        String jsonBody = String.format("""
                {
                    "taiKhoan": "%s",
                    "matKhau": "%s"
                }
                """, username, password);

        Response response = getBaseRequest()
                .body(jsonBody)
                .when()
                .post(LOGIN_ENDPOINT);

        response.then().statusCode(200);

        return response.then()
                .extract()
                .jsonPath()
                .getObject("", UserModel.class);
    }

    public UserModel getEnrolledCourseList(UserModel user) {
        String jsonBody = String.format("""
                {
                    "taiKhoan": "%s"
                }
                """, user.getTaiKhoan());

        Response response = getBaseRequest()
                .header("Authorization", "Bearer " + user.getAccessToken())
                .body(jsonBody)
                .when()
                .post(USER_INFOR_ENDPOINT);

        response.then().statusCode(200);

        List<UserModel.ChiTietKhoaHocGhiDanh> courseList = response.then()
                .extract()
                .jsonPath()
                .getList("chiTietKhoaHocGhiDanh", UserModel.ChiTietKhoaHocGhiDanh.class);

        user.setChiTietKhoaHocGhiDanh(courseList);
        return user;
    }

    public boolean isCourseEnrolled(UserModel user, String courseId) {
        if (user == null || getEnrolledCourseList(user) == null) {
            return false;
        }
        return user.getChiTietKhoaHocGhiDanh().stream()
                .anyMatch(course -> course.getMaKhoaHoc().trim().equalsIgnoreCase(courseId.trim()));
    }

    public void enrollCourse(UserModel user, String courseId) {
        if (!isCourseEnrolled(user, courseId))
        {
            String jsonBody = String.format("""
                {
                    "taiKhoan": "%s",
                    "maKhoaHoc": "%s"
                }
                """, user.getTaiKhoan(), courseId);

            Response response = getBaseRequest()
                    .header("Authorization", "Bearer " + user.getAccessToken())
                    .body(jsonBody)
                    .when()
                    .post(ENROLL_COURSE_ENDPOINT);

            response.then().statusCode(200);
        }
    }

    public void leaveCourse(UserModel user, String courseId) {
        if (isCourseEnrolled(user, courseId))
        {
            String jsonBody = String.format("""
                    {
                        "taiKhoan": "%s",
                        "maKhoaHoc": "%s"
                    }
                    """, user.getTaiKhoan(), courseId);

            Response response = getBaseRequest()
                    .header("Authorization", "Bearer " + user.getAccessToken())
                    .body(jsonBody)
                    .when()
                    .post(LEAVE_COURSE_ENDPOINT);

            response.then().statusCode(200);
        }
    }

}

