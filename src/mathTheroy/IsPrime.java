package mathTheroy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Xu Chun
 * @Desc 试除法判定质数
 * @Time 2021/4/17 20:23
 */

public class IsPrime {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int T = Integer.parseInt(in.readLine());
        while (T -- > 0) {
            int n = Integer.parseInt(in.readLine());
            if (isPrime(n)) {
                out.println("Yes");
            } else {
                out.println("No");
            }
        }
        in.close();
        out.close();
    }

    private static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
