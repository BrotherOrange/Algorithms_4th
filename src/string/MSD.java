package string;

public class MSD {

    private static int R = 256;  // 基数
    private static final int M = 15;  // 小数组的切换阈值
    private static String[] aux;  // 数据分类的辅助数组

    private static int charAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo + M) {
            insertion(a, lo, hi, d);
            return;
        }

        int[] count = new int[R+2];  // 计算频率
        for (int i = lo; i <= hi; i++)
            count[charAt(a[i], d) + 2]++;

        for (int r = 0; r < R + 1; r++)  // 将频率转化为索引
            count[r+1] += count[r];

        for (int i = lo; i <= hi; i++)  // 数据分类
            aux[count[charAt(a[i], d) + 1]++] = a[i];

        for (int i = lo; i <= hi; i++)  // 回写
            a[i] = aux[i - lo];

        for (int r = 0; r < R; r++)
            sort(a, lo + count[r], lo + count[r+1] - 1, d + 1);
    }

    // insertion sort a[lo..hi], starting at dth character
    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                exchange(a, j, j-1);
    }

    // exchange a[i] and a[j]
    private static void exchange(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // is v less than w, starting at character d
    private static boolean less(String v, String w, int d) {
        // assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }
}
