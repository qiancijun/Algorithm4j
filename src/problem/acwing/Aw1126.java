package problem.acwing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Xu Chun
 * @Desc Acwing 1126 最小费用
 * @Time 2021/3/16 21:08
 */

public class Aw1126 {
    static final int N = 2010;
    static int n, m, S, T;
    static double[][] g = new double[N][N];
    static double[] dist = new double[N];
    static boolean[] st = new boolean[N];
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        while (m-- > 0) {
            s = in.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);
            double z = (100.0 - w) / 100;
            g[u][v] = g[v][u] = Math.max(g[u][v], z);
        }
        s = in.readLine().split(" ");
        S = Integer.parseInt(s[0]);
        T = Integer.parseInt(s[1]);

        out.printf("%.8f", 100 / dijkstra());

        out.close();
        in.close();
    }

    private static double dijkstra() {
        dist[S] = 1;
        for (int i = 0; i < n; i++) {
            int t = -1;
            for (int j = 1; j <= n; j++) {
                if (!st[j] && (t == -1 || dist[t] < dist[j])) {
                    t = j;
                }
            }
            st[t] = true;
            for (int j = 1; j <= n; j++) {
                dist[j] = Math.max(dist[j], dist[t] * g[t][j]);
            }
        }
        return dist[T];
    }

}
