import java.io.*;
import java.util.Scanner;

public class PA1
{
    public static void main(String args[]) throws IOException
    {
        // fetching data from file
        Scanner file = new Scanner(new File(args[0]));
        String newText = "";
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
                }
                // reading S
                else
                {
                    String tempNum = newText.substring(2, 3);
                    int numS = Integer.parseInt(tempNum);
                }

            }
        }
        catch(Exception e)
        {
            System.out.println("File reading error.");
        }
        file.close();

        // learning threads...
        System.out.println("THREAD TESTING");
        Thread t1 = new Thread(new Farmer("one"));
        Thread t2 = new Thread(new Farmer("two"));
        Thread t3 = new Thread(new Farmer("three"));
        Thread t4 = new Thread(new Farmer("four"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}