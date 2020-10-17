public class Customer extends Thread
{
    // attributes
    private String name;
    private int arrivalTime;
    private int eatingTime;
    private int seatedTime;
    private int exitTime;
    private Restaurant restaurant;

    // constructor
    public Customer(int a, String n, int e, Restaurant r)
    {
        this.arrivalTime = a;
        this.name = n;
        this.eatingTime = e;
        this.seatedTime = 0;
        this.exitTime = 0;
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

    public int getSeated()
    {
        return this.seatedTime;
    }

    public int getExit()
    {
        return this.exitTime;
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

    // methods
    public void getData()
    {
        String strArrival = String.format("%-11d", this.arrivalTime);
        String strSeated = String.format("%-9d", this.seatedTime);
        System.out.println(this.name + "\t   " + strArrival  + strSeated  + this.exitTime);
    }

    // thread method
    public void run()
    {
        try
        {
            while(true)
            {
                if(getArrival() > restaurant.getTime())
                {
                    try { sleep(50); } catch (InterruptedException e) { }
                }
                else
                {
                    // the restaurant is full
                    synchronized (this)
                    {
                        while(restaurant.checkFull() == true || restaurant.getCleaningState() == true)
                        {
                            restaurant.setNeedToClean(true);
                            wait();
                        }
                    }

                    restaurant.allowCustomer(this);
                    break;
                }
            }

            setSeated(restaurant.getTime());
            
            for(int i = 0; i < getEating(); i++)
            {
                try { sleep(100); } catch (InterruptedException e) { }
            }

            restaurant.leaveCustomer(this);

            setExit(this.seatedTime + getEating());

        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }
    }

}
