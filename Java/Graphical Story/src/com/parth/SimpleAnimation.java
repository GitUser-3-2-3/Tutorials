package com.parth;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class SimpleAnimation {
    private int xPos = 70;
    private int yPos = 70;

    public static void main(String[] args) {
        System.out.println();

        SimpleAnimation gui = new SimpleAnimation();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyDrawPanel3 drawPanel = new MyDrawPanel3();

        frame.getContentPane().add(drawPanel);
        frame.setSize(600, 300);
        frame.setVisible(true);

        for (int i = 0; i < 130; i++) {
            xPos++;
            yPos++;

            drawPanel.repaint();

            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (Exception e) {
                System.out.println("error: " + e);
            }
        }
    }

    class MyDrawPanel3 extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.RED);
            g.fillOval(xPos, yPos, 40, 40);
        }
    }
}
