package cn.itcast.netty.c1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author wanys
 * @Date 2023/1/5 21:47
 * @Version 1.0
 **/
@Slf4j
public class ChannelDemo1 {
    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("src/main/resources/data.txt", "rw")) {
           //获取FileChannel的两种方式
            //FileChannel channel1 = new FileInputStream("").getChannel();
            FileChannel channel = file.getChannel();
            //10个字节大小的ByteBuffer
            ByteBuffer buffer = ByteBuffer.allocate(10);// 划分内存
            do {
                // 向 buffer 写入  (从channel读取)
                int len = channel.read(buffer);
                log.debug("读到字节数：{}", len);
                //文件末尾标志
                if (len == -1) {
                    break;//结束循环
                }
                // 切换 buffer 读模式
                buffer.flip();
//                limit = position;缓冲区有多少，读限制就是多少
//                position = 0;//读指针重置到缓冲区头
//                mark = -1;
                while(buffer.hasRemaining()) {
                    log.debug("{}", (char)buffer.get());//或buffer获取并打印
                }
                // 切换 buffer 写模式的两种方式
                buffer.clear();//清空缓冲区   或者  buffer.compact();compact 方法，是把未读完的部分向前压缩，然后切换至写模式
//                position = 0;写指针归零
//                limit = capacity;写限制为缓冲区容量大小
//                mark = -1;

            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
