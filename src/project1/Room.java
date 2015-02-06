package project1;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  (NNI)
 * @version (date)
 */

public class Room 
{
    private String description;
    private String pictureName;
    private HashMap<String,Room> exits;        // stores exits of this room.
    private ImageIcon roomIcon;


    public Room(String description, String pictureName) 
    {
        this.description = description;
        this.pictureName = pictureName;
        createPicture();
        exits = new HashMap<String,Room>();
    }

    /**
     * Define an exit from this room.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, in the form:
     *     You are in the kitchen.
     *     Exits: north west
     */
    public String getLongDescription()
    {
        return "Du �r " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set keys = exits.keySet();
        for(Iterator iter = keys.iterator(); iter.hasNext(); )
            returnString += " " + iter.next();
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public void createPicture()
    {	/*Fixat skalningen s� bilden skalar efter f�nstret. �ven delat upp getPicture i createPicture (som
     	SKAPAR bilden och getPicture som h�mtar den. S� man inte skapar om den varje g�ng man ska se bilden.*/
        URL imageURL = this.getClass().getClassLoader().getResource(pictureName);
        
        Dimension imgSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = (imgSize.getWidth()) * 1;
        double height = (imgSize.getHeight()) * 1;

        if(imageURL == null){
            System.out.println("image not found");
        	imageURL = this.getClass().getClassLoader().getResource("castle.gif");
        	ImageIcon icon = new ImageIcon(imageURL);
            Image scaledIm = icon.getImage().getScaledInstance((int)width, (int)height, Image.SCALE_FAST);
            roomIcon = new ImageIcon(scaledIm);
        }
        else {
            ImageIcon icon = new ImageIcon(imageURL);
            Image scaledIm = icon.getImage().getScaledInstance((int)width, (int)height, Image.SCALE_FAST);
            roomIcon = new ImageIcon(scaledIm);

        }
        
    }
    
    public ImageIcon getPicture()
    {	
    	return roomIcon;
    }
    
    public HashMap<String,Room> getExits()
    {
    	return exits;
    }

}

