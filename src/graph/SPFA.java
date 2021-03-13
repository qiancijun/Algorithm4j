package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Xu Chun
 * @Desc SPFA算法
 * @Time 2021/3/12 21:42
 */

public class SPFA {
    static final int N = 100010, INF = 0x3f3f3f3f;
    static int n, m;
    static int[] he   = new int[N];
    static int[] e    = new int[N];
    static int[] ne   = new int[N];
    static int[] w    = new int[N];
    static int idx = 0;
    static int[] dist = new int[N];
    static boolean[] st = new boolean[N];
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(System.out);
        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        // 初始化邻接表的表头
        Arrays.fill(he, -1);
        while (m-- > 0) {
            s = in.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);
            add(u, v, w);
        }

        int res = spfa();
        if (res == -1) {
            out.println("impossible");
        } else {
            out.println(res);
        }

        in.close();
        out.close();
    }

    // 邻接表的存储
    private static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = he[a];
        he[a] = idx;
        idx ++;
    }

    private static int spfa() {
        Arrays.fill(dist, INF);
        dist[1] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        st[1] = true; // 当前点是不是在队列中，防止队列中存重复的点

        while (!q.isEmpty()) {
            int curr = q.poll();
            st[curr] = false;

            for (int i = he[curr]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[curr] + w[i]) {
                    dist[j] = dist[curr] + w[i];
                    if (!st[j]) {
                        q.add(j);
                        st[j] = true;
                    }
                }
            }
        }

        if (dist[n] == INF) return -1;
        return dist[n];
    }

}
