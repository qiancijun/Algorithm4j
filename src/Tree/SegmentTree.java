package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/3/12 16:43
 */

public class SegmentTree {
    static final int N = 100010;
    static int n, m;
    static int[] a = new int[N];
    static Node[] tr = new Node[N * 4];

    private static class Node {
        int l, r, sum;
        public Node(int l, int r, int sum) {
            this.l = l;
            this.r = r;
            this.sum = sum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        s = in.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(s[i - 1]);
        }
        build(1, 1, n);

        while (m-- > 0) {
            s = in.readLine().split(" ");
            int k = Integer.parseInt(s[0]);
            int a = Integer.parseInt(s[1]);
            int b = Integer.parseInt(s[2]);
            if (k == 0) {
                out.println(query(1, a, b));
            } else {
                modify(1, a, b);
            }
        }
        in.close();
        out.close();
    }

    // 向上归并的操作
    public static void pushup(int r) {
        tr[r].sum = tr[r << 1].sum + tr[r << 1 | 1].sum;
    }

    // 构建线段树
    private static void build(int u, int l, int r) {
        if (l == r) tr[u] = new Node(l, r, a[l]);
        else {
            tr[u] = new Node(l, r, 0);
            int mid = (l + r) >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
            pushup(u);
        }
    }

    private static int query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) return tr[u].sum;
        int mid = (tr[u].l + tr[u].r) >> 1;
        int sum = 0;
        if (l <= mid) sum = query(u << 1, l, r);
        if (mid < r) sum += query(u << 1 | 1, l, r);
        return sum;
    }

    private static void modify(int u, int x, int v) {
        if (tr[u].l == tr[u].r) tr[u].sum += v;
        else {
            int mid = (tr[u].l + tr[u].r) >> 1;
            if (x <= mid) modify(u << 1, x, v);
            else modify(u << 1 | 1, x, v);
            pushup(u);
        }
    }

}
