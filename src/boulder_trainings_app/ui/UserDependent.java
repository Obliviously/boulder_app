package boulder_trainings_app.ui;

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

}
