package sortings;

public class HeapSort {

    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int k = N / 2; k >= 1; k--)
            sink(a, k);

        while (N > 1) {
            exchange(a, 1, N--);
            sink(a, 1);
        }
    }
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private static void exchange(Comparable[] pq, int i, int j) {
        Comparable t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private static void swim(Comparable[] pq, int k) {
        while (k > 1 && less(pq, k / 2, k)) {
            exchange(pq, k / 2, k);
            k = k / 2;
        }
    }

    private static void sink(Comparable[] pq, int k) {
        int N = pq.length;
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(pq, j, j + 1)) j++;
            if (!less(pq, k, j)) break;
            exchange(pq, k, j);
            k = j;
        }
    }
}
