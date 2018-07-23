package boulder_trainings_app.controller;

import boulder_trainings_app.controller.interfaces.SelectionDependent;
import boulder_trainings_app.data.Boulder;
import java.util.logging.Logger;
import javafx.collections.SetChangeListener;

/**
 *
 * @author Fabian Rauscher
 */
public class SelectionController
{
    private static final Logger LOGGER = Logger.getLogger(SelectionController.class.getName());

    private static SelectionController instance;

    private Boulder selectedBoulder = null;

    private SelectionController()
    {
        SelectionDependent.COMPONENTS.addListener((SetChangeListener.Change<? extends SelectionDependent> c) ->
        {
            SelectionDependent d = (SelectionDependent) c.getElementAdded();
        });
    }

    public static SelectionController getInstance()
    {
        if (SelectionController.instance == null)
        {
            SelectionController.instance = new SelectionController();
        }
        return SelectionController.instance;
    }

    public void selectBoulder(Boulder boulder)
    {
        SelectionDependent.COMPONENTS.forEach(c -> c.selectBoulder(boulder));
        selectedBoulder = boulder;
    }

    public Boulder getSelectedBoulder()
    {
        return selectedBoulder;
    }

}
