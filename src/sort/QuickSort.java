package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class QuickSort {
    static final int N = 100000;
    static int[] nums = new int[N];
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(System.out);
        n = Integer.parseInt(in.readLine());
        String[] s = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }
        quickSort(nums, 0, n - 1);
        for (int i = 0; i < n; i++) {
            out.print(nums[i] + " ");
        }

    }

    private static void quickSort(int[] nums, int l, int r) {
        if (l >= r) return;
        int i = l - 1, j = r + 1;
        int x = nums[(l + r) >> 1];
        while (i < j) {
            while (nums[++i] < x);
            while (nums[--j] > x);
            if (i < j) swap(i, j);
        }
        quickSort(nums, l, j);
        quickSort(nums, j + 1, r);
    }

    private static void swap(int num, int num1) {
        int t = nums[num];
        nums[num] = nums[num1];
        nums[num1] = t;
    }
}
