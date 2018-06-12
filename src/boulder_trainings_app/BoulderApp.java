/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.ui.GraphicalUserInterface;
import boulder_trainings_app.utils.Consts;
import boulder_trainings_app.ui.SwingUserInterface;
import boulder_trainings_app.utils.Payload;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import org.joda.time.DateTime;

/**
 *
 * @author Fabian Rauscher
 */
public class BoulderApp implements Observer
{
    private final GraphicalUserInterface userInterface;
    private final ApplicationState applicationState;

    public BoulderApp()
    {
        this.userInterface = new SwingUserInterface();
        this.applicationState = new ApplicationState();
    }

    public void start()
    {
        userInterface.display(Consts.PROGRAM_NAME, Consts.MIN_WIDTH, Consts.MIN_HEIGHT);
        BoulderManager.loadBoulder(DateTime.now());

        applicationState.addObserver(this);

    }

    public static void main(String[] args)
    {
        BoulderApp app = new BoulderApp();
        app.start();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if (o instanceof ApplicationState)
        {
            if (arg instanceof Payload)
            {
                Payload payload = (Payload) arg;
                Boulder boulder;
                ArrayList<Boulder> boulderList;
                switch (payload.getState())
                {
                case ADDED_BOULDER:
                    boulder = (Boulder) payload.getData();
                    userInterface.addBoulder(boulder);

                    break;
                case REMOVED_BOULDER:
                    boulder = (Boulder) payload.getData();
                    userInterface.removeBoulder(boulder);

                    break;
                case ADDED_BOULDER_LIST:
                    boulderList = (ArrayList<Boulder>) payload.getData();
                    boulderList.forEach((b) -> userInterface.addBoulder(b));
                    break;
                case REMOVED_BOULDER_LIST:
                    boulderList = (ArrayList<Boulder>) payload.getData();
                    boulderList.forEach((b) -> userInterface.removeBoulder(b));
                    break;

                case HIGHLIGHT_BOULDER:
                    boulder = (Boulder) payload.getData();
                    userInterface.highLightBoulder(boulder);
                    break;

                case SELECT_BOULDER:
                    boulder = (Boulder) payload.getData();
                    userInterface.selectBoulder(boulder);
                    break;
                default:
                    break;
                }
            }
        }
    }
}
