package nio;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;

/**
 * Buffer 在 Java NIO中负责数据的存取 . 底层为数组,用于存储不通数据类型的数据.
 * 根据数据类型的不同(boolean 除外), 提供了相应类型的缓冲区
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * <p>
 * 上述缓冲区的管理方式几乎一致,通过allocate()获取缓冲区
 * <p>
 * 缓冲区存取数据的两个核心方法.
 * put() : 存入数据到缓冲区
 * get() : 获取数缓冲区的数据
 */
public class BufferTest {

    private static final Logger LOG = LoggerFactory.getLogger(BufferTest.class);

    @Test
    public void test() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        LOG.info("position :{}", buffer.position());
        LOG.info("limit :{}", buffer.limit());
        LOG.info("capacity :{}", buffer.capacity());

    }

}
