package mathTheroy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Xu Chun
 * @Desc 试除法求所有约数
 * @Time 2021/4/17 20:39
 */

public class Divisors {
    static PrintWriter out;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        int T = Integer.parseInt(in.readLine());
        while (T -- > 0) {
            int n = Integer.parseInt(in.readLine());
            getDivisors(n);
        }
        in.close();
        out.close();
    }

    private static void getDivisors(int n) {
        List<Integer> ans = new ArrayList();
        for (int i = 1; i <= n / i; i++) {
            if (n % i == 0) {
                ans.add(i);
                if (i != n / i) ans.add(n / i);
            }
        }
        Collections.sort(ans);
        for (int a : ans) {
            out.print(a + " ");
        }
        out.println();
    }
}
