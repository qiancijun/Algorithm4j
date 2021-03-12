package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Xu Chun
 * @Desc 有边数限制的最短路
 * @Time 2021/3/12 16:10
 */

public class BellmanFord {
    static final int N = 10010, INF = 0x3f3f3f3f;
    static int n, m, k;
    static int[] dist = new int[N];
    static int[] backup = new int[N];
    static Edge[] edges = new Edge[N];
    private static class Edge {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        k = Integer.parseInt(s[2]);
        for (int i = 0; i < m ; i ++) {
            s = in.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);
            edges[i] = new Edge(u, v, w);
        }
        int res = belllmenFord();
        if (res == -1) {
            out.println("impossible");
        } else {
            out.println(res);
        }
        in.close();
        out.close();
    }

    private static int belllmenFord() {
        // 初始化距离数组
        Arrays.fill(dist, INF);
        dist[1] = 0;
        // 循环 k 次，代表松弛图 k 次
        for (int i = 0; i < k; i++) {
            // 备份原有的图
            backup = Arrays.copyOf(dist, N);
            for (int j = 0; j < m; j++) {
                int a = edges[j].u;
                int b = edges[j].v;
                int c = edges[j].w;
                // 对每个可达点都进行一遍距离更新
                dist[b] = Math.min(dist[b], backup[a] + c);
            }
        }
        if (dist[n] > INF / 2) return -1;
        return dist[n];
    }
}
