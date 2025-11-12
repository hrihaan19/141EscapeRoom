/*
* Problem 1: Escape Room
* * V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/

/**
 * Create an escape room game where the player must navigate
 * to the other side of the screen in the fewest steps, while
 * avoiding obstacles and collecting prizes.
 */
public class EscapeRoom
{
    // All comments from the original file are omitted for brevity
    // but the requirements are implemented below.

  public static void main(String[] args) 
  {      
    // welcome message
    System.out.println("----------------------\n");
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("pick up all the prizes.\n");
    System.out.println("\nHERE ARE THE COMMANDS YOU CAN ENTER:");
    System.out.println("MOVEMENT (ONE SPACE):");
    System.out.println("  right (r/d), left (l/a), up (u/w), down (s)");
    System.out.println("MOVEMENT (JUMP TWO SPACES):");
    System.out.println("  jump (jr), jumpleft (jl), jumpup (ju), jumpdown (jd)");
    System.out.println("TRAP ACTIONS:");
    System.out.println("  check[direction] (c[r,l,u,d]) - Check adjacent square for a trap.");
    System.out.println("  spring[direction] (s[r,l,u,d]) - Spring trap in adjacent square.");
    System.out.println("ACTIONS:");
    System.out.println("  pickup (p) - Pick up a prize at your current location.");
    System.out.println("GAME OPTIONS:");
    System.out.println("  replay     - Reset the game (penalty if not at the end).");
    System.out.println("  quit (q)   - End the game.");
    System.out.println("  help (?)   - Show this help message.");
    
    GameGUI game = new GameGUI();
    game.createBoard();

    // size of move
    int m = 60; 
    // individual player moves (not used in this implementation, m is used directly)
    int px = 0;
    int py = 0; 
    
    int score = 0;

    // This Scanner is not used, UserInput.java handles input
    // Scanner in = new Scanner(System.in); 
    
    // UPDATED: Added WASD, check trap (c-), and spring trap (s-) commands
    String[] validCommands = { 
        "right", "left", "up", "down", "r", "l", "u", "d", "w", "a", "s",
        "jump", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
        "checkright", "cr", "checkleft", "cl", "checkup", "cu", "checkdown", "cd",
        "springright", "sr", "springleft", "sl", "springup", "su", "springdown", "sd",
        "pickup", "p", "quit", "q", "replay", "help", "?"
    };
  
    // set up game
    boolean play = true;
    while (play)
    {
      /* TODO: get all the commands working */
      /* Your code here */
      
      // Get valid user input
      System.out.print("\nEnter a command. you can type 'help' or '?' for the valid commands: ");
      String input = UserInput.getValidInput(validCommands);
      
      // Use a switch statement to handle game logic based on the command
      switch (input)
      {
          case "right", "r", "d" -> // ADDED 'd' for right
              score += game.movePlayer(m, 0);
          case "left", "l", "a" -> // ADDED 'a' for left
              score += game.movePlayer(-m, 0);
          case "up", "u", "w" -> // ADDED 'w' for up
              score += game.movePlayer(0, -m);
          case "down", "s" -> // ADDED 's' for down
              score += game.movePlayer(0, m);
          case "jump", "jr" -> score += game.movePlayer(2 * m, 0);
          case "jumpleft", "jl" -> score += game.movePlayer(-2 * m, 0);
          case "jumpup", "ju" -> score += game.movePlayer(0, -2 * m);
          case "jumpdown", "jd" -> score += game.movePlayer(0, 2 * m);
          case "checkright", "cr" -> game.isTrap(m, 0); // isTrap() prints its own message
          case "checkleft", "cl" -> game.isTrap(-m, 0);
          case "checkup", "cu" -> game.isTrap(0, -m);
          case "checkdown", "cd" -> // Default "jump" to "jump right"
              game.isTrap(0, m);
          case "springright", "sr" -> score += game.springTrap(m, 0);
          case "springleft", "sl" -> score += game.springTrap(-m, 0);
          case "springup", "su" -> score += game.springTrap(0, -m);
          case "springdown", "sd" -> score += game.springTrap(0, m);
          case "pickup", "p" -> score += game.pickupPrize();
          case "replay" -> {
              score += game.replay();
              System.out.println("Game reset. Score: " + score);
            }
          case "quit", "q" -> play = false;
          case "help", "?" -> {
              System.out.println("\nHERE ARE THE COMMANDS YOU CAN ENTER:");
              System.out.println("MOVEMENT (ONE SPACE):");
              System.out.println("  right (r/d), left (l/a), up (u/w), down (s)");
              System.out.println("MOVEMENT (JUMP TWO SPACES):");
              System.out.println("  jump (jr), jumpleft (jl), jumpup (ju), jumpdown (jd)");
              System.out.println("TRAP ACTIONS:");
              System.out.println("  check[direction] (c[r,l,u,d]) - Check adjacent square for a trap.");
              System.out.println("  spring[direction] (s[r,l,u,d]) - Spring trap in adjacent square.");
              System.out.println("ACTIONS:");
              System.out.println("  pickup (p) - Pick up a prize at your current location.");
              System.out.println("GAME OPTIONS:");
              System.out.println("  replay     - Reset the game (penalty if not at the end).");
              System.out.println("  quit (q)   - End the game.");
              System.out.println("  help (?)   - Show this help message.");
            }
      }
        // --- Player Movement (1 Space) ---
        // --- Player Movement (Jump 2 Spaces) ---
        // Default "jump" to "jump right"
        // --- Check for Trap (NEW) ---
        // --- Spring Trap (NEW) ---
        // --- Player Actions ---
        // --- Game Control ---
        // --- Help Message ---
              // Optional: Print score after every move
      // System.out.println("Current Score: " + score);
    }

  

    score += game.endGame();

    System.out.println("--- Game Over ---");
    System.out.println("Final Score: " + score);
    System.out.println("Total Steps: " + game.getSteps());
  }
}
