package com.angel.uni.management.menu.gui;

import javax.swing.*;
import java.awt.*;

public class InitialPage extends Page {

   private final JPanel cardPanel;
   private final CardLayout cardLayout;

   public InitialPage() {
      cardLayout = new CardLayout();
      cardPanel = new JPanel(cardLayout);

      JPanel initialPage = createInitialPagePanel();
      cardPanel.add(initialPage, "INITIAL_MENU");

      JFrame frame = new JFrame();
      frame.add(cardPanel, BorderLayout.CENTER);

      frameProperties(frame);
   }

   private JPanel createInitialPagePanel() {
      JPanel panel = new JPanel();
      panelProperties(panel);

      JButton searchMenuButton = new JButton("Search menu");
      JButton updateMenuButton = new JButton("Update menu");
      JButton createMenuButton = new JButton("Create menu");
      JButton deleteMenuButton = new JButton("Delete menu");

      updateMenuButton.addActionListener(e -> {
         UpdatePage updatePage = new UpdatePage(cardPanel, cardLayout);
         JPanel updatePanel = updatePage.createUpdatePanel();
         cardPanel.add(updatePanel, "UPDATE_MENU");
         cardLayout.show(cardPanel, "UPDATE_MENU");
      });


      panel.add(searchMenuButton);
      panel.add(updateMenuButton);
      panel.add(createMenuButton);
      panel.add(deleteMenuButton);

      return panel;
   }
}
