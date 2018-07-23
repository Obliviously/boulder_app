package boulder_trainings_app.controller;

import boulder_trainings_app.UserFileManager;
import boulder_trainings_app.controller.interfaces.UserDependent;
import boulder_trainings_app.data.User;
import java.util.logging.Logger;
import javafx.collections.SetChangeListener;

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
}
