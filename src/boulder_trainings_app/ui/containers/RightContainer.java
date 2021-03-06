package boulder_trainings_app.ui.containers;

import boulder_trainings_app.utils.Consts;
import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.ui.containers.components.BoulderEdit;
import boulder_trainings_app.ui.containers.components.BoulderSelect;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import boulder_trainings_app.controller.interfaces.StateDependent;
import static boulder_trainings_app.controller.interfaces.StateDependent.COMPONENTS;

/**
 *
 * @author Fabian Rauscher
 */
public class RightContainer extends JPanel implements StateDependent
{
    private final BoulderSelect boulderSelect;
    private final BoulderEdit boulderEdit;

    public RightContainer()
    {
        super();
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setPreferredSize(new Dimension(Consts.MIN_WIDTH / 5, HEIGHT));
        super.setBorder(new EmptyBorder(5, 0, 5, 5));

        boulderSelect = new BoulderSelect();
        boulderEdit = new BoulderEdit();

        super.add(boulderSelect);

        COMPONENTS.add(this);
    }

    @Override
    public void changeState(ProgramState programState)
    {
        this.removeAll();
        switch (programState)
        {
        case SELECT:
            this.add(boulderSelect);
            break;
        case EDIT:
            this.add(boulderEdit);
            break;
        case CREATE:
            this.add(boulderEdit);
            break;
        default:
            break;
        }
        this.repaint();
        this.validate();
    }
}
