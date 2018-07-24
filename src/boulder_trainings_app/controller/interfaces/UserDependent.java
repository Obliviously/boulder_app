package boulder_trainings_app.controller.interfaces;

import boulder_trainings_app.data.User;
import java.util.HashSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 *
 * @author Fabian Rauscher
 */
public interface UserDependent
{
    public static final ObservableSet<UserDependent> COMPONENTS = FXCollections.observableSet(new HashSet<UserDependent>());

    public void setUser(User user);

    public void updateUser(User user);
}
