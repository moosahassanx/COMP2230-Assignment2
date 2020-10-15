import java.io.*;
import java.util.Scanner;

public class P1
{
    public static void main(String args[]) throws IOException
    {
        // fetching data from file
        Scanner file = new Scanner(new File(args[0]));
        String newText = "";
        Bridge ownBridge = new Bridge();
        try
        {
            while(file.hasNext())
            {
                newText = file.next();
                char side = newText.charAt(0);

                // reading N
                if(side == 'N')
                {
                    String tempNum = newText.substring(2, 3);
                    int numN = Integer.parseInt(tempNum);
                    
                    // creating north farmers
                    for(int i = 0; i < numN; i++)
                    {
                        Farmer northFarmer = new Farmer("N_Farmer" + (i+1) , "North", ownBridge);
                        northFarmer.start();
                    }
                }
                // reading S
                else
                {
                    String tempNum = newText.substring(2, 3);
                    int numS = Integer.parseInt(tempNum);

                    // creating south farmers
                    for(int i = 0 ; i < numS; i++)
                    {
                        Farmer southFarmer = new Farmer("S_Farmer" + (i+1), "South", ownBridge);
                        southFarmer.start();
                    }
                }

            }
        }
        catch(Exception e)
        {
            System.out.println("File reading error.");
        }

        file.close();
        
    }
}