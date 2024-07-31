package com.parth.connection;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;

import static java.awt.BorderLayout.CENTER;
import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SimpleChatClientOne {
    private JTextField outgoing;
    private PrintWriter writer;

    public void go() {
        setUpNetworking();
        outgoing = new JTextField(20);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> sendMessage());

        JPanel mainPanel = new JPanel();
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);

        JFrame frame = new JFrame("Simple Chat Client");
        frame.getContentPane().add(CENTER, mainPanel);
        frame.setSize(400, 100);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setUpNetworking() {
        try {
            InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 5005);
            SocketChannel socketChannel = SocketChannel.open(socketAddress);

            writer = new PrintWriter(Channels.newWriter(socketChannel, UTF_8));
            System.out.println("Networking Established");
        } catch (IOException ioException) {
            System.out.println("Exception: " + ioException);
        }
    }

    private void sendMessage() {
        writer.println(outgoing.getText());
        writer.flush();
        outgoing.setText("");
        outgoing.requestFocus();
    }

    public static void main(String[] args) {
        new SimpleChatClientOne().go();
    }
}
