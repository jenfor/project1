package project1;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Shop extends Room {
	private JPanel panelClickable;
    private String description;
    private String pictureName;
    private String backgroundName;
    private HashMap<String,Room> exits;        // stores exits of this room.
    private String exit;
    private ImageIcon roomIcon;
    private Image backgroundImage;
	
	public Shop(String description, String pictureName)
	{
    	/*String temp = pictureName;
        this.description = description;
        this.pictureName = pictureName;
        backgroundName = pictureName;
        super.createPicture();
        exits = new HashMap<String,Room>();
        super.createBackground(pictureName);*/
		
	}
	
	
	private void setupShop()
	{
        //***************************************************************************************
        //Knappar på bilden
        
		panelClickable = new JPanel();
		
		panelClickable.setOpaque(false);
        //panelClickable.setLayout(new GridLayout(4,4));
        panelClickable.setLayout(null);
        JButton clickButton = new JButton ("Skylt");
        clickButton.setBounds(4,6,200,400);
        clickButton.setContentAreaFilled(false);
        //clickButton.setBorderPainted(false); //med eller utan kant
        panelClickable.add(clickButton);
        
        JButton clickButton2 = new JButton ("Skylt2");
        clickButton2.setBounds(300,400,200,200);
        clickButton2.setContentAreaFilled(false);
        //clickButton.setBorderPainted(false); //med eller utan kant
        panelClickable.add(clickButton2);
        
        //***************************************************************************************
	}
	
	public JPanel getRoomPanel()
	{
		return panelClickable;
	}
	
}
