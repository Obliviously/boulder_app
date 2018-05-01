/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

/**
 *
 * @author fabian
 */
public class Middle extends JPanel
{
    public Middle()
    {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gbl);
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.weighty = 1.0;
        c.insets = new Insets(0, 3, 0, 3);
        
        c.weightx = 0.25;
        c.gridx = 0;
        Sections sections = new Sections();
        this.add(sections, c);

        c.weightx = 0.5;
        c.gridx = 1;
        View3d view3d = new View3d();
        this.add(view3d, c);
        
        c.weightx = 0.25;
        c.gridx = 2;
        Statistics statistics = new Statistics();
        this.add(statistics, c);
    }

}
