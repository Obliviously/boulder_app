package boulder_trainings_app.controller;

import boulder_trainings_app.UserFileManager;
import boulder_trainings_app.controller.interfaces.StateDependent;
import boulder_trainings_app.controller.interfaces.UserDependent;
import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.BoulderCompletion;
import boulder_trainings_app.data.User;
import java.util.ArrayList;
import java.util.logging.Logger;
import javafx.collections.SetChangeListener;
import org.joda.time.DateTime;

/**
 *
 * @author Fabian Rauscher
 */
public class UserController
{
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    private static UserController instance;

    private User user;

    private UserController()
    {
        UserDependent.COMPONENTS.addListener((SetChangeListener.Change<? extends UserDependent> c) ->
        {
            UserDependent d = (UserDependent) c.getElementAdded();
        });
    }

    public static UserController getInstance()
    {
        if (UserController.instance == null)
        {
            UserController.instance = new UserController();
        }
        return UserController.instance;
    }

    public boolean loadUser()
    {
        User user = UserFileManager.loadUser();
        if (user != null)
        {
            this.user = user;
            UserDependent.COMPONENTS.forEach((c) -> c.setUser(user));
            return true;
        }
        return false;
    }

    public void setUser(User user)
    {
        if (user != null)
        {
            this.user = user;
            UserFileManager.saveUser(user);
            UserDependent.COMPONENTS.forEach((c) -> c.setUser(user));
        }
    }

    public void addCompletion(Boulder boulder, int attempts, boolean flashed, boolean onsight, DateTime date)
    {
        user.addCompletion(boulder, attempts, flashed, onsight, date);
        UserDependent.COMPONENTS.forEach((c) -> c.updateUser(user));

        UserFileManager.saveUser(user);
    }

    public ArrayList<BoulderCompletion> getCompletions(String boulderId)
    {
        return user.getCompletions(boulderId);
    }
}
