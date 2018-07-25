/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.ui.utils.UIUtilities;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        super.setLayout(new GridLayout(1, 1, 50, 50));
        weekStatistics = new JButton("Week Statistic");
        super.add(weekStatistics);

        weekStatistics.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                Frame parentFrame = UIUtilities.getParentFrame(LeftSettings.this);
                new WeekStatisticDialog(parentFrame);
            }
        });
    }
}
