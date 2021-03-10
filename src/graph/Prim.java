package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

public class Prim {
    static int N = 510, M = 100010, INF = 0x3f3f3f3f;
    static int n, m;
    static int[] dist = new int[N]; // dist为其他点到s集合的距离
    static boolean[] st = new boolean[N];
    static int[][] g = new int[N][N];
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(System.out);
        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        for (int i = 1; i <= n; i++) {
            Arrays.fill(g[i], INF);
        }

        while (m-- > 0) {
            s = in.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);
            g[u][v] = g[v][u] = Math.min(g[u][v], w);
        }

        int res = prim();
        if (res == -1) {
            out.println("impossible");
        } else {
            out.println(res);
        }
        out.close();
        in.close();
    }

    private static int prim() {
        int ans = 0;
        Arrays.fill(dist, INF);
        for (int i = 0; i < n; i++) {
            // 寻找离集合S最近的点
            int t = -1;
            for (int j = 1; j <= n; j++) {
                if (!st[j] && (t == -1 || dist[j] < dist[t]))
                    t = j;
            }
            // 判断是否连通，有无最小生成树
            if (i != 0 && dist[t] == INF) return -1;
            // 更新S最新的权值和
            if (i != 0) ans += dist[t];
            st[t] = true;
            // 拿当前点去更新其他点到集合的最小距离
            for (int j = 1; j <= n; j++) {
                dist[j] = Math.min(dist[j], g[t][j]);
            }
        }
        return ans;
    }
}
