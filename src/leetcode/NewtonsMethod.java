package leetcode;

public class NewtonsMethod {

    // 牛顿法求平方根
    // 公式：Xn+1 = (Xn + m/xn) / 2
    public static int sqrt(int m) {
        if (m < 2) return m;

        double x0 = m;
        double x1 = (x0 + m / x0) / 2.0;
        while (Math.abs(x0 - x1) >= 1) {
            x0 = x1;
            x1 = (x0 + m / x0) / 2.0;
        }
        return (int) x1;
    }
}
