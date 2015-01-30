package project1;
import java.net.URL;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

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

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court 
     * yard".
     */
    public Room(String description, String pictureName) 
    {
        this.description = description;
        this.pictureName = pictureName;
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
        return "Du är " + description + ".\n" + getExitString();
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
    
    public ImageIcon getPicture()
    {
        URL imageURL = this.getClass().getClassLoader().getResource(pictureName);
        if(imageURL == null){
            System.out.println("image not found");
        	URL imageURL2 = this.getClass().getClassLoader().getResource("tomt.jpg");
        	ImageIcon icon2 = new ImageIcon(imageURL2);
        	return icon2;
        }
        else {
            ImageIcon icon = new ImageIcon(imageURL);
            return icon;

        }
        
    }

}

