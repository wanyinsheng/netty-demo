package cn.itcast.nio.c2;


import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static cn.itcast.nio.c2.ByteBufferUtil.debugAll;

/**
 * 字符串与 ByteBuffer 互转
 */
public class TestByteBufferString {
    public static void main(String[] args) {
        // 1. 字符串转为 ByteBuffer
//        ByteBuffer buffer1 = ByteBuffer.allocate(16);
//        buffer1.put("hello".getBytes());//5个字节
//        buffer1.put("你好啊".getBytes());//9个字节
//        debugAll(buffer1);

        // 2. Charset   position为0 ，也就是会自动切换到读模式
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello你好啊");//汉子是三字节
        debugAll(buffer2);
//
        // 3. wrap   包装
//        ByteBuffer buffer3 = ByteBuffer.wrap("hello你好啊".getBytes());
//        debugAll(buffer3);
//
        // 4. 转为字符串
        String str1 = StandardCharsets.UTF_8.decode(buffer2).toString();
        System.out.println(str1);
//
//        buffer1.flip();//需要读模式才可以
//        String str2 = StandardCharsets.UTF_8.decode(buffer1).toString();
//        System.out.println(str2);

    }
}
