package boulder_trainings_app.controller.interfaces;

import boulder_trainings_app.data.Boulder;
import java.util.HashSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 *
 * @author Fabian Rauscher
 */
public interface SelectionDependent
{
    public static final ObservableSet<SelectionDependent> COMPONENTS = FXCollections.observableSet(new HashSet<SelectionDependent>());

    public void selectBoulder(Boulder boulder);
}
