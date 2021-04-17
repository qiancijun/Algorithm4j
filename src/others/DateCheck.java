package others;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/4/17 17:45
 */

public class DateCheck {

    static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) {
        System.out.println(checkValid(2001, 2, 29));
    }

    private static boolean checkValid(int year, int month, int day) {
        if (month == 0 || month > 12) return false;
        if (day == 0) return false;
        if (month != 2) {
            if (day > days[month]) return false;
        } else {
            int leap = year % 100 == 0 && year % 4 == 0 || year % 400 == 0 ? 1 : 0;
            if (day > 28 + leap) return false;
        }
        return true;
    }
}
