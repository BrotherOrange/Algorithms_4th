package sort;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSort extends AbstractSort {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }
    
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        // 改进: if (hi <= lo + M) { InsertionSort.sort(a, lo, hi); return; }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }
    
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, lo, i);
        return j;
    }

    // 三向切分
    private static void sort3way(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;  // 改进方法同上
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exchange(a, lt++, i++);
            else if (cmp > 0) exchange(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
}
