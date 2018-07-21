package boulder_trainings_app.ui;

import boulder_trainings_app.data.Boulder;
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

    public void addBoulder(Boulder boulder);

    public void removeBoulder(Boulder boulder);

    public void selectBoulder(Boulder boulder);   

    public void editBoulder(Boulder boulder);

    public void changeState(ProgramState programState);

    public void updateBoulder(Boulder boulder);
}
