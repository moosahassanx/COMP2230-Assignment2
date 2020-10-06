public class Customer 
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
    public String getName()
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
    public void setName(String n)
    {
        this.name = n;
    }
}
