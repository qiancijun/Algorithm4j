package mathTheroy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Xu Chun
 * @Desc 线性筛法求素数
 * @Time 2021/4/17 20:28
 */

public class GetPrimes {
    static final int N = 1000010;
    static int[] prime = new int[N];
    static boolean[] st = new boolean[N];
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int n = Integer.parseInt(in.readLine());
        getPrime(n);
        out.println(cnt);

        in.close();
        out.close();
    }

    private static void getPrime(int x) {
        for (int i = 2; i <= x; i++) {
            if (!st[i]) prime[cnt++] = i;
            for (int j = 0; prime[j] <= x / i; j++) {
                st[prime[j] * i] = true;
                if (i % prime[j] == 0) break;
            }
        }
    }
}
