package boulder_trainings_app.ui;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.enums.ProgramState;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Fabian Rauscher
 */
public interface StateDependent
{

    public static final ObservableList<StateDependent> COMPONENTS = FXCollections.observableList(new ArrayList<StateDependent>());

    public void addBoulder(Boulder boulder);

    public void removeBoulder(Boulder boulder);

    public void highLightBoulder(Boulder boulder);

    public void selectBoulder(Boulder boulder);

    public void editBoulder(Boulder boulder);

    public void changeState(ProgramState programState);
}
