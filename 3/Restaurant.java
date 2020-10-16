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
    private boolean isFull;
    private BlockingQueue bQueue;

    // constructor
    public Restaurant(String n)
    {
        this.name = n;
        this.cList = new ArrayList<Customer>();
        this.maxCustomers = 5;
        this.watch = 0;
        this.numOfCus = 0;
        this.toServe = 0;
        this.isFull = false;
        this.bQueue = new ArrayBlockingQueue(5);
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

    // methods
    public void openRestaurant()
    {
        for (Customer c : cList)
        {
            c.start();
        }

        while(this.toServe != 0)
        {
            System.out.println("this.toServe: " + this.toServe);
            try { Thread.sleep(100); } catch (InterruptedException e) { }

            this.watch++;
            System.out.println("openRestaurant() loop");

            // after every loop, notify all the other customers they gucci gang prada
            /*
            for (Customer c: cList)
            {
                c.notifyAll();
            }
            */
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

    public void allowCustomer()
    {
        this.numOfCus++;
    }

    public void leaveCustomer()
    {
        this.numOfCus--;
        this.toServe--;
    }

}

// if you take the customer object.wait() itll stop everything until u do customerobject.notify()