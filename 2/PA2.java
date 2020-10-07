import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PA2 {
    public static void main(String args[]) throws IOException
    {
        // fetching data from file
        Scanner file = new Scanner(new File(args[0]));
        String newText = "";

        try
        {
            while(file.hasNext())
            {
                newText = file.nextLine();

                if(!newText.equals("END"))
                {
                    String[] splitStr = newText.split("\\s+");
                    
                    int aTime = Integer.parseInt(splitStr[0]);
                    String cusId = splitStr[1];
                    int eTime = Integer.parseInt(splitStr[2]);

                    Customer customerObject = new Customer(aTime, cusId, eTime);

                    System.out.println("Customer (" + cusId + ") has been created with: ");
                    System.out.println("arrival time: " + aTime);
                    System.out.println("eating time: " + eTime + "\n");
                }
                else
                {
                    System.out.println("END OF CREATING CUSTOMERS");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }
    }
}
