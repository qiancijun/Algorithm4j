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
 * @Desc Acwing1129 热浪
 * 问题的关键在于建图，问题的本质是单源最短路，建完图跑一边单源最短路即可
 * @Time 2021/3/14 19:13
 */

public class Aw1129 {
    static final int N = 2510, M = 2 * 6200 + 10, INF = 0x3f3f3f3f;
    static int n, m, S, T, idx = 0;
    static int[] he, e, ne, w, dist;
    static boolean[] st;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        he = new int[N];
        e = new int[M];
        ne = new int[M];
        w = new int[M];
        dist = new int[N];
        st = new boolean[N];

        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        S = Integer.parseInt(s[2]);
        T = Integer.parseInt(s[3]);

        Arrays.fill(he, -1);

        while (m-- > 0) {
            s = in.readLine().split(" ");
            int u =Integer.parseInt(s[0]);
            int v =Integer.parseInt(s[1]);
            int w =Integer.parseInt(s[2]);
            add(u, v, w);
            add(v, u, w);
        }

        spfa();
        out.println(dist[T]);
        in.close();
        out.close();
    }

    private static void spfa() {
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(dist, INF);
        dist[S] = 0;
        st[S] = true;
        q.add(S);
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
    }

    private static void add(int a, int b, int c) {
        w[idx] = c;
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx ++;
    }

}
