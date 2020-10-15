import javax.xml.stream.util.StreamReaderDelegate;

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
        String strArrival = String.format("%-11d", this.arrivalTime);
        String strSeated = String.format("%-9d", this.seatedTime);
        System.out.println(this.name + "\t   " + strArrival  + strSeated  + this.exitTime);
    }

    @Override
    public void run()
    {
        // customer keeps trying to enter the restaurant
        while(true)
        {
            if(getArrival() > restaurant.getTime())
            {
                try { sleep(50); } catch (InterruptedException e) { }
            }
            else
            {
                while(restaurant.getCleaningState() == true)
                {
                    try { sleep(10); } catch (InterruptedException e) { }
                }

                restaurant.allowCustomer();
                break;
            }
            
        }

        setSeated(restaurant.getTime());
        for(int i = 0; i < getEating(); i++)
        {
            try { sleep(100); } catch (InterruptedException e) { }
        }

        restaurant.leaveCustomer();
        setExit(seatedTime + getEating());
        
    }
}