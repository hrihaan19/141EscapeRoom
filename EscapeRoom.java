
public class EscapeRoom
{

  private static int highScore = Integer.MAX_VALUE;
  @SuppressWarnings("UnusedAssignment")
  public static int playGame() {
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
          case "d" -> score += game.movePlayer(m, 0);
          case "a" -> score += game.movePlayer(-m, 0);
          case "w" -> score += game.movePlayer(0, -m);
          case "s" -> score += game.movePlayer(0, m);
          case "jumpd" -> score += game.jumpPlayer(2 * m, 0);
          case "jumpa" -> score += game.jumpPlayer(-2 * m, 0);
          case "jumpw" -> score += game.jumpPlayer(0, -2 * m);
          case "jumps" -> score += game.jumpPlayer(0, 2 * m);

          case "checkd" -> game.isTrap(m, 0);
          case "checka" -> game.isTrap(-m, 0);
          case "checkw" -> game.isTrap(0, -m);
          case "checks" -> game.isTrap(0, m);

          case "springd" -> score += game.springTrap(m, 0);
          case "springa" -> score += game.springTrap(-m, 0);
          case "springw" -> score += game.springTrap(0, -m);
          case "springs" -> score += game.springTrap(0, m);

          case "p", "pickup" -> score += game.pickupPrize();
          case "replay" -> {
              int changeInScore = game.replay();
              score += changeInScore;
              if (changeInScore != 0) {
                  System.out.println("Applied the replay cost: " + changeInScore + " points.");
              }
              score = 0;
              System.out.println("Game reset. Score set to 0.");
            }

          case "q", "quit" -> play = false;
          case "h", "help", "?" -> printHelpMessage();
          default -> { 
              score -= 1;
              System.out.println("Invalid command. Score -1.");
            }
      }
      
      if (play) {
        int currentSteps = game.getSteps(); 
        System.out.println("Current Score: " + score + " and Steps Taken: " + currentSteps);

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

    return finalScore;
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
        int lastGameScore = playGame();
        if (lastGameScore < highScore) {
            highScore = lastGameScore;
            System.out.println("You have achieved your new best Score! (this really just means the lowest score. youre getting cooked bro)");
        }
        System.out.println("Best Score This Session: " + highScore);

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
