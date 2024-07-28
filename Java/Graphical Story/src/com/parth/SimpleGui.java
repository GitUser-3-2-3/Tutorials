package com.parth;

import javax.swing.*;

public class SimpleGui {
    static JFrame frame = new JFrame();
    static JButton button = new JButton("Button");

    public static void main(String[] args) {
        System.out.println();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(button);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public void changeIt() {
        button.setText("I've been clicked");
    }
}
