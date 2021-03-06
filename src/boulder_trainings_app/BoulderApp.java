package boulder_trainings_app;

import boulder_trainings_app.controller.BoulderController;
import boulder_trainings_app.controller.StateController;
import boulder_trainings_app.controller.UserController;
import boulder_trainings_app.data.User;
import boulder_trainings_app.data.enums.ProgramState;
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
        StateController.getInstance().changeState(ProgramState.SELECT);

        if (!UserController.getInstance().loadUser())
        {
            System.out.println("boulder_trainings_app.BoulderApp.start()");
            userInterface.showWelcomeMessage();
            UserController.getInstance().setUser(new User());
        }
    }

    public static void main(String[] args)
    {
        BoulderApp app = new BoulderApp();
        app.start();
    }
}
