package cn.itcast.nio.c2;

import java.nio.ByteBuffer;

import static cn.itcast.nio.c2.ByteBufferUtil.debugAll;

public class TestByteBufferExam {
    public static void main(String[] args) {
         /*
         网络上有多条数据发送给服务端，数据之间使用 \n 进行分隔
         但由于某种原因这些数据在接收时，被进行了重新组合，例如原始数据有3条为
             Hello,world\n
             I'm zhangsan\n
             How are you?\n
         变成了下面的两个 byteBuffer (黏包，半包)
             Hello,world\nI'm zhangsan\nHo
             w are you?\n
         现在要求你编写程序，将错乱的数据恢复成原始的按 \n 分隔的数据
         */
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(source);
        source.put("w are you?\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            // 找到一条完整消息
            if (source.get(i) == '\n') {
                int length = i + 1 - source.position();//source.position()指向的是下一次文件读取位置
                // 把这条完整消息存入新的 ByteBuffer
                ByteBuffer target = ByteBuffer.allocate(length);
                // 从 source 读，向 target 写
                for (int j = 0; j < length; j++) {
                    target.put(source.get());
                }
                debugAll(target);
            }
        }
        source.compact();
    }

    /**
     * 另一种写法
     * @param source
     */
    private static void split1(ByteBuffer source) {
        source.flip();
        int oldLimit = source.limit();
        for (int i = 0; i < oldLimit; i++) {
            if (source.get(i) == '\n') {
                System.out.println(i);
                ByteBuffer target = ByteBuffer.allocate(i + 1 - source.position());
                // 0 ~ limit
                source.limit(i + 1);  //修改buffer的limit（只允许复制i+1以前的数据）
                target.put(source); // 从source 读，向 target 写
                debugAll(target);
                source.limit(oldLimit);//重置回原来的limit
            }
        }
        source.compact();//将没读完的进行压缩
    }
}
