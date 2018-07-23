package boulder_trainings_app;

import boulder_trainings_app.controller.BoulderController;
import boulder_trainings_app.controller.UserController;
import boulder_trainings_app.ui.GraphicalUserInterface;
import boulder_trainings_app.utils.Consts;
import boulder_trainings_app.ui.SwingUserInterface;
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
        BoulderController.getInstance().loadBoulder(DateTime.now());
        if (!UserController.getInstance().loadUser())
        {
            System.out.println("boulder_trainings_app.BoulderApp.start()");
            userInterface.createUser();
        }
    }

    public static void main(String[] args)
    {
        BoulderApp app = new BoulderApp();
        app.start();
    }
}
