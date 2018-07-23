package boulder_trainings_app.controller.interfaces;

import boulder_trainings_app.data.enums.ProgramState;
import java.util.HashSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 *
 * @author Fabian Rauscher
 */
public interface StateDependent
{
    public static final ObservableSet<StateDependent> COMPONENTS = FXCollections.observableSet(new HashSet<StateDependent>());

    public void changeState(ProgramState programState);
}
