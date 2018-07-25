package boulder_trainings_app.data;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Fabian Rauscher
 */
public class User implements Serializable
{
    private final HashMap<String, BoulderStatistic> statistics;

    public User()
    {
        this.statistics = new HashMap<>();
    }

    public void addCompletion(String boulderID, BoulderCompletion bc)
    {
        if (statistics.containsKey(boulderID))
        {
            statistics.get(boulderID).addCompletion(bc);
        }
        else
        {
            BoulderStatistic bs = new BoulderStatistic();
            bs.addCompletion(bc);
            statistics.put(boulderID, bs);
        }
    }

    public BoulderStatistic getBoulderStatistic(String boulderID)
    {
        if (statistics.containsKey(boulderID))
        {
            return statistics.get(boulderID);
        }
        BoulderStatistic bs = new BoulderStatistic();
        statistics.put(boulderID, bs);
        return bs;
    }
}
