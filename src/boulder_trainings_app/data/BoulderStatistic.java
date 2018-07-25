package boulder_trainings_app.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Fabian Rauscher
 */
public class BoulderStatistic implements Serializable
{
    private boolean flashed;
    private boolean onsighted;
    private final ArrayList<BoulderCompletion> completions;

    public BoulderStatistic()
    {
        this.flashed = false;
        this.onsighted = false;
        this.completions = new ArrayList<>();
    }
    
    public boolean isCompleted(){
        return !completions.isEmpty();
    }

    public void addCompletion(BoulderCompletion bc)
    {
        completions.add(bc);
    }

    public ArrayList<BoulderCompletion> getCompletions()
    {
        return completions;
    }

    public boolean onsighted()
    {
        return this.onsighted;
    }

    public boolean flashed()
    {
        return this.flashed;
    }

    public void setFlashed(boolean flashed)
    {
        //if you didnt flash you cant have onsighted
        if (!flashed && this.onsighted)
        {
            this.onsighted = false;
        }
        this.flashed = flashed;
    }

    public void setOnsighted(boolean onsighted)
    {
        //you cant onsight without flashing
        if (onsighted)
        {
            this.flashed = true;
        }
        this.onsighted = onsighted;
    }

}
