/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers.components;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Fabian Rauscher
 */
public class LeftSettings extends JPanel
{
    JButton weekStatistics;

    public LeftSettings()
    {
        super();
        super.setLayout(new GridLayout());
        weekStatistics = new JButton("Weekly Statistic");
        super.add(weekStatistics);
    }
}
