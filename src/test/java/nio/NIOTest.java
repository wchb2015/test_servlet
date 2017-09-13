package nio;


import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIOTest {


    @Test
    public void client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        FileChannel inChannel = FileChannel.open(Paths.get("/Users/wchb/Desktop/50000.jpg"), StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (inChannel.read(buffer) != -1) {
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        inChannel.close();
        socketChannel.close();

    }

    @Test
    public void server() throws IOException {
        //1.获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        //2. 绑定连接
        ssChannel.bind(new InetSocketAddress(9898));

        //3.获取客户端连接的通道
        SocketChannel socketChannel = ssChannel.accept();

        //4.分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //5. 接收客户端的数据,并保存到本地
        while (socketChannel.read(buffer) != -1) {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        //6.关闭通道
        socketChannel.close();
        outChannel.close();
        ssChannel.close();

    }

}
