/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.data;

import java.util.Observable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabian Rauscher
 */
public class BoulderList extends Observable
{
    private static final Logger LOGGER = Logger.getLogger(BoulderList.class.getName());

    private final ArrayList<Boulder> boulderList = new ArrayList<>();
    private String highlightedBoulder = null;

    public synchronized void addBoulder(Boulder boulder)
    {
        setChanged();
        boulderList.add(boulder);
        notifyObservers(new Payload(Payload.State.ADDED_BOULDER, boulder));
    }

    public synchronized void addBoulders(ArrayList<Boulder> boulders)
    {
        setChanged();
        LOGGER.log(Level.INFO, "Added " + boulders.size() + " boulders!");
        boulderList.addAll(boulders);
        notifyObservers(new Payload(Payload.State.ADDED_BOULDER_LIST, boulders));
    }

    public synchronized void removeSection(Section section)
    {
        setChanged();
        ArrayList<Boulder> removedBoulders = new ArrayList<Boulder>();
        for (Boulder boulder : boulderList)
        {
            if (boulder.getSection().equals(section))
            {
                boulderList.remove(boulder);
                removedBoulders.add(boulder);
            }
        }
        notifyObservers(new Payload(Payload.State.REMOVED_BOULDER_LIST, removedBoulders));
    }

    public Boulder getBoulderById(String boulderId)
    {
        for (Boulder b : boulderList)
        {
            if (b.getId().equals(boulderId))
            {
                return b;
            }
        }
        return null;
    }

    public synchronized void highLightBoulder(String boulderId)
    {
        Boulder boulder = getBoulderById(boulderId);
        if (boulder != null && !boulder.isHighlighted())
        {
            setChanged();
            boulder.setHighlighted(true);
            notifyObservers(new Payload(Payload.State.HIGHLIGHT_BOULDER, boulderId));
        }
    }
}
