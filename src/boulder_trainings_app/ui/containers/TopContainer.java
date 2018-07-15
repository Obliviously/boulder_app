/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers;

import boulder_trainings_app.ui.containers.components.DateSelect;
import boulder_trainings_app.ui.containers.components.LeftSettings;
import boulder_trainings_app.ui.containers.components.RightSettings;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 *
 * @author fabian
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
        RightSettings rightSettings = new RightSettings();
        super.setLayout(gbl);
        c.gridx = 0;
        c.weightx = 0.33;
        super.add(leftSettings, c);
        c.gridx = 1;
        c.weightx = 0.33;
        super.add(dateSelect, c);
        c.gridx = 2;
        c.weightx = 0.33;
        super.add(rightSettings, c);
    }
}
