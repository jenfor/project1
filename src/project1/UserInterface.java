package project1;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.awt.image.*;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author (Grupp 7)
 * @version (2015-01-30)
 */
public class UserInterface implements ActionListener
{
    private GameEngine engine;
    private JFrame myFrame;
    private JTextField entryField;
    private JTextArea log;
    private JLabel image;
    ImageIcon ikon;
    private JButton exitButton;
    private HashMap<String,JButton> exitButtons = new HashMap<String,JButton>();
    private JPanelWithBackground panel;
    

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface(GameEngine gameEngine)
    {
        engine = gameEngine;
        createGUI();
    }

    /**
     * Print out some text into the text area.
     */
    public void print(String text)
    {
        log.append(text);
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println(String text)
    {
        log.append(text + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Show an image file in the interface.
     */
    public void showImage(Room currentRoom)
    {
        /*URL imageURL = this.getClass().getClassLoader().getResource(imageName);
        if(imageURL == null)
            System.out.println("image not found");
        else {
            ImageIcon icon = new ImageIcon(imageURL);
            image.setIcon(icon);
            myFrame.pack();
        }*/
    	
    	ikon = currentRoom.getPicture();
    	
        image.setIcon(ikon);
        myFrame.pack();
        
        /*Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        myFrame.setLocation(d.width/2 - myFrame.getWidth()/2, d.height/2 - myFrame.getHeight()/2);
        myFrame.setVisible(true); */
        
       
    	
    }

    /**
     * Enable or disable input in the input field.
     */
    public void enable(boolean on)
    {
        entryField.setEditable(on);
        if(!on)
            entryField.getCaret().setBlinkRate(0);
    }

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        myFrame = new JFrame("Spel");
        entryField = new JTextField(34);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        
        double textHeight = height * 0.15;
        double textWidth = height * 0.15;
        double imgWidth = width * 0.9;
        double imgHeight = height * 0.9;
        
        
        myFrame.setPreferredSize(new Dimension((int)width, (int)height));
        myFrame.setResizable(false);

        log = new JTextArea();
        log.setEditable(false);
        //JScrollPane listScroller = new JScrollPane(log);
        JScrollPane textBox = new JScrollPane(log);
        
        exitButton = new JButton("Exit");
        //JButton exitButton = new JButton("Button 1");
        JButton button2 = new JButton("Affär");
        JButton mapButton = new JButton("Karta");
        JButton button4 = new JButton("Föremål");
        JButton button5 = new JButton("Pengar");
        JButton button6 = new JButton("Föremål");
        JButton button7 = new JButton("Pengar");
        JButton button9 = new JButton("eeee");
        
        JButton button8 = new JButton("nijhiuhu");
        button8.setPreferredSize(new Dimension(1,1));
        button8.setLayout(null);
		button8.setLocation(0,0);
		button8.setOpaque(false);
		button8.setContentAreaFilled(false);
		button8.setBorderPainted(false);
		
        button2.setPreferredSize(new Dimension(100, 100));
        button6.setPreferredSize(new Dimension(100, 100));
        
        int i = 0; 
        for(i = 0; i < 3; i++)
        {
        	String tempString = "" + i;
        	//exitButtons.put(tempString, new JButton("Exit"));
        	//exitButtons.put("0", button9);
        	exitButtons.put(tempString, new JButton("ooo"));
        }
        
        //listScroller.setPreferredSize(new Dimension(200, 200));
        //listScroller.setMinimumSize(new Dimension(200,200));
        

        
        textBox.setPreferredSize(new Dimension( (int)textWidth, (int)textHeight));
        textBox.setMinimumSize(new Dimension( (int)textWidth , (int)textHeight));
        
        //******************************************
        //JPanel panel = new JPanel();
        
        try {
			panel = new JPanelWithBackground("stig.jpg");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			
			e1.printStackTrace();
		}
        
        

        image = new JLabel();

        image.setPreferredSize(new Dimension((int)imgWidth, ((int)imgHeight) - (int)textHeight)); //bildstorlek, gör om till att skala
        image.setMinimumSize(new Dimension((int)imgWidth, ((int)imgHeight) - (int)textHeight)); //istället för att skära av
        image.setHorizontalAlignment(JLabel.CENTER);
        

        JPanel panelClickable = new JPanel();
        JPanel p = new JPanel(new GridLayout(4,1));
        JPanel p2 = new JPanel(new GridLayout(4,1));
        JPanel b = new JPanel();
        
        panelClickable.setOpaque(false);
        
        
        //***************************************************************************************
        //Knappar på bilden
        
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
        
        
        b.setLayout(new BoxLayout(b, BoxLayout.X_AXIS));

        p.add(button2);
        p.add(mapButton);
        p.add(exitButton);
        
        
        b.add(button4);
        b.add(button5);
        
        
		for(i = 0; i < 3; i++)
		{
			String temp = "" + i;
			p2.add(exitButtons.get(temp));
		}
        

        panel.setLayout(new BorderLayout());
        panel.add(textBox, BorderLayout.AFTER_LAST_LINE);
        panel.add(p, BorderLayout.WEST);
        panel.add(p2, BorderLayout.EAST);
        panel.add(b, BorderLayout.NORTH);
        //panel.add(image, BorderLayout.CENTER);
        //panel.add(entryField, BorderLayout.NORTH);
        //panel.add(p,BorderLayout.WEST);
        
        //panel.add(button8);
        panel.add(panelClickable);
        
        panel.setPreferredSize(new Dimension((int)width, (int)height)); //bildstorlek, gör om till att skala
        panel.setMinimumSize(new Dimension((int)width, (int)height)); //istället för att skära av
        

        myFrame.getContentPane().add(panel, BorderLayout.NORTH);

        // add some event listeners to some components
        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                engine.goRoomClick("shop");
            }
        });
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                engine.goRoomClick(engine.getCurrentRoom().getExits());
            }
        });
        
        mapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	println("Du är " + engine.currentRoom.getShortDescription());
            }
        });

        entryField.addActionListener(this);
        
        //***********************************************************************************************************************
        
      //Where the GUI is created:
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Meny");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("Öppna sparat spel",
                                 KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Spara", new ImageIcon("image.gif"));
        menuItem.setMnemonic(KeyEvent.VK_D);
        menu.add(menuItem);


        menuItem = new JMenuItem("Avsluta",
                                 new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        menu.add(menuItem);


        //a group of radio button menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_O);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        //a group of check box menu items
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        menu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        menu.add(cbMenuItem);

        //a submenu
        menu.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        submenu.add(menuItem);
        menu.add(submenu);

        //Build second menu in the menu bar.
        menu = new JMenu("Inställningar");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        menuBar.add(menu);

        myFrame.setJMenuBar(menuBar);
        
      //Build second menu in the menu bar.
        menu = new JMenu("Hjälp");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription(
                "This menu does nothing");
        
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Hjääälp",
                new ImageIcon("images/middle.gif"));
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent event) {
        			JOptionPane.showMessageDialog( null, "[hjälp kommer senare]","Hjälp",JOptionPane.OK_CANCEL_OPTION); 
        		}
        });
        
        menu.add(menuItem);

        myFrame.setJMenuBar(menuBar);
        
        
        
        //*****************************************
        

        myFrame.pack();
        myFrame.setVisible(true);
        entryField.requestFocus();
        
    }

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed(ActionEvent e) 
    {
        // no need to check the type of action at the moment.
        // there is only one possible action: text entry
        processCommand();
    }

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        boolean finished = false;
        String input = entryField.getText();
        entryField.setText("");

        engine.interpretCommand(input);
    }
    
	public void setButtons(String exit)
    {
		//JButton exitButton = new JButton("setButton");
    	//exitButton.setLabel("setButton");
    	//p.add(exitButton);
    	exitButton.setLabel(exit);
        /*Set keys = exits.keySet();
        for(Iterator iter = keys.iterator(); iter.hasNext(); )
        	button.setLabel((String)iter.next());*/
        
    }
	
	public void setAllButtons(HashMap<String,String> exits)
    {
		
		int i;
		for(i = 0; i < 3; i++)
		{
			String temp = "" + i;
			String exitString;
			
			exitString = exits.get(temp);
			exitButtons.get(temp).setLabel(exitString);

			//exitButtons.get(temp);
			//setLabel(exitString);
			
		}
		
		/*Set keys = exits.keySet();
		for(Iterator iter = keys.iterator(); iter.hasNext(); )
		{
			iter. = new JButton("exit");
		}*/
		
		//JButton exitButton = new JButton("setButton");
    	//exitButton.setLabel("setButton");
    	//p.add(exitButton);
    	//button.setLabel(exits);
        /*Set keys = exits.keySet();
        for(Iterator iter = keys.iterator(); iter.hasNext(); )
        	button.setLabel((String)iter.next());*/
        
    }
	
	public void setBackground(Image fileName)
	{
		panel.changePanelImg(fileName);
	}
	
}
