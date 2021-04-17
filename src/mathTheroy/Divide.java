package mathTheroy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author Xu Chun
 * @Desc 分解质因数
 * @Time 2021/4/17 20:34
 */

public class Divide {
    static PrintWriter out;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);

        int T = Integer.parseInt(in.readLine());
        while (T -- > 0) {
            int n = Integer.parseInt(in.readLine());
            divide(n);
            out.println();
        }

        in.close();
        out.close();
    }

    private static void divide(int n) {
        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                int s = 0;
                while (n % i == 0) {
                    n /= i;
                    s++;
                }
                out.println(i + " " + s);
            }
        }
        if (n > 1) out.println(n + " " + 1);
    }
}
