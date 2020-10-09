import java.util.concurrent.CountDownLatch;

public class Customer extends Thread {
    // attributes
    private String name;
    private int arrivalTime;
    private int eatingTime;
    private int ogEatingTime;
    private int seatedTime;
    private int exitTime;
    private Restaurant restaurant;
    private int watch;

    // constructor
    public Customer(int a, String n, int e, Restaurant r) {
        this.name = n;
        this.arrivalTime = a;
        this.eatingTime = e;
        this.restaurant = r;
        this.seatedTime = 0;
        this.watch = 0;
        this.ogEatingTime = e;
    }

    // accessors
    public String getCusName() {
        return this.name;
    }

    public int getArrival() {
        return this.arrivalTime;
    }

    public int getEating() {
        return this.eatingTime;
    }

    public int getSeated() {
        return this.seatedTime;
    }

    public int getExit() {
        return this.exitTime;
    }

    // mutators
    public void setCusName(String n) {
        this.name = n;
    }

    public void setSeated(int s) {
        this.seatedTime = s;
    }

    public void setExit(int e) {
        this.exitTime = e;
    }

    // method
    public void eat() {
        // decrement eating time
        this.eatingTime -= 1;
    }

    public void feedTime(int w)
    {
        this.watch = w;
    }
    
    public void getData()
    {
        System.out.println(this.name + "\t   " + this.arrivalTime + "\t      " + this.seatedTime + "\t       " + this.exitTime);
    }

    public void run()
    {
        // sleep thread
        try { Thread.sleep(1); } catch (InterruptedException e) { }

        System.out.println(this.name + " is waiting to go in the restaurant");

        restaurant.allowCustomer(getArrival());
        
        System.out.println(this.name + " has entered the restaurant");

        while (getEating() != 0)
        {
            eat();
            System.out.println(this.name + " took a bite");
        }

        System.out.println(this.name + " has finished eating");

        restaurant.leaveCustomer();
    }
}

// start() - starts the thread
// getState() - returns state of thread (e.g. New, Runnable, Running, Waiting, Terminated)
// getName() - returns name of thread
// getPriority() - returns priority of the thread
// sleep() - stop the thread for a period of time
// join() - stop the current thread until the called thread gets terminated
// isAlive() - check if the threads still alive