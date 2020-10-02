public class Farmer extends Thread
{
    // attributes
    private String name;
    private String island;
    private String direction;
    private Bridge cookStrait;

    // constructor
    public Farmer(String n, String l, Bridge b) 
    {
        this.name = n;
        this.island = l;
        updateDirection();
        this.cookStrait = b;
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
    public void updateIsland()
    {
        if(this.island.equals("North"))
        {
            this.island = "South";
        }
        else
        {
            this.island = "North";
        }
    }
    
    public void run()
    {
        // infinitely run
        while(true)
        {
            System.out.println(name + ": Waiting for bridge. Going towards " + direction);

            cookStrait.cross();

            System.out.println(this.name + ": Crossing bridge Step 5.");
            // sleep for 200 ms
            try
            {
                Thread.sleep(200);
            }
            catch(Exception e){ }
            
            System.out.println(this.name + ": Crossing bridge Step 10.");
            // sleep for 200 ms
            try
            {
                Thread.sleep(200);
            }
            catch(Exception e){ }

            System.out.println(this.name + ": Crossing bridge Step 15.");
            // sleep for 200 ms
            try
            {
                Thread.sleep(200);
            }
            catch(Exception e){ }


            System.out.println(this.name + ": Across the bridge.");
            System.out.println("NEON: " + cookStrait.getCrossed());

            updateIsland();
            updateDirection();

            cookStrait.exit();
        }
    }
    
}