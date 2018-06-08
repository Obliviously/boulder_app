/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.components;

import boulder_trainings_app.BoulderManager;
import boulder_trainings_app.data.Boulder;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

/**
 *
 * @author fabian
 */
public class Sections extends JPanel implements Observer
{
    public Sections()
    {
        super();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //create 8 sections
        for (int i = 0; i < 8; i++)
        {
            JPanel section = new JPanel();
            JLabel label = new JLabel("Section: " + (i + 1));
            JButton toggleButton = new JButton("+");
            JList<Boulder> list = new JList<>();
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            list.setVisibleRowCount(-1);

            GridBagLayout gbl = new GridBagLayout();
            GridBagConstraints c = new GridBagConstraints();
            section.setLayout(gbl);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0;
            c.ipadx = 0;
            c.gridy = 0;
            c.weighty = 1.0;

            c.weightx = 0.75;
            c.gridx = 0;
            section.add(label, c);

            c.weightx = 0.25;
            c.gridx = 1;
            section.add(toggleButton, c);

            c.weightx = 1.0;
            c.gridy = 1;
            c.gridx = 0;
            section.add(list, c);

            this.add(section);

            BoulderManager.getInstance().getBoulderList().addObserver(this);

        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        
    }

    protected JComponent makeTextPanel(String text)
    {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        // filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
}
