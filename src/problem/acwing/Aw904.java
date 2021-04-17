package problem.acwing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Xu Chun
 * @Desc Acwing 904. 虫洞
 * @Time 2021/4/17 10:07
 */

public class Aw904 {
    static final int N = 510, M = 5210;
    static int[] he = new int[N];
    static int[] e = new int[M];
    static int[] ne = new int[M];
    static int[] w = new int[M];
    static int[] dist = new int[N];
    static int[] cnt = new int[N];
    static boolean[] st = new boolean[N];
    static int idx = 0;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String[] s = in.readLine().split(" ");
        int T = Integer.parseInt(s[0]);
        while (T -- > 0) {
            int m, w;
            Arrays.fill(he, -1);
            idx = 0;
            s = in.readLine().split(" ");
            n = Integer.parseInt(s[0]);
            m = Integer.parseInt(s[1]);
            w = Integer.parseInt(s[2]);
            while (m -- > 0) {
                int a, b, c;
                s = in.readLine().split(" ");
                a = Integer.parseInt(s[0]);
                b = Integer.parseInt(s[1]);
                c = Integer.parseInt(s[2]);
                add(a, b, c);
                add(b, a, c);
            }
            while (w -- > 0) {
                int a, b, c;
                s = in.readLine().split(" ");
                a = Integer.parseInt(s[0]);
                b = Integer.parseInt(s[1]);
                c = Integer.parseInt(s[2]);
                add(a, b, -c);
            }
            if (spfa()) out.println("Yes");
            else out.println("No");
        }

        in.close();
        out.close();
    }

    private static void add(int a, int b, int c) {
        w[idx] = c;
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx ++;
    }

    private static boolean spfa() {
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(st, false);
        Arrays.fill(cnt, 0);
        for (int i = 1; i <= n; i++ ) {
            q.add(i);
            st[i] = true;
        }
        while (q.isEmpty()) {
            int t = q.poll();
            st[t] = false;

            for (int i = t; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[t] + w[i]) {
                    dist[j] = dist[t] + w[i];
                    cnt[j] = cnt[t] + 1;
                    if (cnt[j] >= n) return true;
                    if (!st[j]) {
                        st[j] = true;
                        q.add(j);
                    }
                }
            }
        }
        return false;
    }
}
