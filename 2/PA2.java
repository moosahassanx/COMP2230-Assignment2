import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PA2 {
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

        // run through all the customers
        for(int i = 0; i < customerList.size(); i++)
        {
            // there are more than 0 seats available
            if(restaurant.availableSeats() > 0)
            {
                restaurant.allowCustomer(customerList.get(i));
            }
            // theres no seats available
            else
            {
                restaurant.waitCustomer(customerList.get(i));
            }
        }

        // displaying results
        System.out.println("Customer  Arrives     Seats    Leave");
        System.out.println("C1\t   0\t      0\t       5");              // FAKE OUTPUT
    }
}