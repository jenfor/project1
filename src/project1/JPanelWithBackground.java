package project1;
import javax.imageio.ImageIO;
import javax.swing.*; //hipp

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.image.*;

public class JPanelWithBackground extends JPanel{
	private Image backgroundImage;
	private URL imageURL;

	  // Some code to initialize the background image.
	  // Here, we use the constructor to load the image. This
	  // can vary depending on the use case of the panel.
	  public JPanelWithBackground(String fileName) throws IOException{
		imageURL = this.getClass().getClassLoader().getResource(fileName);
	    backgroundImage = ImageIO.read(new File(imageURL.getPath() ));
	  }

	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    // Draw the background image.
	    g.drawImage(backgroundImage, 0, 0, this);
	  }
	  
	  public void changePanelImg(Image fileName)
	  {
			backgroundImage = fileName;
			/*imageURL = this.getClass().getClassLoader().getResource(fileName);
			backgroundImage = ImageIO.read(new File(imageURL.getPath() ));*/

	  }
	  
}

