package com.angel.uni.management.menu.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePage extends Page {

    private final JPanel cardPanel;
    private final CardLayout cardLayout;

    public UpdatePage(JPanel cardPanel, CardLayout cardLayout) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
    }

    public JPanel createUpdatePanel() {
        JPanel panel = new JPanel();
        panelProperties(panel);
        JLabel label = new JLabel("Update Menu", SwingConstants.CENTER);
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "INITIAL_MENU"));
        panel.add(label);
        panel.add(backButton);
        return panel;
    }

    private void teacherPanel(JPanel panel) {
        panel.add(new JLabel("Teacher's id: "));
        JTextField idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Teacher's name: "));
        JTextField nameField = new JTextField();
        panel.add(nameField);

        JButton submitButton = new JButton("Submit");
        JButton resetButton = new JButton("Reset");
        panel.add(resetButton);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idField.setText("");
                nameField.setText("");
            }
        });
    }
}
