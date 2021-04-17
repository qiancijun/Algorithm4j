package problem.acwing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Xu Chun
 * @Desc Acwing 786. 第k个数
 * @Time 2021/4/17 17:54
 */

public class Aw768 {

    static final int N = 100010;
    static int n, m;
    static int[] a = new int[N];

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        s = in.readLine().split(" ");
        for (int i = 0; i < n; i++ )
            a[i] = Integer.parseInt(s[i]);

        out.println(quick_sort(0, n - 1, m));

        in.close();
        out.close();
    }

    private static int quick_sort(int l, int r, int m) {
        if (l >= r) return a[l];
        int i = l - 1, j = r + 1, x = a[l];
        while (i < j) {
            while (a[++i] < x);
            while (a[--j] > x);
            if (i < j)
               swap(i, j);
        }
        if (j >= m - 1) return quick_sort(l, j, m);
        else return quick_sort(j + 1, r, m);
    }

    private static void swap(int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}
