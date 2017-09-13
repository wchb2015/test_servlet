package nio;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class ChannelTest {


    private static final Logger LOG = LoggerFactory.getLogger(ChannelTest.class);


    @Test
    public void testCharset() throws CharacterCodingException {
        Charset cs1 = Charset.forName("GBK");

        //获取编码器
        CharsetEncoder encoder = cs1.newEncoder();

        //获取解码器
        CharsetDecoder decoder = cs1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("中国人美国人");
        cBuf.flip();

        //编码
        ByteBuffer bBuf = encoder.encode(cBuf);

        for (int i = 0; i < 12; i++) {
            LOG.info("bBuf.get():{}", bBuf.get());
        }

        //解码
        bBuf.flip();
        CharBuffer cBuf2 = decoder.decode(bBuf);
        LOG.info("cBuf2.toString():{}", cBuf2.toString());
        LOG.info("------------------------------------------------------");

        Charset cs2 = Charset.forName("GBK");
        bBuf.flip();
        CharBuffer cBuf3 = cs2.decode(bBuf);
        LOG.info("cBuf3.toString():{}", cBuf3.toString());
    }

    /**
     * 利用Channel完成文件复制.
     * 1. 获取通道.
     * 2. 分配指定大小的缓冲区.
     * 3. 将通道的数据写入缓冲区.
     * 4. 将缓冲区中的数据写入通道.
     */
    @Test
    public void fileTransfer() throws IOException {
        FileInputStream fis = new FileInputStream("/Users/wchb/Desktop/welcome.js");
        FileOutputStream fos = new FileOutputStream("/Users/wchb/Desktop/2welcome.js");

        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);

        while (inChannel.read(buf) != -1) {
            buf.flip();//切换读取数据的模式
            outChannel.write(buf);
            buf.clear();
        }

        outChannel.close();
        inChannel.close();
        fis.close();
        fos.close();

    }
}
