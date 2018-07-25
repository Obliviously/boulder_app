package boulder_trainings_app.ui.containers;

import boulder_trainings_app.ui.containers.components.DateSelect;
import boulder_trainings_app.ui.containers.components.LeftSettings;
import boulder_trainings_app.ui.containers.components.ModeSelection;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author Fabian Rauscher
 */
public class TopContainer extends JPanel 
{
    public TopContainer()
    {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        c.ipadx = 0;
        c.ipady = 0;
        DateSelect dateSelect = new DateSelect();
        LeftSettings leftSettings = new LeftSettings();
        ModeSelection modeSelection = new ModeSelection();
        super.setLayout(gbl);
        c.gridx = 0;
        c.weightx = 0.3;
        super.add(leftSettings, c);
        c.gridx = 1;
        c.weightx = 0.4;
        super.add(modeSelection, c);
        c.gridx = 2;
        c.weightx = 0.2;
        super.add(dateSelect, c);
    }
}
