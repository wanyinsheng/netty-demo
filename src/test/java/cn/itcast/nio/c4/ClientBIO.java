package cn.itcast.nio.c4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 阻塞客户端
 * @Author wanys
 * @Date 2023/1/6 08:46
 * @Version 1.0
 **/
public class ClientBIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8081));
        System.out.println("waiting...");
        AtomicInteger atomicInteger = new AtomicInteger();
        while(true){
            sc.write(Charset.defaultCharset().encode(atomicInteger.getAndIncrement()+""));
            Thread.sleep(5000);
        }
    }
}
