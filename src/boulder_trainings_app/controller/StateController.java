package boulder_trainings_app.controller;

import boulder_trainings_app.controller.interfaces.StateDependent;
import boulder_trainings_app.data.enums.ProgramState;
import java.util.logging.Logger;
import javafx.collections.SetChangeListener;

/**
 *
 * @author Fabian Rauscher
 */
public class StateController
{
    private static final Logger LOGGER = Logger.getLogger(StateController.class.getName());

    private static StateController instance;

    private ProgramState programState = ProgramState.SELECT;

    private StateController()
    {
        StateDependent.COMPONENTS.addListener((SetChangeListener.Change<? extends StateDependent> c) ->
        {
            StateDependent d = (StateDependent) c.getElementAdded();
        });
    }

    public static StateController getInstance()
    {
        if (StateController.instance == null)
        {
            StateController.instance = new StateController();
        }
        return StateController.instance;
    }

    public void changeState(ProgramState programState)
    {
        if (this.programState != programState)
        {
            StateDependent.COMPONENTS.forEach((c) -> c.changeState(programState));
            this.programState = programState;
        }
    }
}
