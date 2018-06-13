/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app;

import boulder_trainings_app.ui.GraphicalUserInterface;
import boulder_trainings_app.utils.Consts;
import boulder_trainings_app.ui.SwingUserInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

/**
 *
 * @author Fabian Rauscher
 */
public class BoulderApp
{
    private final GraphicalUserInterface userInterface;

    public BoulderApp()
    {
        this.userInterface = new SwingUserInterface();
    }

    public void start()
    {
        userInterface.display(Consts.PROGRAM_NAME, Consts.MIN_WIDTH, Consts.MIN_HEIGHT);
        ApplicationState.getInstance().loadBoulder(DateTime.now());
    }

    public static void main(String[] args)
    {
        BoulderApp app = new BoulderApp();
        app.start();
    }
}
