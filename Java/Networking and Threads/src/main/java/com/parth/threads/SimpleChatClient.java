package com.parth.threads;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.awt.BorderLayout.CENTER;
import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.swing.ScrollPaneConstants.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SimpleChatClient {
    private JTextArea incoming;
    private JTextField outgoing;
    private BufferedReader reader;
    private PrintWriter writer;

    public void go() {
        setUpNetworking();

        JScrollPane scroller = createScrollableTextArea();
        outgoing = new JTextField(20);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> sendMessage());

        JPanel mainPanel = new JPanel();
        mainPanel.add(scroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new IncomingReader());

        JFrame frame = new JFrame("Simple Chat Client");
        frame.getContentPane().add(CENTER, mainPanel);
        frame.setSize(400, 350);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JScrollPane createScrollableTextArea() {
        incoming = new JTextArea(15, 30);

        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);

        JScrollPane scroller = new JScrollPane(incoming);
        scroller.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        return scroller;
    }

    private void setUpNetworking() {
        try {
            InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 5005);
            SocketChannel socketChannel = SocketChannel.open(serverAddress);

            reader = new BufferedReader(Channels.newReader(socketChannel, UTF_8));
            writer = new PrintWriter(Channels.newWriter(socketChannel, UTF_8));

            System.out.println("Networking established");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void sendMessage() {
        writer.println(outgoing.getText());
        writer.flush();

        outgoing.setText("");
        outgoing.requestFocus();
    }

    public class IncomingReader implements Runnable {
        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("reads " + message);
                    incoming.append(message + "\n");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new SimpleChatClient().go();
    }
}








