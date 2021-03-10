package graph;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

public class Dijkstra {
    static int N = 510;
    static int n, m;
    static int[] dist = new int[N];
    static boolean[] st = new boolean[N];
    static int[][] g = new int[N][N];
    public static void main(String[] a) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintStream out = new PrintStream(System.out);
        String[] line = in.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);

        for (int i = 1; i <= n; i++) {
            Arrays.fill(g[i], 0x3f3f3f3f);
        }

        while (m-- > 0) {
            String[] args = in.readLine().split(" ");
            int u = Integer.parseInt(args[0]);
            int v = Integer.parseInt(args[1]);
            int w = Integer.parseInt(args[2]);
            g[u][v] = Math.min(g[u][v], w);
        }
        int res = dijksrta();
        if (res == -1) {
            out.println(-1);
        } else {
            out.println(res);
        }
        out.close();
    }

    private static int dijksrta() {
        Arrays.fill(dist, 0x3f3f3f3f);
        dist[1] = 0;

        for (int i = 0; i < n; i++) {
            int t = -1;
            for (int j = 1; j <= n; j++) {
                if (!st[j] && (t == -1 || dist[j] < dist[t]))
                    t = j;
            }
            st[t] = true;

            for (int j = 1; j <= n; j++) {
                dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
            }
        }
        if (dist[n] == 0x3f3f3f3f)
            return -1;
        return dist[n];
    }
}
