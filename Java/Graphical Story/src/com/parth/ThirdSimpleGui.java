package com.parth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class ThirdSimpleGui implements ActionListener {
    private JFrame frame;

    public static void main(String[] args) {
        ThirdSimpleGui simpleGui = new ThirdSimpleGui();
        simpleGui.go();
    }

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton button = new JButton("Change Colors");
        button.addActionListener(this);

        MyDrawPanel2 drawPanel = new MyDrawPanel2();

        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.setSize(700, 300);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.repaint();
    }
}

class MyDrawPanel2 extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        Random random = new Random();

        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        Color startColor = new Color(red, green, blue);

        red = random.nextInt(256);
        green = random.nextInt(256);
        blue = random.nextInt(256);

        Color endColor = new Color(red, green, blue);

        GradientPaint gradient = new GradientPaint(70, 70, startColor, 150, 150, endColor);
        graphics2D.setPaint(gradient);
        graphics2D.fillOval(300, 70, 100, 100);
    }
}
