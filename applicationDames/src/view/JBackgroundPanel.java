package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

public class JBackgroundPanel extends JPanel {
  private BufferedImage img;
 
  public JBackgroundPanel(String url) {
    // load the background image
    try {
      img = ImageIO.read(new File(url));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
 
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // paint the background image and scale it to fill the entire space
    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
  }
}
