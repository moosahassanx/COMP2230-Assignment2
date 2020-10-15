import java.util.ArrayList;

public class Restaurant
{
    // attributes
    private String name;
    private ArrayList<Customer> cList;
    private int maxCustomers;

    // constructor
    public Restaurant(String n)
    {
        this.name = n;
        this.cList = new ArrayList<Customer>();
        this.maxCustomers = 5;
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

    // mutators
    public void setName(String n)
    {
        this.name = n;
    }

    public void setCustomerList(ArrayList<Customer> c)
    {
        this.cList = c;
    }

    // methods
    public void openRestaurant()
    {
        for (Customer c : cList) 
        {
            c.start();
        }
    }

    // testing methods
    synchronized void doingTesting()
    {
        //
    }

}