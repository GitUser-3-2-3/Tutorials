package com.parth.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;

import static java.nio.charset.StandardCharsets.UTF_8;

public class DailyAdviceClient {
    public void go() {
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 5005);

        try (SocketChannel socketChannel = SocketChannel.open(socketAddress)) {
            Reader reader = Channels.newReader(socketChannel, UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String advice = bufferedReader.readLine();
            System.out.println("\n" + "Today you should: " + advice);

            reader.close();
        } catch (IOException ioException) {
            System.out.println("ioException: " + ioException);
        }
    }

    public static void main(String[] args) {
        new DailyAdviceClient().go();
    }
}
