package boulder_trainings_app.data;

import java.io.Serializable;
import org.joda.time.DateTime;

/**
 *
 * @author Fabian
 */
public class BoulderCompletion implements Serializable
{
    private final DateTime date;
    private final int attempts;

    public BoulderCompletion( int attempts, DateTime date)
    {      
        this.attempts = attempts;
        this.date = date;
    } 

    public DateTime getDate()
    {
        return date;
    }

    public int getAttempts()
    {
        return attempts;
    }
}
