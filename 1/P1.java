// TITLE: 					Assignment2
// COURSE: 					COMP2240
// AUTHOR: 					Moosa Hassan
// STUDENT NUMBER: 			3331532
// DATE: 					17/10/2020 
// DESCRIPTION: 			Main file - reads data text files and sends each information to its relative classes

// importing java libraries
import java.io.*;
import java.util.Scanner;

public class P1
{
    public static void main(final String args[]) throws IOException 
    {
        // fetching data from file
        final Scanner file = new Scanner(new File(args[0]));
        String newText = "";
        final Bridge ownBridge = new Bridge();

        // reading the file
        try 
        {
            while (file.hasNext()) 
            {
                newText = file.next();
                final char side = newText.charAt(0);

                // reading N
                if (side == 'N') 
                {
                    final String tempNum = newText.substring(2, 3);
                    final int numN = Integer.parseInt(tempNum);

                    // creating north farmers
                    for (int i = 0; i < numN; i++) 
                    {
                        final Farmer northFarmer = new Farmer("N_Farmer" + (i + 1), "North", ownBridge);
                        northFarmer.start();
                    }
                }
                // reading S
                else 
                {
                    final String tempNum = newText.substring(2, 3);
                    final int numS = Integer.parseInt(tempNum);

                    // creating south farmers
                    for (int i = 0; i < numS; i++) 
                    {
                        final Farmer southFarmer = new Farmer("S_Farmer" + (i + 1), "South", ownBridge);
                        southFarmer.start();
                    }
                }

            }
        }
        catch (final Exception e)
        {
            System.out.println("File reading error.");
        }

        file.close();
        
    }
}