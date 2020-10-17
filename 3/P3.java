// TITLE: 					Assignment2
// COURSE: 					COMP2240
// AUTHOR: 					Moosa Hassan
// STUDENT NUMBER: 			3331532
// DATE: 					17/10/2020 
// DESCRIPTION: 			Main file - reads data text files and sends each information to its relative classes

// importing java libraries
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class P3
{
    public static void main(final String args[]) throws FileNotFoundException 
    {
        // fetching data from file
        final Scanner file = new Scanner(new File(args[0]));
        String newText = "";
        final ArrayList<Customer> customerList = new ArrayList<>();
        final Restaurant restaurantMonitor = new Restaurant("Wacka Flocka Flame's Sippin Lean's Galore");

        // reading the file
        try 
        {
            while (file.hasNext()) 
            {
                newText = file.nextLine();

                // file has more information
                if (!newText.equals("END")) 
                {
                    // managing and assigning data
                    final String[] splitStr = newText.split("\\s+");
                    final int aTime = Integer.parseInt(splitStr[0]);
                    final String cusId = splitStr[1];
                    final int eTime = Integer.parseInt(splitStr[2]);

                    // create object with data and add to list
                    final Customer customerObject = new Customer(aTime, cusId, eTime, restaurantMonitor);
                    customerList.add(customerObject);
                }
            }
        } 
        catch (final Exception e) 
        {
            System.out.println("ERROR: " + e);
        }

        // send list to restaurant
        restaurantMonitor.setCustomerList(customerList);
        restaurantMonitor.openRestaurant();

        // displaying results
        System.out.println("Customer  Arrives     Seats    Leave");
        for (final Customer c : customerList)
        {
            c.getData();
        }
        
    }
}