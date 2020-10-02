import java.util.*;

public class Farmer extends Thread
{
    // attributes
    private String name;
    private String island;
    private String direction;
    private Bridge cookStrait;

    // constructor
    public Farmer(String n, String l, String d, Bridge b) 
    {
        this.name = n;
        this.island = l;
        updateDirection();
        this.cookStrait = b;
        System.out.println(name + ": Waiting for bridge. Going towards " + direction);
    }

    // mutators
    public void setFarmerName(String n)
    {
        this.name = n;
    }
    public void setIsland(String l)
    {
        this.island = l;
    }
    public void setDirection(String d)
    {
        this.direction = d;
    }

    // accessors
    public String getFarmerName()
    {
        return this.name;
    }
    public String getIsland()
    {
        return this.island;
    }
    public String getDirection()
    {
        return this.direction;
    }

    // methods
    public void updateDirection()
    {
        // farmer is on NORTH island
        if(this.island.equals("North"))
        {
            this.direction = "South";
        }
        // farmer is on SOUTH island
        else
        {
            this.direction = "North";
        }
    }

    public void run()
    {
        try
        {
            //
        }
        catch(Exception e){}
    }
    
}