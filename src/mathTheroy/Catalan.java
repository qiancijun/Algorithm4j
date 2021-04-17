package mathTheroy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * @author Xu Chun
 * @Desc
 * @Time 2021/4/17 21:02
 */

public class Catalan {
    static long mod = 1000000007;
    static final int N = 200010;
    static long[] fact = new long[N];
    static long[] infact = new long[N];
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        init();
        out.println(catalan(n));
        in.close();
        out.close();
    }

    private static long qmi(long n, long m) {
        long ans = 1 % mod, t = n;
        while (m != 0) {
            if ((m & 1) == 1) {
                ans = ans * t % mod;
            }
            t = t * t % mod;
            m >>= 1;
        }
        return ans;
    }

    private static void init() {
        fact[0] = infact[0] = 1;
        for (int i = 1; i < N ; i++) {
            fact[i] = fact[i - 1] * i % mod;
            infact[i] = infact[i - 1] * qmi(i, mod - 2) % mod;
        }
    }

    public static long catalan(int n) {
        long ans = fact[2 * n] * infact[n] % mod * infact[n] % mod * qmi(n + 1, mod - 2) % mod;
        return ans;
    }
}
