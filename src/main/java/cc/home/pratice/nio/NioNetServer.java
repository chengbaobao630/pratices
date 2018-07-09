package cc.home.pratice.nio;

import javax.xml.soap.SAAJResult;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author chengcheng
 */
public class NioNetServer {


    private static void start() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 8090);
        serverSocketChannel.bind(socketAddress);
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        for (;;) {
            if (selector.select() > 0) {
                Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
                while (keyIter.hasNext()) {
                    SelectionKey selectionKey = keyIter.next();
                    if (selectionKey.isAcceptable()) {
                        System.out.println("accept");
                        final SelectableChannel channel = selectionKey.channel();
                        final SocketChannel accept = ((ServerSocketChannel) channel).accept();
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    }else if (selectionKey.isReadable()) {
                        System.out.println("read");
                        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
                        final SocketChannel readChannel1 = (SocketChannel) selectionKey.channel();
                        while (readChannel1.read(byteBuffer) > 0) {
//                            byteBuffer.compact();
                            System.out.println(new String(byteBuffer.array(),"utf-8"));
                            byteBuffer.flip();
                        }
//                        readChannel.close();
                        selectionKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);

                    }else if (selectionKey.isValid() && selectionKey.isWritable()) {
                        System.out.println("write");
                        final SelectableChannel channel = selectionKey.channel();

                        StringBuilder sendStr = new StringBuilder();

                        sendStr.append("Http/1.1 200 Ok\r\n");

                        sendStr.append("Content-Type:application/json;charset=utf-8\r\n");

                        sendStr.append("\r\n");

                        sendStr.append("<html><head><title>显示报文</title></head><body>");

                        sendStr.append("接受到请求的报文是：+<br>");

                        sendStr.append("</body></html>");

                        final ByteBuffer wrap = ByteBuffer.wrap(sendStr.toString().getBytes("utf-8"));
                        ((SocketChannel)channel).write(wrap);
                        channel.close();
//                        selectionKey.interestOps(SelectionKey.OP_READ);
                    }
                    keyIter.remove();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        start();
    }
}
