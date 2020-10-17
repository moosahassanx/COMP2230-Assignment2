// TITLE: 					Assignment2
// COURSE: 					COMP2240
// AUTHOR: 					Moosa Hassan
// STUDENT NUMBER: 			3331532
// DATE: 					17/10/2020 
// DESCRIPTION: 			individually attempts to enter the restaurant, eat and then leave to its given time

public class Customer extends Thread
{
    // attributes
    private String name;
    private final int arrivalTime;
    private final int eatingTime;
    private int seatedTime;
    private int exitTime;
    private final Restaurant restaurant;

    // constructor
    public Customer(final int a, final String n, final int e, final Restaurant r) 
    {
        this.arrivalTime = a;
        this.name = n;
        this.eatingTime = e;
        this.seatedTime = 0;
        this.exitTime = 0;
        this.restaurant = r;
    }

    // accessors
    public String getCusName() { return this.name; }

    public int getArrival() { return this.arrivalTime; }

    public int getEating() { return this.eatingTime; }

    public int getSeated() { return this.seatedTime; }

    public int getExit() { return this.exitTime; }

    // mutators
    public void setCusName(final String n) { this.name = n; }

    public void setSeated(final int s) { this.seatedTime = s; }

    public void setExit(final int e) { this.exitTime = e; }

    // methods
    public void getData() 
    {
        // retrieve data with proper spacing required
        final String strArrival = String.format("%-11d", this.arrivalTime);
        final String strSeated = String.format("%-9d", this.seatedTime);

        // print in console
        System.out.println(this.name + "\t   " + strArrival + strSeated + this.exitTime);
    }

    // thread method
    public void run() 
    {
        try 
        {
            // customer keeps trying to enter the restaurant
            while (true) 
            {
                // customer is not allowed before it has arrived (basic logic)
                if (getArrival() > restaurant.getTime()) 
                {
                    try { sleep(50); } catch (final InterruptedException e) { }
                }
                // attempt to allow the customer into the restaurant
                else 
                {
                    // the restaurant is full or is currently in a cleaning state
                    synchronized (this)
                    {
                        while (restaurant.checkFull() == true || restaurant.getCleaningState() == true) 
                        {
                            restaurant.setNeedToClean(true);
                            wait();
                        }
                    }

                    // allow the customer to enter the restaurant
                    restaurant.allowCustomer(this);
                    break;
                }
            }

            // customer is seated and begins eating immediately
            setSeated(restaurant.getTime());
            for (int i = 0; i < getEating(); i++) 
            {
                try { sleep(100); } catch (final InterruptedException e) { }
            }

            // customer leaves the restaurant
            restaurant.leaveCustomer(this);
            setExit(this.seatedTime + getEating());

        } 
        catch (final Exception e)
        {
            System.out.println("ERROR: " + e);
        }
    }

}
