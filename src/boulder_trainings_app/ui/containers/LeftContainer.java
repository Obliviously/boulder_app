/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers;

import boulder_trainings_app.utils.Consts;
import boulder_trainings_app.ui.containers.components.Sections;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Fabian Rauscher
 */
public class LeftContainer extends JPanel
{
    public LeftContainer()
    {
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setPreferredSize(new Dimension(Consts.MIN_WIDTH / 5, 0));
        super.setBorder(new EmptyBorder(5, 5, 5, 0));

        Sections sections = new Sections();

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(sections, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setBorder(null);
        super.add(scrollPane);
    }
}
