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
 * @Desc SPFA判断图中是否存在负环
 * @Time 2021/3/13 17:21
 */

public class SPFAJuggNegativeRing {
    static final int N = 10010, INF = 0x3f3f3f3f;
    static int n, m;
    static int[] he = new int[N];
    static int[] e  = new int[N];
    static int[] ne = new int[N];
    static int[] w  = new int[N];
    static int[] dist = new int[N];
    static int[] cnt = new int[N];
    static boolean[] st = new boolean[N];
    static int idx = 0;
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
        if (spfa()) {
            out.println("Yes");
        } else {
            out.println("No");
        }
        in.close();
        out.close();
    }

    private static boolean spfa() {
        Queue<Integer> q = new LinkedList<>();
        // 需要把所有点全部加入到队列中，如果只加起点，可能起点走不到负环就退出了
        for (int i = 1; i <= n; i++) {
            q.add(i);
            st[i] = true;
        }

        while (!q.isEmpty()) {
            int t = q.poll();
            st[t] = false;
            for (int i = he[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[t] + w[i]) {
                    dist[j] = dist[t] + w[i];
                    cnt[j] = cnt[t] + 1;

                    if (cnt[j] >= n) return true; // 抽屉原理判负环

                    if (!st[j]) {
                        q.add(j);
                        st[j] = true;
                    }
                }
            }
        }
        return false;
    }

    private static void add(int a, int b, int c) {
        w[idx] = c;
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx;
        idx++;
    }
}
