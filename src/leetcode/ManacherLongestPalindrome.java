package leetcode;

public class ManacherLongestPalindrome {

    public static String find(String s) {
        if(s == null || s.length() == 0 || s.length() == 1) return s;

        char[] arr = new char [s.length() * 2 + 1];

        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            arr[j] = '#';
            arr[j+1] = s.charAt(i);
            j += 2;
        }
        arr[j] = '#';

        int max = Integer.MIN_VALUE, maxIndex = 0;
        int right = -1, index = -1;
        int[] count = new int[arr.length];
        for (int i = 0; i != arr.length; i++) {
            count[i] = right > i ? Math.min(count[index - (i - index)], right - i) : 1;
            while (i + count[i] < arr.length && i - count[i] >= 0) {
                if (arr[i + count[i]] == arr[i - count[i]]) count[i]++;
                else break;
            }
            if (i + count[i] > right) {
                right = i + count[i];
                index = i;
            }
            if (count[i] > max) {
                max = count[i];
                maxIndex = i;
            }
        }

        if (maxIndex % 2 == 0)
            return s.substring(maxIndex / 2 - (max - 1) / 2, maxIndex / 2 + (max - 1) / 2);
        return s.substring(maxIndex / 2 - (max - 1) / 2, maxIndex / 2 + (max - 1) / 2 + 1);
    }
}
