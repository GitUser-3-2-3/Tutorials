package com.parth.connection;

import jdk.jfr.Percentage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Random;

public class DailyAdviceServer {
    private final String[] adviceList = {
        "Take smaller bites",
        "Go for loose clothing, they are more comfortable",
        "Shove! the truth in their faces",
        "Get a haircut that is comfy than stylish"
    };
    private final Random random = new Random();

    public void go() {
        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            serverChannel.bind(new InetSocketAddress(5005));

            while (serverChannel.isOpen()) {
                SocketChannel clientChannel = serverChannel.accept();
                PrintWriter writer = new PrintWriter(
                    Channels.newOutputStream(clientChannel)
                );

                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        } catch (IOException ioException) {
            System.out.println("Exception: " + ioException);
        }
    }

    private String getAdvice() {
        int nextAdvice = random.nextInt(adviceList.length);
        return adviceList[nextAdvice];
    }

    public static void main(String[] args) {
        new DailyAdviceServer().go();
    }
}
