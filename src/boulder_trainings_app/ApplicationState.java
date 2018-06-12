/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.utils.Payload;
import boulder_trainings_app.data.enums.BoulderSection;
import boulder_trainings_app.data.enums.ProgramState;
import java.util.Observable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabian Rauscher
 */
public class ApplicationState extends Observable
{
    private static final Logger LOGGER = Logger.getLogger(ApplicationState.class.getName());

    private final ArrayList<Boulder> boulderList = new ArrayList<>();
    private static ApplicationState instance;
    private ProgramState programState = ProgramState.SELECT;

    private ApplicationState()
    {
    }

    public static ApplicationState getInstance()
    {
        if (ApplicationState.instance == null)
        {
            ApplicationState.instance = new ApplicationState();
        }
        return ApplicationState.instance;
    }

    public void changeStateTo(ProgramState programState)
    {
        if (this.programState != programState)
        {
            setChanged();
            this.programState = programState;
            notifyObservers(new Payload(Payload.State.PROGRAM_STATE_CHANGED, programState));
        }

    }

    public void editBoulder(Boulder boulder)
    {
        setChanged();
        notifyObservers(new Payload(Payload.State.EDIT_BOULDER, boulder));
    }

    public void saveBoulder(Boulder boulder)
    {
        setChanged();
        BoulderManager.saveBoulder(boulder);
        notifyObservers(new Payload(Payload.State.SAVE_BOULDER, boulder));
    }

    public ArrayList<Boulder> getBoulderList()
    {
        return new ArrayList<>(boulderList);
    }

    public synchronized void addBoulder(Boulder boulder)
    {
        setChanged();
        boulderList.add(boulder);
        notifyObservers(new Payload(Payload.State.ADDED_BOULDER, boulder));
    }

    public synchronized void addBoulders(ArrayList<Boulder> boulders)
    {
        setChanged();
        LOGGER.log(Level.INFO, "Added {0} boulders!", boulders.size());
        boulderList.addAll(boulders);
        notifyObservers(new Payload(Payload.State.ADDED_BOULDER_LIST, boulders));
    }

    public synchronized void removeSection(BoulderSection section)
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

    public synchronized void selectBoulder(String boulderId)
    {
        Boulder boulder;
        if ((boulder = getBoulderById(boulderId)) != null)
        {
            setChanged();
            notifyObservers(new Payload(Payload.State.SELECT_BOULDER, boulder));
        }
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
