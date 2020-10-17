// TITLE: 					Assignment2
// COURSE: 					COMP2240
// AUTHOR: 					Moosa Hassan
// STUDENT NUMBER: 			3331532
// DATE: 					17/10/2020 
// DESCRIPTION: 			monitor-acted class: controls when and how many customers can enter

// importing java libraries
import java.util.ArrayList;

public class Restaurant
{
    // attributes
    private String name;
    private ArrayList<Customer> cList;
    private final int maxCustomers;
    private int watch;
    private int numOfCus;
    private int toServe;
    private boolean cleaningState;

    // constructor
    public Restaurant(final String n) 
    {
        this.name = n;
        this.cList = new ArrayList<Customer>();
        this.maxCustomers = 5;
        this.watch = 0;
        this.numOfCus = 0;
        this.toServe = 0;
        this.cleaningState = false;
    }

    // accessors
    public String getName() { return this.name; }

    public ArrayList<Customer> getCustomerList() { return this.cList; }

    public int getMaxCustomers() { return this.maxCustomers; }

    public int getNumOfCus() { return this.numOfCus; }

    public int getTime() { return this.watch; }

    public int availableSeats() { return 5 - this.numOfCus; }

    public boolean getCleaningState() { return this.cleaningState; }

    // mutators
    public void setName(final String n) { this.name = n; }

    public void setNeedToClean(final boolean n) { this.cleaningState = n; }

    public void setCustomerList(final ArrayList<Customer> c) 
    {
        this.cList = c;
        this.toServe = cList.size();
    }

    // methods
    public void openRestaurant() 
    {
        // start every thread in the list simultaneously
        for (final Customer c : cList) 
        {
            c.start();
        }

        // iterate time until all the customers have been served
        while (this.toServe != 0) 
        {
            try { Thread.sleep(100); } catch (final InterruptedException e) { }

            // restaurant requires cleaning process
            if (this.cleaningState == true) 
            {
                // begin cleaning process
                this.cleaningState = true;

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
                this.cleaningState = false;
            }

            // all customers must notify all other customers for next stage
            for (final Customer c : this.cList) 
            {
                synchronized (c) 
                {
                    c.notifyAll();
                }
            }

            this.watch++;
        }
    }

    public boolean checkFull() 
    {
        // the maximum number of customers has reached
        if (this.maxCustomers == this.numOfCus) 
        {
            return true;
        }
        // there are available seats in the restaurant
        else 
        {
            return false;
        }
    }

    public void allowCustomer(final Customer c) throws InterruptedException 
    {
        this.numOfCus++; // increment current number of customers
    }

    public void leaveCustomer(final Customer c)
    {
        this.numOfCus--;    // customers in the restaurant currently
        this.toServe--;     // customers remaining to eat/enter
    }

}