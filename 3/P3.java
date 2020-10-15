import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class P3
{
    public static void main(String args[]) throws FileNotFoundException
    {
        // fetching data from file
        Scanner file = new Scanner(new File(args[0]));
        String newText = "";
        ArrayList<Customer> customerList = new ArrayList<>();
        Restaurant restaurantMonitor = new Restaurant("Wacka Flocka Flame's Sippin Lean's Galore");

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

                    Customer customerObject = new Customer(aTime, cusId, eTime, restaurantMonitor);
                    customerList.add(customerObject);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }

        // logic
        restaurantMonitor.setCustomerList(customerList);
        ArrayList<Customer> finalList = restaurantMonitor.getCustomerList();
        
        restaurantMonitor.openRestaurant();

        // displaying results
        System.out.println("Customer  Arrives     Seats    Leave");
        for (Customer c : finalList)
        {
            c.getData();
        }
        
    }
}