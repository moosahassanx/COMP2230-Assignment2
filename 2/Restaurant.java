import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Restaurant
{
    // attributes
    private String name;
    private Semaphore sem;
    private Queue<Customer> q;
    private int watch;

    // constructor
    public Restaurant(String n)
    {
        this.name = n;
        sem = new Semaphore(5);             // limit to 5 seats
        q = new LinkedList<Customer>();
        watch = 0;                          // keep track of time
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

    public void allowCustomer(Customer c) throws InterruptedException
    {
        System.out.println("Customer " + c.getCusName() + " has entered.");
        
        sem.acquire();
        
        c.run();        // run code for customer

        System.out.println("Available seats: " + availableSeats() + "\n");

        // customer will eat the food here and then need to leave
        // when restaurant is full, the algorithm continues until theyre all done eating
        // next wave of customers come in (using waitCustomer() method)
        // ^ repeat this process
    }
    
    public void waitCustomer(Customer c)
    {
        // add customer to linked list
        q.add(c);
        System.out.println("Customer " + c.getCusName() + " is in waiting queue.");

        System.out.println("Available seats: " + availableSeats() + "\n");
    }
}