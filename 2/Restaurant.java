// TITLE: 					Assignment2
// COURSE: 					COMP2240
// AUTHOR: 					Moosa Hassan
// STUDENT NUMBER: 			3331532
// DATE: 					17/10/2020 
// DESCRIPTION: 			semaphore-based class: controls when and how many customers can enter

// importing java libraries
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Restaurant
{
    // attributes
    private String name;
    private final Semaphore sem;
    private int watch;
    private ArrayList<Customer> cList;
    private int servedCustomers;
    private boolean isCleaning;

    // constructor
    public Restaurant(final String n) 
    {
        this.name = n;
        this.sem = new Semaphore(5); // limit to 5 seats
        this.watch = 0;
        this.cList = new ArrayList<Customer>();
        this.servedCustomers = 0;
        this.isCleaning = false;
    }

    // accessors
    public String getName() { return this.name; }

    public ArrayList<Customer> getArraylist() { return cList; }

    public boolean getCleaningState() { return this.isCleaning; }

    public int getTime() { return this.watch; }

    // mutators
    public void setName(final String n) { this.name = n; }

    public void setArraylist(final ArrayList<Customer> c) { this.cList = c; }

    public void setCleaningState(final boolean s) { this.isCleaning = s; }

    // methods
    public int availableSeats() 
    {
        return this.sem.availablePermits();
    }

    public void allowCustomer() 
    {
        try { sem.acquire(); } catch (final Exception e) { }
    }

    public void leaveCustomer() 
    {
        // semaphore release
        servedCustomers++;
        sem.release();
    }

    public void runSimulation() 
    {
        // start every thread in the list simultaneously
        for (final Customer customer : cList) 
        {
            customer.start();
        }

        // iterate time until all the customers have been served
        while (this.servedCustomers < cList.size()) 
        {
            try { Thread.sleep(100); } catch (final InterruptedException e) { }

            // customers have filled up all seats in the restaurant
            if (availableSeats() == 0) 
            {
                // begin cleaning process
                setCleaningState(true);

                // iterate time until all seats are not empty
                while (availableSeats() != 5) 
                {
                    try { Thread.sleep(100); } catch (final InterruptedException e) { }
                    this.watch++;
                }
                for (int i = 0; i < 4; i++) 
                {
                    try { Thread.sleep(100); } catch (final InterruptedException e) { }
                    this.watch++;
                }

                // cleaning process is finished
                setCleaningState(false);
            }
            
            this.watch++;
        }
    }

}