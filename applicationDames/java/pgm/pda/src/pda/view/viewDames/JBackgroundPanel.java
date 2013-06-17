package pda.view.viewDames;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

public class JBackgroundPanel extends JPanel {
  private BufferedImage img;
 
  public JBackgroundPanel(String url) {
    try {
      img = ImageIO.read(new File(url));
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
 
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
  }
}
