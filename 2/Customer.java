public class Customer extends Thread
{
    // attributes
    private String name;
    private int arrivalTime;
    private int eatingTime;

    // constructor
    public Customer(int a, String n, int e)
    {
        this.name = n;
        this.arrivalTime = a;
        this.eatingTime = e;
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
}
