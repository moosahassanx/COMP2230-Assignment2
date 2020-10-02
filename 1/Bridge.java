import java.util.concurrent.Semaphore;

public class Bridge
{
    private int crossed;
    private Semaphore bridgeSem;

    // constructor
    public Bridge()
    {
        crossed = 0;
        bridgeSem = new Semaphore(1, true);       // 1 bridge resource
    }

    // accessors
    public int getCrossed()
    {
        return crossed;
    }

    // mutators
    public void cross()
    {
        // semaphore acquire
        try
        {
            bridgeSem.acquire();
            crossed++;
        }
        catch(Exception e)
        {
            System.out.println("ERROR: " + e);
        }
    }

    public void exit()
    {
        // semaphore release
        bridgeSem.release();
    }
}