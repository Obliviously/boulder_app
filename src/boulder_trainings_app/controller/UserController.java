package boulder_trainings_app.controller;

import boulder_trainings_app.UserFileManager;
import boulder_trainings_app.controller.interfaces.UserDependent;
import boulder_trainings_app.data.BoulderCompletion;
import boulder_trainings_app.data.BoulderStatistic;
import boulder_trainings_app.data.User;
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

    public void saveUser()
    {
        UserFileManager.saveUser(user);
    }

    public void addCompletion(String boulderID, int attempts, boolean flashed, boolean onsight, DateTime date)
    {
        BoulderStatistic bs = user.getBoulderStatistic(boulderID);
        bs.addCompletion(new BoulderCompletion(attempts, date));
        bs.setFlashed(flashed);
        bs.setOnsighted(onsight);
    }

    public void updateUser()
    {
        UserDependent.COMPONENTS.forEach((c) -> c.updateUser(user));
    }

    public void setFlashed(String boulderID, boolean flashed)
    {
        BoulderStatistic bs = user.getBoulderStatistic(boulderID);
        if (bs != null)
        {
            bs.setFlashed(flashed);
        }
    }

    public void setOnsighted(String boulderID, boolean onsighted)
    {
        BoulderStatistic bs = user.getBoulderStatistic(boulderID);
        if (bs != null)
        {
            bs.setOnsighted(onsighted);
        }
    }

    public BoulderStatistic getStatistic(String boulderId)
    {
        return user.getBoulderStatistic(boulderId);
    }
}
