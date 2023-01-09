package cn.itcast.nio.c4;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Author wanys
 * @Date 2023/1/7 11:31
 * @Version 1.0
 **/
@Slf4j
public class AioDemo1 {
    public static void main(String[] args) throws IOException {
        try{
            AsynchronousFileChannel s =
                    AsynchronousFileChannel.open(
                            Paths.get("1.txt"), StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(2);
            log.debug("begin...");
            //CompletionHandler回调对象
            s.read(buffer, 0, null, new CompletionHandler<Integer, ByteBuffer>() {
                //两个回调方法
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    log.debug("read completed...{}", result);
                    buffer.flip();
                    //debug(buffer);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {

                    log.debug("read failed...");
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug("do other things...");
        //阻止主线程结束
        System.in.read();
    }
}
