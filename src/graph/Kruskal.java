package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

public class Kruskal {
    static int N = 200010;
    static int n, m;
    static int[] parents = new int[N];
    static Edge[] edges;
    private static class Edge implements Comparable<Edge> {
        int u, v, w;

        @Override
        public int compareTo(Edge o) {
            return w - o.w;
        }

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(System.out);
        String[] s = in.readLine().split(" ");
        // 数据读入
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        edges = new Edge[m];
        // 初始化并查集
        for (int i = 0; i <= n; i++)  parents[i] = i;

        // 数据读入
        for (int i = 0; i < m; i++) {
            s = in.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);
            edges[i] = new Edge(u, v, w);
        }

        // 排序
        Arrays.sort(edges);

        int res = kruskal();
        if (res == -1) {
            out.println("impossible");
        } else {
            out.println(res);
        }
        out.close();
        in.close();
    }

    // 贪心的从集合中选取 n - 1 条边
    private static int kruskal() {
        int ans = 0, cnt = 0;
        for (int i = 0; i < m; i++) {
            int a = edges[i].u, b = edges[i].v, c = edges[i].w;
            a = find(a);
            b = find(b);
            if (a != b) {
                parents[a] = b;
                cnt ++;
                ans += c;
            }
        }
        if (cnt < n - 1) return -1;
        return ans;
    }

    // 并查集的Union-Find
    private static int find(int x) {
        while (x != parents[x]) {
            parents[x] = parents[parents[x]]; // 隔代压缩
            x = parents[x];
        }
        return x;
    }
}
