package boulder_trainings_app.controller.interfaces;

import boulder_trainings_app.data.Boulder;
import java.util.HashSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 *
 * @author Fabian Rauscher
 */
public interface BoulderDependent
{
    public static final ObservableSet<BoulderDependent> COMPONENTS = FXCollections.observableSet(new HashSet<BoulderDependent>());

    public void addBoulder(Boulder boulder);

    public void removeBoulder(Boulder boulder);

    public void updateBoulder(Boulder boulder);
}
