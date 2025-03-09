package com.angel.uni.management.menu.gui;

import javax.swing.*;
import java.awt.*;

public abstract class Page {

    private static final int TOP_DIMENSIONS = 50;
    private static final int BOTTOM_DIMENSIONS = 50;
    private static final int LEFT_DIMENSIONS = 50;
    private static final int RIGHT_DIMENSIONS = 50;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static final int COLUMNS = 1;
    private static final int ROWS = 4;

    protected void frameProperties(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Menu Management System");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    protected void panelProperties(JPanel panel) {
        panel.setBorder(BorderFactory.createEmptyBorder(TOP_DIMENSIONS, LEFT_DIMENSIONS, BOTTOM_DIMENSIONS, RIGHT_DIMENSIONS));
        panel.setLayout(new GridLayout(ROWS, COLUMNS));
    }
}
