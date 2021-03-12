package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/12 16:30
 */

public class TreeArray {
    static final int N = 100010;
    static int n, m;
    static int[] tr = new int[N];
    static int[] a = new int[N];
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        s = in.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(s[i - 1]);
            add(i, a[i]);
        }

        while (m-- > 0) {
            s = in.readLine().split(" ");
            int k = Integer.parseInt(s[0]);
            int a = Integer.parseInt(s[1]);
            int b = Integer.parseInt(s[2]);
            if (k == 0)
                out.println(query(b) - query(a - 1));
            else if (k == 1)
                add(a, b);
        }

        in.close();
        out.close();
    }

    private static int lowbit(int x) {
        return x & (-x);
    }

    private static void add(int x, int v) {
        for (int i = x; i <= n; i += lowbit(i))
            tr[i] += v;
    }

    private static int query(int x) {
        int res = 0;
        for (int i = x; i > 0; i -= lowbit(i))
            res += tr[i];
        return res;
    }

}
