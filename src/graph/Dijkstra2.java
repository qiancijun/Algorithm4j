package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra2 {
    static int N = 100010;

    static int n, m;
    static final int INF = 0x3f3f3f3f;
    static int[] dist = new int[N];
    static boolean[] st = new boolean[N];
    static List[] g = new List[N];

    private static class PII implements Comparable<PII> {
        int v, w;
        @Override
        public int compareTo(PII o) {
            return this.w - o.w;
        }

        public PII(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(System.out);

        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<PII>();
        }
        while (m-- > 0) {
            s = in.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);
            g[u].add(new PII(v, w));
        }

        int res = dijkstra();
        if (res == -1) {
            out.println(-1);
        } else {
            out.println(res);
        }
        out.close();
    }

    private static int dijkstra() {
        Arrays.fill(dist, INF);
        dist[1] = 0;
        PriorityQueue<PII> pq = new PriorityQueue<>();

        pq.add(new PII(1, 0));

        while (!pq.isEmpty()) {
            PII curr = pq.poll();
            int now = curr.v;
            int dis = curr.w;
            if (st[now]) continue;
            st[now] = true;

            for (int i = 0; i < g[now].size(); i++) {
                PII n = (PII) g[now].get(i);
                if (st[n.v]) continue;
                if (dist[n.v] > dis + n.w) {
                    dist[n.v] = dis + n.w;
                    pq.add(new PII(n.v, n.w + dis));
                }
            }
        }
        if (dist[n] == INF)
            return -1;
        return dist[n];
    }
}
