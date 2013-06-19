package pda.view.viewDames;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

/**
*Cette classe est une sous classe de JPanel permettant de mettre une image en fond de ce panel
*/
public class JBackgroundPanel extends JPanel {
  private BufferedImage img;
 
  public JBackgroundPanel() {
    try {
      img = ImageIO.read(new File("data/img/Fond_net.jpg"));
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
