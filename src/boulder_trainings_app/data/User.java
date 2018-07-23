package boulder_trainings_app.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Fabian Rauscher
 */
public class User implements Serializable
{
    private String name;
    private ArrayList<BoulderStatistic> statistics;

    public User(String name)
    {
        this.name = name;
    }
}
