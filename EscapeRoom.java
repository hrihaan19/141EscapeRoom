/*
* Problem 1: Escape Room
* V1.1 - Simplified Controls
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
  private static int highScore = Integer.MAX_VALUE;
  // All game logic is now in a separate method
  public static int playGame() {
    GameGUI game = new GameGUI();
    game.createBoard();

    // size of move
    int m = 60; 
    int score = 0;

    // NEW: Simplified command list
    String[] validCommands = { 
        // Movement
        "w", "a", "s", "d",
        // Jump (2 spaces)
        "jumpw", "jumpa", "jumps", "jumpd",
        // Check for trap
        "checkw", "checka", "checks", "checkd",
        // Spring a trap
        "springw", "springa", "springs", "springd",
        // Other actions
        "p", "pickup", "q", "quit", "replay", "h", "help", "?"
    };
  
    // This is the inner game loop for a single playthrough
    boolean play = true;
    while (play)
    {
      System.out.print("\nEnter command (h for help) > ");
      
      // Use the new getCommand method that checks for invalid input
      String input = UserInput.getCommand(validCommands);
      
      // NEW: Updated switch for simple commands
      switch (input)
      {
          case "d" -> score += game.movePlayer(m, 0);
          case "a" -> score += game.movePlayer(-m, 0);
          case "w" -> score += game.movePlayer(0, -m);
          case "s" -> score += game.movePlayer(0, m);
          case "jumpd" -> score += game.movePlayer(2 * m, 0);
          case "jumpa" -> score += game.movePlayer(-2 * m, 0);
          case "jumpw" -> score += game.movePlayer(0, -2 * m);
          case "jumps" -> score += game.movePlayer(0, 2 * m);
          case "checkd" -> game.isTrap(m, 0); // isTrap() prints its own message
          case "checka" -> game.isTrap(-m, 0);
          case "checkw" -> game.isTrap(0, -m);
          case "checks" -> game.isTrap(0, m);
          case "springd" -> score += game.springTrap(m, 0);
          case "springa" -> score += game.springTrap(-m, 0);
          case "springw" -> score += game.springTrap(0, -m);
          case "springs" -> score += game.springTrap(0, m);
          case "p", "pickup" -> score += game.pickupPrize();
          case "replay" -> {
              score += game.replay(); // Get replay penalty/bonus
              score = 0; // Reset score to 0
              System.out.println("Game reset. Score set to 0.");
            }
          case "q", "quit" -> play = false; // This exits the inner loop
          case "h", "help", "?" -> printHelpMessage();
          default -> {
              // This will catch the "invalid" string from UserInput.getCommand
              score -= 1; // Apply 1 point penalty
              System.out.println("Invalid command. Score -1.");
            }
      }
        // --- Player Movement (1 Space) ---
        // --- Player Movement (Jump 2 Spaces) ---
        // --- Check for Trap ---
        // --- Spring Trap ---
        // --- Player Actions ---
        // --- Game Control ---
        // --- Help Message ---
        // --- Invalid Command Penalty ---
      
      // Print the score after every valid (or invalid) move
      if (play) {
        int currentSteps = game.getSteps(); 
        System.out.println("Current Score: " + score + " and Steps Taken: " + currentSteps);
      }
    }

    // --- Game Over ---
    // This code runs when the inner loop (play) ends
    int finalScore = score + game.endGame();

    System.out.println("--- Game Over ---");
    System.out.println("Final Score: " + finalScore);
    System.out.println("Total Steps: " + game.getSteps());

    return finalScore;
  }

  /**
   * Prints all available commands.
   */
  public static void printHelpMessage() {
      System.out.println("\n Escape Room Key");
      System.out.println("Movement (One Space only):");
      System.out.println("  w (up), a (left), s (down), d (right)");
      System.out.println("\nMovement (Super Jump Two Spaces):");
      System.out.println("  jumpw, jumpa, jumps, jumpd");
      System.out.println("\nTrap Actions (Adjacent Square):");
      System.out.println("  check[wasd] - Check for a trap (e.g., 'checkw')");
      System.out.println("  spring[wasd] - Spring a trap (e.g., 'springa')");
      System.out.println("\nActions (Current Square):");
      System.out.println("  p (pickup) - Pick up a prize.");
      System.out.println("\nGame:");
      System.out.println("  replay     - Reset the game and score (penalty if not at the end).");
      System.out.println("  q (quit)   - End the game.");
      System.out.println("  h (?)      - Show this help message.");
      System.out.println("--------------------------------\n");
  }

  /**
   * Main method - runs the entire game
   */
  public static void main(String[] args) 
  {      
    // welcome message
    System.out.println("Welcome to HRA EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("and pick up all the prizes.");
    
    // Show help message *before* playing
    printHelpMessage();

    // This is the new outer loop for "Play Again"
    boolean gameIsRunning = true;
    while (gameIsRunning) {
        // Run a full game session
        int lastGameScore = playGame();
        if (lastGameScore < highScore){
          highScore = lastGameScore;
          System.out.println("You have achieved your new best Score! (this really just means the lowest score. youre getting cooked bro)");
        }
        System.out.println("Best Score This Session: " + highScore);

        // After the game session (playGame()) is over, ask to play again
        System.out.print("\nDo you want to play again? (y/n) > ");
        String[] yesNoCommands = {"yes", "no", "y", "n"};
        String decision = UserInput.getValidInput(yesNoCommands); // Use original looping method

        if (decision.equals("no") || decision.equals("n")) {
            gameIsRunning = false;
        } else {
          // If yes, the loop will simply restart
          System.out.println("\n--- New Game ---");
          // Re-print help just in case
          printHelpMessage();
            
        }
    }

    System.out.println("Thanks for playing!");
  }
}


    System.out.println("Thanks for playing!");
  }
}
