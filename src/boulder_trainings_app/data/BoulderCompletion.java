package boulder_trainings_app.data;

import java.io.Serializable;
import org.joda.time.DateTime;

/**
 *
 * @author Fabian
 */
public class BoulderCompletion implements Serializable
{
    private final String boulderID;
    private final DateTime date;
    private final int attempts;
    private final boolean flashed;
    private final boolean onsight;

    public BoulderCompletion(String boulderID, int attempts, boolean flashed, boolean onsight, DateTime date)
    {
        this.boulderID = boulderID;
        this.attempts = attempts;
        this.flashed = flashed;
        this.onsight = onsight;
        this.date = date;
    }

    public String getBoulderID()
    {
        return boulderID;
    }

    public DateTime getDate()
    {
        return date;
    }

    public int getAttempts()
    {
        return attempts;
    }

    public boolean isFlashed()
    {
        return flashed;
    }

    public boolean isOnsight()
    {
        return onsight;
    }

}
