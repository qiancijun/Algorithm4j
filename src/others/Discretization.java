package others;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/12 19:43
 */

public class Discretization {
    static final int N = 300010;
    static int n, m;
    static int[] sum = new int[N];
    static int[] a = new int[N];
    static List<Integer> alls = new ArrayList<>();
    static List<Pair> add = new ArrayList<>();
    static List<Pair> query = new ArrayList<>();
    private static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        for (int i = 0; i < n; i++) {
            s = in.readLine().split(" ");
            int x = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            add.add(new Pair(x, v));
            alls.add(x);
        }
        for (int i = 0; i < m; i++) {
            s = in.readLine().split(" ");
            int l = Integer.parseInt(s[0]);
            int r = Integer.parseInt(s[1]);
            query.add(new Pair(l, r));
            alls.add(l);
            alls.add(r);
        }
        alls = alls.stream().distinct().collect(Collectors.toList());
        Collections.sort(alls);
        for (Pair item : add) {
            int x = find(item.x);
            a[x] += item.y;
        }

        for (int i = 1; i <= alls.size(); i++) {
            sum[i] = sum[i - 1] + a[i];
        }
        for (Pair item : query) {
            int l = find(item.x), r = find(item.y);
            int res = sum[r] - sum[l - 1];
            out.println(res);
        }
        in.close();
        out.close();
    }

    private static int find(int x) {
        int l = 0, r = alls.size() - 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (alls.get(mid) >= x) r = mid;
            else l = mid + 1;
        }
        return r + 1;
    }
}
