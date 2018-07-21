package boulder_trainings_app.ui.containers;

import boulder_trainings_app.ui.containers.components.Info;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author Fabian Rauscher
 */
public class BottomContainer extends JPanel
{
    public BottomContainer()
    {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.ipadx = 0;
        c.ipady = 0;
        Info info = new Info();
        super.setLayout(gbl);
        super.add(info, c);
    }
}
