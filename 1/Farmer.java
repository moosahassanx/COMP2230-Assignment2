// TITLE: 					Assignment2
// COURSE: 					COMP2240
// AUTHOR: 					Moosa Hassan
// STUDENT NUMBER: 			3331532
// DATE: 					17/10/2020 
// DESCRIPTION: 			individually attempt to cross the bridge amongst its peer farmers (threads)

public class Farmer extends Thread
{
    // attributes
    private String name;
    private String island;
    private String direction;
    private final Bridge cookStrait;

    // constructor
    public Farmer(final String n, final String l, final Bridge b) 
    {
        this.name = n;
        this.island = l;
        updateDirection();
        this.cookStrait = b;
    }

    // mutators
    public void setFarmerName(final String n) 
    {
        this.name = n;
    }

    public void setIsland(final String l) 
    {
        this.island = l;
    }

    public void setDirection(final String d) 
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
        if (this.island.equals("North")) 
        {
            this.direction = "South";
        }
        // farmer is on SOUTH island
        else 
        {
            this.direction = "North";
        }
    }

    public void updateIsland() 
    {
        // farmer was on NORTH island
        if (this.island.equals("North")) 
        {
            this.island = "South";
        } 
        // farmer was on SOUTH island
        else 
        {
            this.island = "North";
        }
    }

    public void run() 
    {
        // infinitely run
        while (true) 
        {
            System.out.println(name + ": Waiting for bridge. Going towards " + direction);

            // attempt to cross the bridge
            cookStrait.cross();

            // take a step
            System.out.println(this.name + ": Crossing bridge Step 5.");
            try { Thread.sleep(200); } catch (final Exception e) { }

            // take a step
            System.out.println(this.name + ": Crossing bridge Step 10.");
            try { Thread.sleep(200); } catch (final Exception e) { }

            // take a step
            System.out.println(this.name + ": Crossing bridge Step 15.");
            try { Thread.sleep(200); } catch (final Exception e) { }

            // announce details
            System.out.println(this.name + ": Across the bridge.");
            System.out.println("NEON: " + cookStrait.getCrossed());

            // update details after crossing bridge
            updateIsland();
            updateDirection();

            cookStrait.exit();
        }
    }
    
}