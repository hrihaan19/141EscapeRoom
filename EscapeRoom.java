
public class EscapeRoom
{

  public static void playGame() {
    GameGUI game = new GameGUI();
    game.createBoard();

    int m = 60; 
    int score = 0;

    String[] validCommands = { 
        "w", "a", "s", "d",
        "jumpw", "jumpa", "jumps", "jumpd",
        "checkw", "checka", "checks", "checkd",
        "springw", "springa", "springs", "springd",
        "p", "pickup", "q", "quit", "replay", "h", "help", "?"
    };
  
    boolean play = true;
    while (play)
    {
      System.out.print("\nEnter command (h for help) > ");
      
      String input = UserInput.getCommand(validCommands);
      
      switch (input)
      {
          case "d": 
              score += game.movePlayer(m, 0);
              break;
          case "a": 
              score += game.movePlayer(-m, 0);
              break;
          case "w": 
              score += game.movePlayer(0, -m);
              break;
          case "s": 
              score += game.movePlayer(0, m);
              break;
          case "jumpd": 
              score += game.jumpPlayer(2 * m, 0);
              break;
          case "jumpa": 
              score += game.jumpPlayer(-2 * m, 0);
              break;
          case "jumpw": 
              score += game.jumpPlayer(0, -2 * m);
              break;
          case "jumps": 
              score += game.jumpPlayer(0, 2 * m);
              break;

          case "checkd":
              game.isTrap(m, 0); 
              break;
          case "checka":
              game.isTrap(-m, 0);
              break;
          case "checkw":
              game.isTrap(0, -m);
              break;
          case "checks":
              game.isTrap(0, m);
              break;

          case "springd":
              score += game.springTrap(m, 0);
              break;
          case "springa":
              score += game.springTrap(-m, 0);
              break;
          case "springw":
              score += game.springTrap(0, -m);
              break;
          case "springs":
              score += game.springTrap(0, m);
              break;

          case "p":
          case "pickup":
              score += game.pickupPrize();
              break;

          case "replay":
              score += game.replay(); 
              score = 0; 
              System.out.println("Game reset. Score set to 0.");
              break;
          case "q":
          case "quit":
              play = false; 
              break;
              
          case "h":
          case "help":
          case "?":
              printHelpMessage();
              break;
          
          default: 
              score -= 1;
              System.out.println("Invalid command. Score -1.");
              break;
      }
      
      if (play) {
          System.out.println("Current Score: " + score);
      }

      if (game.allPrizesCollected()) {
          System.out.println("\n*** YOU COLLECTED ALL THE PRIZES! YOU WIN! ***");
          play = false; 
      }
    }

 
    int endScoreBonus = game.endGame(); 
    int finalScore = score + endScoreBonus;

    System.out.println("--- Game Over ---");
    System.out.println("Final Score: " + finalScore);
    System.out.println("Total Steps: " + game.getSteps());
  }


  public static void printHelpMessage() {
      System.out.println("\n--- Escape Room Commands ---");
      System.out.println("Movement (One Space):");
      System.out.println("  w (up), a (left), s (down), d (right)");
      System.out.println("\nMovement (Jump Two Spaces):");
      System.out.println("  jumpw, jumpa, jumps, jumpd (CAN bypass walls)");
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


  public static void main(String[] args) 
  {      
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("and pick up all the prizes.");
    
    printHelpMessage();

    boolean gameIsRunning = true;
    while (gameIsRunning) {
        playGame();

        System.out.print("\nDo you want to play again? (y/n) > ");
        String[] yesNoCommands = {"yes", "no", "y", "n"};
        String decision = UserInput.getValidInput(yesNoCommands); 

        if (decision.equals("no") || decision.equals("n")) {
            gameIsRunning = false;
        } else {
          System.out.println("\n--- New Game ---");
          printHelpMessage();
        }
    }

    System.out.println("Thanks for playing!");
  }
}
