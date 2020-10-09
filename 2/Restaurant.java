import java.util.concurrent.Semaphore;

public class Restaurant
{
    // attributes
    private String name;
    private Semaphore sem;
    private int watch;

    // constructor
    public Restaurant(String n)
    {
        this.name = n;
        sem = new Semaphore(5);                     // limit to 5 seats
        watch = 0;
    }

    // accessors
    public String getName()
    {
        return this.name;
    }

    // mutators
    public void setName(String n)
    {
        this.name = n;
    }
    
    // methods
    public int availableSeats()
    {
        return this.sem.availablePermits();
    }

    // CONDITIONS MUST BE MET:
    // - cputime == arrivalTime
    // - customer id must be in order
    public void allowCustomer(int aTime)
    {
        // customers are always trying to enter
        while(true)
        {
            // if(aTime == watch)
            // {
                // semaphore acquire
                try
                {
                    sem.acquire();
                    return;
                }
                catch(Exception e) { }
            // }
        }
    }

    public void leaveCustomer()
    {
        // semaphore release
        sem.release();
    }

}