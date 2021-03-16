package problem.acwing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Xu Chun
 * @Desc Acwing 1128 信使
 * @Time 2021/3/14 19:38
 */

public class Aw1128 {
    static final int N = 110, M = 210, INF = 0x3f3f3f3f;
    static int[][] g = new int[N][N];
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        for (int i = 1; i <= n; i++) Arrays.fill(g[i], INF);

        while (m-- > 0) {
            s = in.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);
            g[u][v] = g[v][u] = Math.min(g[u][v], w);
        }

        floyd();

        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (g[1][i] == INF) {
                res = -1;
                break;
            }
            res = Math.max(res, g[1][i]);
        }
        out.println(res);
        in.close();
        out.close();
    }

    private static void floyd() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }
    }
}
