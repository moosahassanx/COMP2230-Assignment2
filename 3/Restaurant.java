import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Restaurant
{
    // attributes
    private String name;
    private ArrayList<Customer> cList;
    private int maxCustomers;
    private int watch;
    private int numOfCus;
    private int toServe;
    private boolean cleaningState;

    // constructor
    public Restaurant(String n)
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
    public String getName()
    {
        return this.name;
    }

    public ArrayList<Customer> getCustomerList()
    {
        return this.cList;
    }

    public int getMaxCustomers()
    {
        return this.maxCustomers;
    }

    public int getNumOfCus()
    {
        return this.numOfCus;
    }

    public int getTime()
    {
        return this.watch;
    }

    public int availableSeats()
    {
        return 5 - this.numOfCus;
    }

    public boolean getCleaningState()
    {
        return this.cleaningState;
    }

    // mutators
    public void setName(String n)
    {
        this.name = n;
    }

    public void setCustomerList(ArrayList<Customer> c)
    {
        this.cList = c;
        this.toServe = cList.size();
    }

    public void setNeedToClean(boolean n)
    {
        this.cleaningState = n;
    }

    // methods
    public void openRestaurant()
    {
        for (Customer c : cList)
        {
            c.start();
        }

        while(this.toServe != 0)        // there are no more customers to serve
        {
            try { Thread.sleep(100); } catch (InterruptedException e) { }

            if(this.cleaningState == true)
            {
                this.cleaningState = true;

                while(availableSeats() != 5)
                {
                    try { Thread.sleep(100); } catch (InterruptedException e) { }
                    this.watch++;
                }
                for(int i = 0; i < 4; i++)
                {
                    try { Thread.sleep(100); } catch (InterruptedException e) { }
                    this.watch++;
                }

                this.cleaningState = false;
            }

            for (Customer c : this.cList) 
            {
                synchronized(c)
                {
                    c.notifyAll();
                }
            }

            this.watch++;
        }
    }

    public boolean checkFull()
    {
        if(this.maxCustomers == this.numOfCus)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void allowCustomer(Customer c) throws InterruptedException
    {
        this.numOfCus++;
    }

    public void leaveCustomer(Customer c)
    {
        this.numOfCus--;
        this.toServe--;
    }

}