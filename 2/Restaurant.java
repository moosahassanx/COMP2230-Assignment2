import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Restaurant
{
    // attributes
    private String name;
    private Semaphore sem;
    private int watch;
    private ArrayList<Customer> cList;
    private int servedCustomers;
    private boolean isCleaning;

    // constructor
    public Restaurant(String n)
    {
        this.name = n;
        this.sem = new Semaphore(5);                     // limit to 5 seats
        this.watch = 0;
        this.cList = new ArrayList<Customer>();
        this.servedCustomers = 0;
        this.isCleaning = false;
    }

    // accessors
    public String getName()
    {
        return this.name;
    }

    public ArrayList<Customer> getArraylist()
    {
        return cList;
    }

    public boolean getCleaningState()
    {
        return this.isCleaning;
    }

    // mutators
    public void setName(String n)
    {
        this.name = n;
    }

    public void setArraylist(ArrayList<Customer> c)
    {
        this.cList = c;
    }

    public void setCleaningState(boolean s)
    {
        this.isCleaning = s;
    }
    
    // methods
    public int availableSeats()
    {
        return this.sem.availablePermits();
    }

    public void allowCustomer()
    {
        try { sem.acquire(); } catch(Exception e) { }
    }

    public void leaveCustomer()
    {
        servedCustomers++;
        sem.release();          // semaphore release
    }

    public int getTime()
    {
        return this.watch;
    }

    public void runSimulation()
    {
        for (Customer customer : cList) 
        {
            customer.start();
        }

        while(this.servedCustomers < cList.size())
        {
            try { Thread.sleep(100); } catch (InterruptedException e) { }

            if(availableSeats() == 0)
            {
                setCleaningState(true);

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

                setCleaningState(false);
            }

            this.watch++;
        }


    }

}