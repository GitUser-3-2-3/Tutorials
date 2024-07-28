package com.parth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class SecondSimpleGui implements ActionListener {
    private JButton button;

    public static void main(String[] args) {
        SecondSimpleGui simpleGui = new SecondSimpleGui();
        simpleGui.go();
    }

    public void go() {
        JFrame frame = new JFrame();

        button = new JButton("Button");
        button.addActionListener(this);

        MyDrawPanel drawPanel = new MyDrawPanel();

        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        frame.setSize(900, 500);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button.setText("Button Clicked!");
    }
}

class MyDrawPanel extends JPanel {
    @Override
    public void paintComponents(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(20, 50, 100, 100);
    }
}
