package design.petterns.singleton.demo6;

import java.io.InputStream;

public class Client {
    public static void main(String[] args) throws Exception {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("ifconfig");

        InputStream is = process.getInputStream();

        byte[] arrs = new byte[ 1024 * 1024 * 100];

        int len = is.read(arrs);

        System.out.println(new String(arrs, 0, len, "GBK"));


    }
}
