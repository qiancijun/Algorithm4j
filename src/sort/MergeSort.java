package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/11 8:06
 */

public class MergeSort {
    static final int N = 100010;
    static int n;
    static int[] nums = new int[N];
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(System.out);
        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        s = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }

        int[] tmp = new int[N];
        mergeSort(0, n - 1, tmp);
        for (int i = 0; i < n; i++) {
            out.print(nums[i] + " ");
        }
        out.close();
        in.close();
    }

    private static void mergeSort(int l, int r, int[] tmp) {
        if (l >= r) return;
        int mid = (l + r) >> 1;
        mergeSort(l, mid, tmp);
        mergeSort(mid + 1, r, tmp);
        int k = 0, i = l, j = mid + 1;
        while (i <= mid && j <= r) {
            if (nums[i] < nums[j]) tmp[k++] = nums[i++];
            else tmp[k++] = nums[j++];
        }
        while (i <= mid) tmp[k++] = nums[i++];
        while (j <= r) tmp[k++] = nums[j++];
        for (int x = l, y = 0; x <= r; x++, y++) {
            nums[x] = tmp[y];
        }
    }
}
