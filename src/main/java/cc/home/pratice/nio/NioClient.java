package cc.home.pratice.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author chengcheng
 */
public class NioClient {

    private static void start() throws IOException {
        final SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8090));
        if(socketChannel.isConnected()){
            ByteBuffer hello = ByteBuffer.wrap("hello".getBytes("utf-8"));
            socketChannel.write(hello);
            hello.compact();
            socketChannel.read(hello);
            while (hello.hasRemaining()){
                hello.flip();
                System.out.println(new String(hello.array(),"utf-8"));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        start();
    }
}
