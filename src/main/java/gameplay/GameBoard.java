package gameplay;

import commands.*;
import environment.*;
import weapon.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame implements ActionListener {
  Environment environment = Environment.getEnvironment(10, 10);
  JPanel centerPanel, legendPanel, statsPanel;

  public GameBoard() {

  }

  public void updateCell(int row, int col) {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

  }
}

//package gameplay;
//
//import javax.swing.*;
//import javax.swing.border.Border;
//import javax.swing.border.EmptyBorder;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Graphics;
//import java.awt.GridLayout;
//import java.awt.Image;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//
//public class SampleGUI extends JFrame implements ActionListener {
//JButton imageButton;
//JLabel textLabel, legend;
//JRadioButton[][] grid;
//final static ImageIcon cell = new ImageIcon(
//    new ImageIcon("bin/cell.png").getImage().getScaledInstance(70, 70, DO_NOTHING_ON_CLOSE));
//
//public SampleGUI() {
//  setLayout(new BorderLayout());
//
//  textLabel = new JLabel("(-,-)");
//  textLabel.setHorizontalAlignment(SwingConstants.CENTER);
//  add("North", textLabel);
//
//  JPanel cellStats = new JPanel(new GridLayout(2, 3));
//  for (int i = 0; i < 6; i++)
//    cellStats.add(new JLabel("-", SwingConstants.CENTER));
//  add("South", cellStats);
//
//  legend = new JLabel("Legend will go here");
//  legend.setBorder(BorderFactory.createLineBorder(Color.black));
//  add("East", legend);
//
//  JPanel centerPanel = new JPanel(new GridLayout(10, 10));
//  grid = new JRadioButton[10][10];
//  for (int r = 0; r < 10; r++) {
//    for (int c = 0; c < 10; c++) {
//      grid[r][c] = new JRadioButton(cell);
//      grid[r][c].setSize(70, 70);
//      grid[r][c].addActionListener(this);
//      centerPanel.add(grid[r][c]);
//
//    }
//  }
//  add("Center", centerPanel);
//
//  setResizable(false);
//  pack();
//  setVisible(true);
//}
//
//public ImageIcon createImage() {
//  BufferedImage exampleImage = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
//  Graphics drawer = exampleImage.getGraphics();
//
//  drawer.setColor(new Color(200, 200, 200));
//  drawer.fillRect(0, 0, 50, 50);
//
//  drawer.setColor(new Color(0, 255, 0));
//  drawer.fillOval(20, 20, 10, 10);
//
//  return new ImageIcon(exampleImage);
//}
//
//public static void main(String[] args) {
//  new SampleGUI();
//}
//
//@Override
//public void actionPerformed(ActionEvent event) {
//  if (event.getSource() == legend) {
//    legend.setText("Pushed");
//  } else {
//    for (int row = 0; row < 10; row++) {
//      for (int col = 0; col < 10; col++) {
//        if (event.getSource() == grid[row][col]) {
//          textLabel.setText("(" + col + "," + row + ")");
//        }
//      }
//    }
//  }
//}
//}
