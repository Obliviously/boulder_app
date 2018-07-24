package boulder_trainings_app.data;

import java.io.Serializable;
import java.util.ArrayList;
import org.joda.time.DateTime;

/**
 *
 * @author Fabian Rauscher
 */
public class User implements Serializable
{
    private String name;
    private ArrayList<BoulderCompletion> completions = new ArrayList<>();

    public User(String name)
    {
        this.name = name;
    }

    public void addCompletion(Boulder boulder, int attempts, boolean flashed, boolean onsight, DateTime date)
    {
        BoulderCompletion bc = new BoulderCompletion(boulder.getId(), attempts, flashed, onsight, date);
        completions.add(bc);
    }

    public ArrayList<BoulderCompletion> getCompletions(String boulderId)
    {
        ArrayList<BoulderCompletion> list = new ArrayList<>();
        for (BoulderCompletion bc : completions)
        {
            if (bc.getBoulderID().equals(boulderId))
            {
                list.add(bc);
            }
        }
        return list;
    }
}
