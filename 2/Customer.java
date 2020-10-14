public class Customer extends Thread
{
    // attributes
    private String name;
    private int arrivalTime;
    private int eatingTime;
    private int seatedTime;
    private int exitTime;
    private Restaurant restaurant;
    private int watch;
    private boolean isDone;
    private String state;

    // constructor
    public Customer(int a, String n, int e, Restaurant r) {
        this.name = n;
        this.arrivalTime = a;
        this.eatingTime = e;
        this.restaurant = r;
        this.seatedTime = 0;
        this.watch = 0;
        this.isDone = false;
        this.state = "";
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

    public int getSeated() 
    {
        return this.seatedTime;
    }

    public int getExit() 
    {
        return this.exitTime;
    }

    public boolean leftRestaurant()
    {
        return this.isDone;
    }

    public int getTime()
    {
        return this.watch;
    }

    public String getDoing()
    {
        return this.state;
    }

    // mutators
    public void setCusName(String n) 
    {
        this.name = n;
    }

    public void setSeated(int s) 
    {
        this.seatedTime = s;
    }

    public void setExit(int e) 
    {
        this.exitTime = e;
    }

    public void hasLeft(boolean r)
    {
        this.isDone = r;
    }

    public void setTime(int w)
    {
        this.watch = w;
    }

    public void whatDoing(String s)
    {
        this.state = s;
    }

    // method
    public void eat() 
    {
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

    @Override
    public void run()
    {
        System.out.println("AVAILABLE SEATS PRE RUN(): " + restaurant.availableSeats());

        System.out.println(this.name + " is waiting to go in the restaurant at time: " + this.watch);

        // customer keeps trying to enter the restaurant
        while(true)
        {
            if(getArrival() > restaurant.getTime())
            {
                System.out.println(this.name + " could not enter restaurant at time: " + restaurant.getTime());
                try { sleep(50); } catch (InterruptedException e) { }
            }
            else
            {
                while(restaurant.getCleaningState() == true)
                {
                    System.out.println(this.name + " is waiting for restaurant to finish cleaning at time: " + restaurant.getTime());
                    try { sleep(10); } catch (InterruptedException e) { }
                }

                restaurant.allowCustomer();
                System.out.println(this.name + " has entered the restaurant at time: " + restaurant.getTime());
                break;
            }
            
        }

        setSeated(restaurant.getTime());
        
        for(int i = 0; i < getEating(); i++)
        {
            System.out.println(this.name + " took a bite at time " + restaurant.getTime());
            try { sleep(100); } catch (InterruptedException e) { }
        }

        restaurant.leaveCustomer();
        setExit(seatedTime + getEating());
        System.out.println(this.name + " has left the restaurant. AVAILABLE SEATS: " + restaurant.availableSeats());
        
    }
}