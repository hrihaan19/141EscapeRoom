/**
 * Validate user input according to string array of acceptable inputs.
 * * @author GShorr, PLTW
 * @version 4/16/2019
 */
import java.util.Scanner;

 public class UserInput
{
    // FIX: Create the Scanner one time as a static variable
    private static final Scanner s = new Scanner(System.in);

    /**
     * Verifies that one of the string array provided as an argument will be
     * returned. 
     * <P>
     * This method will *keep asking* the user until a valid input is given.
     * This is good for "yes/no" or "play again" prompts.
     * <P>
     * @return the valid command the user entered
     * */
    public static String getValidInput(String[] validInputs)
    {
        String input = "";
        boolean valid = false;
        do
        {
            input = getLine().toLowerCase();
            for(String str : validInputs)
            {
                if(input.equals(str.toLowerCase()))
                    valid = true;
            }
            if(!valid)
                System.out.print("Invalid input. Please try again\n>");
        }
        while(!valid);
        return input;
    }

    /**
     * NEW METHOD: Gets a single command from the user.
     * <P>
     * This method checks the input *once*. If it is not in the list
     * of valid commands, it returns the special string "invalid".
     * This allows the main game to apply a penalty.
     * <P>
     * @return the valid command, or "invalid" if not found
     * */
    public static String getCommand(String[] validInputs)
    {
        String input = getLine().toLowerCase();
        
        // Handle "pickup" and "help" aliases
        if (input.equals("pickup")) input = "p";
        if (input.equals("help")) input = "h";
        if (input.equals("quit")) input = "q";

        for(String str : validInputs)
        {
            if(input.equals(str.toLowerCase()))
                return input; // Valid command, return it
        }
        
        // If the loop finishes, the command was not in the valid list
        return "invalid"; // Return special string
    }

    
    /**
     * Gets a line of text from the single, static Scanner.
     * @return
     */
    public static String getLine()
    {
        // FIX: Use the single, static Scanner object
        return s.nextLine();
    }


  }