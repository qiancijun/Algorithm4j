package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Xu Chun
 * @Desc 堆优化版的 Dijkstra，使用数组模拟邻接表
 * @Time 2021/3/13 20:22
 */

public class Dijkstra3 {
    static final int N = 150010, INF = 0x3f3f3f3f;
    static int n, m;
    static int[] he = new int[N];
    static int[] e  = new int[N];
    static int[] ne = new int[N];
    static int[] w  = new int[N];
    static int[] dist = new int[N];
    static boolean[] st = new boolean[N];
    static int idx = 0;
    private static class Pair implements Comparable<Pair> {
        int x, y;
        public Pair(int x, int y) {this.x = x; this.y = y;}
        @Override
        public int compareTo(Pair o) {
            return y - o.y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(System.out);

        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        Arrays.fill(he, -1);
        while (m-- > 0) {
            s = in.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);
            add(u, v, w);
        }

        int res = dijkstra();
        if (res == -1) {
            out.println(-1);
        } else {
            out.println(res);
        }

        in.close();
        out.close();
    }

    private static int dijkstra() {
        Arrays.fill(dist, INF);
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        dist[1] = 0;
        pq.add(new Pair(1, 0));
        while (!pq.isEmpty()) {
            Pair t = pq.poll();
            int now = t.x, dis = t.y;
            if (st[now]) continue;
            st[now] = true;

            for (int i = he[now]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dis + w[i]) {
                    dist[j] = dis + w[i];
                    pq.add(new Pair(j, dist[j]));
                }
            }
        }

        if (dist[n] == INF) return -1;
        return dist[n];
    }

    private static void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = he[a];
        he[a] = idx;
        idx ++;
    }
}
