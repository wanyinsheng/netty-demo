package cn.itcast.nio.c3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @Author wanys
 * @Date 2023/1/6 00:00
 * @Version 1.0
 **/
public class TestFileChannel {
    public static void main(String[] args) {
        String FROM = "helloword/data.txt";
        String TO = "helloword/to.txt";
        long start = System.nanoTime();
        try (FileChannel from = new FileInputStream(FROM).getChannel();
             FileChannel to = new FileOutputStream(TO).getChannel();
        ) {
            //transferTo使用了操作系统的零拷贝
            from.transferTo(0, from.size(), to);//最大传输2g
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println("transferTo 用时：" + (end - start) / 1000_000.0);
    }
}
