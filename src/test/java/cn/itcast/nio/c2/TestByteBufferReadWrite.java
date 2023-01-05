package cn.itcast.nio.c2;

import java.nio.ByteBuffer;

import static cn.itcast.nio.c2.ByteBufferUtil.debugAll;

public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        //向缓冲区放入数据
        buffer.put((byte) 0x61); // 'a'
        debugAll(buffer);
        buffer.put(new byte[]{0x62, 0x63, 0x64}); // b  c  d
        debugAll(buffer);
        System.out.println(buffer.get());//因为当前的position是4，获取不到东西
        buffer.flip();//切换到读模式
        System.out.println(buffer.get());//读取到缓冲区的第一个（这里输出的是十进制的，所以是97）
//        debugAll(buffer);
        buffer.compact();//将未读取的内容往前移
        debugAll(buffer);
        //这里没有切换写模式，所以中间有一个位置是空缺的
        buffer.put(new byte[]{0x65, 0x6f});
        debugAll(buffer);
    }
}
