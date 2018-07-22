package boulder_trainings_app.ui;

import boulder_trainings_app.data.Boulder;
import java.util.HashSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 *
 * @author Fabian Rauscher
 */
public interface SelectDependent
{
    public static final ObservableSet<SelectDependent> COMPONENTS = FXCollections.observableSet(new HashSet<SelectDependent>());

    public void selectBoulder(Boulder boulder);
}
