package cn.itcast.nio.c2;

import java.nio.ByteBuffer;

public class  TestByteBufferAllocate {
    public static void main(String[] args) {

        System.out.println(ByteBuffer.allocate(16).getClass());//分配堆内存
        System.out.println(ByteBuffer.allocateDirect(16).getClass());//分配直接内存

        /*
        class java.nio.HeapByteBuffer    - java 堆内存，读写效率较低，受到 GC 的影响
        class java.nio.DirectByteBuffer  - 直接内存，读写效率高（少一次拷贝），不会受 GC 影响，分配的效率低，用完要合理释放，不然会造成内存泄露
         */
        /**
         * 运行结果表明，直接内存在单次分配、写内存、读内存都要明显优于堆内存，
         * 但是在我们频繁分配内存时，堆内存明显优于直接内存。
         * 而且随着分配频率的增加堆内存的优势越明显。
         * 元空间主要存储加载的类信息，这些数据只会在程序启动时分配内存，运行期一般不会频繁加载新的类，故运行期不需要频繁分配内存。元空间在程序启动时直接分配足够的直接内存，可以减少程序的启动时间，同时不会对运行时产生影响。堆内存主要存放的是运行时对象，需要频率的创建与销毁。
         *  这就是jdk1.8元空间在直接内存的原因
         */
    }
}
