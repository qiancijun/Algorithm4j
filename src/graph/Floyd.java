package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/12 16:21
 */

public class Floyd {
    static final int N = 210, INF = 0x3f3f3f3f;
    static int n, m, k;
    static int[] dist = new int[N];
    static int[][] g = new int[N][N];
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        k = Integer.parseInt(s[2]);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) g[i][j] = 0;
                else g[i][j] = INF;
            }
        }

        for (int i = 0; i < m; i++) {
            s = in.readLine().split(" ");
            int u = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            int w = Integer.parseInt(s[2]);
            g[u][v] = Math.min(g[u][v], w);
        }

        floyd();

        while (k-- > 0) {
            s = in.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            if (g[a][b] > INF / 2) out.println("impossible");
            else out.println(g[a][b]);
        }

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
