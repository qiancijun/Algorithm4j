package Test;

import java.io.*;
import java.util.Arrays;

/**
 * @author Xu Chun
 * @Desc 草稿纸
 * @Time 2021/3/13 20:09
 */

public class Test {
    public static void main(String[] args) throws Exception {
        copy("src\\words.txt", "src\\cp.txt");
    }

    private static void copy(String src, String des) throws Exception {
        File s = new File(src);
        File d = new File(des);

        FileInputStream in = new FileInputStream(s);
        FileOutputStream out = new FileOutputStream(d);
        byte[] buf = new byte[1024];

        int len = -1;
        while ((len = in.read(buf)) != -1) {
            out.write(new String(buf, 0, len).getBytes("GBK"));
        }
    }

    private static void showFiles(File file) {
        if (!file.exists() || !file.isDirectory()) {
            System.out.println("文件不存在或者不是文件夹");
        }
        System.out.println("\n" + file.getAbsolutePath() + "\\" + file.getName() + " 文件夹下");
        File[] files = file.listFiles();
        for (File f : files){
            if (f.isDirectory()) {
                showFiles(f);
            } else if (f.getName().endsWith(".java")) {
                System.out.println(f.getAbsolutePath() + "\\" + f.getName());
            }
        }
    }
}
