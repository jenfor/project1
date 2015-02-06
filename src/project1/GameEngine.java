package project1;
/**
 *  Den här klassen skapar rummen och spelet.
 * 
 *  This class creates all rooms, creates the parser and starts
 *  the game.  It also evaluates and executes the commands that 
 *  the parser returns.
 * 
 * @author  (NN)
 * @version (date)
 */
public class GameEngine
{
    private Parser parser;
    public Room currentRoom;
    private UserInterface gui;

    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine()
    {
        parser = new Parser();
        createRooms();
    }

    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();
        updateRoom();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        gui.println("Välkommen till detta fantastiska spel!");
        gui.print("\n");
        gui.println(currentRoom.getLongDescription());
        //gui.showImage("tomt.jpg");
        //gui.showImage(currentRoom);
        //gui.setButtons("bloo");
        //gui.setButtons(currentRoom.getExits());
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room centrum, shop, tomt, spel1, spel2, spel3;
      
        // create the rooms
        centrum = new Room("i mitten av världen","stig.jpg");
        shop = new Room("i affären","slott.jpg");
        tomt = new Room("på din tomt","grasspltt.jpg");
        spel1 = new Room("vid spel 1","minispel1.jpg");
        spel2 = new Room("vid spel 2","strand.jpg");
        spel3 = new Room("vid spel 3","bro.jpg");

        
        // initialise room exits
        centrum.setExit("shop", shop);
        centrum.setExit("tomt", tomt);
        //centrum.setExit("spel1", spel1);
        //centrum.setExit("spel2", spel2);
        //centrum.setExit("spel3", spel3);
        
        shop.setExit("centrum", centrum);

        tomt.setExit("centrum", centrum);
        tomt.setExit("shop", shop);

        spel1.setExit("shop", shop);

        spel2.setExit("shop", shop);
        spel2.setExit("spel3", spel3);

        spel3.setExit("spel2", spel2);

        currentRoom = centrum; // start game outside
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    
    
    public void interpretCommand(String commandLine) 
    {
        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);

        if(command.isUnknown()) {
            gui.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit")) {
            if(command.hasSecondWord())
                gui.println("Quit what?");
            else
                endGame();
        }
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        gui.println("You are lost. You are alone. You wander");
        gui.println("around at the university.\n");
        gui.print("Your command words are: " + parser.showCommands());
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    
    public void goRoomClick(String direction)
    {
    	Room nextRoom = currentRoom.getExit(direction);
    	if (nextRoom == null)
            gui.println("Du kan inte gå dit härifrån!");
        else {
            currentRoom = nextRoom;
            updateRoom();
        }
    }
    
    private void goRoom(Command command) 
    {
        
    	/*if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Go where?");
            return;
        }*/
    
        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            gui.println("There is no door!");
        else {
            currentRoom = nextRoom;
            gui.println(currentRoom.getLongDescription());
        }
    }
    
    private void updateRoom()
    {  
    	gui.println(currentRoom.getLongDescription());
    	gui.showImage(currentRoom);
        gui.setButtons(currentRoom.getExits());
        gui.setBackground(currentRoom.getBackground());
    }

    private void endGame()
    {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
    }

    public Room getCurrentRoom()
    {
    	return currentRoom;
    }
}
