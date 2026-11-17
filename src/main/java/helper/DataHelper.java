package helper;

import java.util.Random;

public class DataHelper {
    public static String generateUnique(int length) {
        // Lấy milliseconds hiện tại làm gốc (luôn luôn tăng theo thời gian, không bao giờ trùng)
        String timePart = String.valueOf(System.currentTimeMillis());

        // Nếu thời gian dài hơn yêu cầu, lấy phần đuôi của thời gian
        if (timePart.length() >= length) {
            return timePart.substring(timePart.length() - length);
        }

        // Nếu thời gian ngắn hơn yêu cầu, bù thêm các số ngẫu nhiên cho đủ độ dài
        int remainingLength = length - timePart.length();
        Random random = new Random();
        StringBuilder sb = new StringBuilder(timePart);

        for (int i = 0; i < remainingLength; i++) {
            sb.append(random.nextInt(10)); // Thêm số ngẫu nhiên từ 0-9
        }

        return sb.toString();
    }

    public static String generateAccount(int length) {
        String prefix = PropertyHelper.get("register.account.prefix");
        int remainingLength = length - prefix.length() ;
        return prefix + generateUnique(remainingLength);
    }

    public static String generatePhoneNumber(int length) {
        String prefix = PropertyHelper.get("register.phone.prefix");
        int remainingLength = length - prefix.length() ;
        return prefix + generateUnique(remainingLength);
    }

    // Hàm tiện ích sinh nhanh bộ dữ liệu chuẩn cho các test case
    public static String createRandomAccount() {
        return generateAccount(PropertyHelper.getIntProperty("register.account.maxLenght"));
    }

    public static String createRandomPhone() {
        return generatePhoneNumber(PropertyHelper.getIntProperty("register.phone.maxLenght"));
    }

    public static String createRandomEmail(String acc) {
        return acc + PropertyHelper.get("register.domain");
    }
}
