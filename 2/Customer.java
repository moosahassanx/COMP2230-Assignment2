public class Customer extends Thread
{
    // attributes
    private String name;
    private int arrivalTime;
    private int eatingTime;
    private Restaurant restaurant;

    // constructor
    public Customer(int a, String n, int e, Restaurant r)
    {
        this.name = n;
        this.arrivalTime = a;
        this.eatingTime = e;
        this.restaurant = r;
    }

    // accessors
    public String getCusName()
    {
        return this.name;
    }
    public int getArrival()
    {
        return this.arrivalTime;
    }
    public int getEating()
    {
        return this.eatingTime;
    }

    // mutators
    public void setCusName(String n)
    {
        this.name = n;
    }

    // method
    public void eat()
    {
        // decrement eating time
        this.eatingTime -= 1;
    }

    public void run()
    {
        eat();
    }
}

// start() - starts the thread
// getState() - returns state of thread (e.g. New, Runnable, Running, Waiting, Terminated)
// getName() - returns name of thread
// getPriority() - returns priority of the thread
// sleep() - stop the thread for a period of time
// join() - stop the current thread until the called thread gets terminated
// isAlive() - check if the threads still alive