// TITLE: 					Assignment2
// COURSE: 					COMP2240
// AUTHOR: 					Moosa Hassan
// STUDENT NUMBER: 			3331532
// DATE: 					17/10/2020 
// DESCRIPTION: 			semaphore-based class: used to limit farmers attempting to run other parts of their code

// importing java libraries
import java.util.concurrent.Semaphore;

public class Bridge
{
    // attributes
    private int crossed;
    private final Semaphore bridgeSem;

    // constructor
    public Bridge() 
    {
        crossed = 0;
        bridgeSem = new Semaphore(1, true); // 1 bridge resource
    }

    // accessors
    public int getCrossed() 
    {
        return crossed;
    }

    // methods
    public void cross() 
    {
        // semaphore acquire
        try 
        {
            bridgeSem.acquire();
            crossed++;
        }
        catch (final Exception e)
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