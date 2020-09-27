import java.util.*;

public class Farmer implements Runnable
{
    // attributes
    private String name;
    private int time;
    private Random r = new Random();

    // constructor
    public Farmer(String n)
    {
        this.name = n;
        this.time = r.nextInt(999);
    }

    // mutators
    public void setName(String n)
    {
        this.name = n;
    }

    // accessors
    public String getName()
    {
        return this.name;
    }

    public void run()
    {
        try
        {
            System.out.printf("%f is sleeping for %d\n", name, time);
            Thread.sleep(time);
            System.out.printf("%s is done \n", name);
        }
        catch(Exception e){}
    }
    
}