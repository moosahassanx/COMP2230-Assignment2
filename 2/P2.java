import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class P2 
{
    public static void main(String args[]) throws IOException, InterruptedException
    {
        // fetching data from file
        Scanner file = new Scanner(new File(args[0]));
        String newText = "";
        Restaurant restaurant = new Restaurant("Gusteau's");
        ArrayList<Customer> customerList = new ArrayList<Customer>();

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

                    Customer customerObject = new Customer(aTime, cusId, eTime, restaurant);
                    customerList.add(customerObject);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }

        // send to list to restaurant
        restaurant.setArraylist(customerList);
        restaurant.runSimulation();

        // displaying results
        System.out.println("Customer  Arrives     Seats    Leave");
        for (Customer c : customerList)
        {
            c.getData();
        }
    }
}