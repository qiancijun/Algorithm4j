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
 * @Desc Acwing 1127 香甜的黄油
 * @Time 2021/3/16 20:53
 */

public class Aw1127 {
    static final int N = 510, P = 810, C = 2 * 1450 + 10, INF = 0x3f3f3f3f;
    static int n, p, c, idx = 0;
    static int[] id, he, e, ne, w, dist;
    static boolean[] st = new boolean[P];
    public static void main(String[] args) throws IOException {
        id = new int[N];
        he = new int[P];
        e = new int[C];
        ne = new int[C];
        w = new int[C];
        dist = new int[P];

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        p = Integer.parseInt(s[1]);
        c = Integer.parseInt(s[2]);

        for (int i = 0; i < n; i++) id[i] = Integer.parseInt(in.readLine());

        Arrays.fill(he, -1);
        while (c-- > 0) {
            s = in.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);
            add(u, v, w);
            add(v, u, w);
        }

        int res = INF;
        for (int i = 1; i <= p; i++) {
            res = Math.min(res, spfa(i));
        }
        out.println(res);
        in.close();
        out.close();
    }

    private static void add(int a, int b, int c) {
        w[idx] = c;
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx++;
    }

    private static int spfa(int s) {
        Arrays.fill(dist, INF);
        dist[s] = 0;
        st[s] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        while (!q.isEmpty()) {
            int t = q.poll();
            st[t] = false;

            for (int i = he[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[t] + w[i]) {
                    dist[j] = dist[t] + w[i];
                    if (!st[j]) {
                        st[j] = true;
                        q.add(j);
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            int j = id[i];
            if (dist[j] == INF) return INF;
            res += dist[j];
        }
        return res;
    }

}
