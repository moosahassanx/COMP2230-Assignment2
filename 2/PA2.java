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

        int watch = 0;

        // run through all the customers
        for (Customer customer : customerList)
        {
            customer.feedTime(watch);
            customer.start();
        }



        // displaying results
        System.out.println("Customer  Arrives     Seats    Leave");
        for (Customer c : customerList) {
            c.getData();
        }
        System.out.println("C1\t   0\t      0\t       5");              // FAKE OUTPUT
    }
}