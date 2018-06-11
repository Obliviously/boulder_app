/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.BoulderList;
import boulder_trainings_app.data.Payload;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Fabian Rauscher
 */
public class Sections extends JPanel implements Observer
{

    private HashMap<Integer, JList> sections = new HashMap();
    private HashMap<Integer, JButton> buttons = new HashMap();

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
            toggleButton.setFocusable(false);
            buttons.put(i + 1, toggleButton);

            DefaultListModel listModel = new DefaultListModel<>();
            JList<String> list = new JList<>(listModel);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            list.setVisibleRowCount(-1);
            list.setVisible(false);

            sections.put(i + 1, list);

            GridBagLayout gbl = new GridBagLayout();
            GridBagConstraints c = new GridBagConstraints();
            section.setLayout(gbl);
            c.fill = GridBagConstraints.BOTH;
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

            c.weightx = 1;
            c.gridy = 1;
            c.gridx = 0;
            c.gridwidth = 2;
            section.add(list, c);

            this.add(section);

            list.addListSelectionListener(new ListSelectionListener()
            {
                @Override
                public void valueChanged(ListSelectionEvent e)
                {
                    if (!e.getValueIsAdjusting())
                    {
                        BoulderList.getInstance().selectBoulder(list.getSelectedValue());
                    }
                }
            }
            );

            toggleButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    list.setVisible(!list.isVisible());
                }
            });

            BoulderList.getInstance().addObserver(this);

        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if (o instanceof BoulderList)
        {
            if (arg instanceof Payload)
            {
                Payload payload = (Payload) arg;
                Boulder boulder;
                ArrayList<Boulder> boulderList;
                switch (payload.getState())
                {
                case ADDED_BOULDER:
                    boulder = (Boulder) payload.getData();
                    addBoulder(boulder);

                    break;
                case REMOVED_BOULDER:
                    boulder = (Boulder) payload.getData();
                    removeBoulder(boulder);

                    break;
                case ADDED_BOULDER_LIST:
                    boulderList = (ArrayList<Boulder>) payload.getData();
                    for (Boulder b : boulderList)
                    {
                        addBoulder(b);
                    }
                    break;
                case REMOVED_BOULDER_LIST:
                    boulderList = (ArrayList<Boulder>) payload.getData();

                    for (Boulder b : boulderList)
                    {
                        removeBoulder(b);
                    }
                    break;
                case SELECT_BOULDER:
                    boulder = (Boulder) payload.getData();
                    JList list = sections.get(boulder.getSection().toInt());
                    if (!list.isVisible())
                    {
                        buttons.get(boulder.getSection().toInt()).doClick();
                    }
                    list.setSelectedValue(boulder.getId(), true);
                    break;
                default:
                    break;
                }
            }
        }
    }

    private void addBoulder(Boulder boulder)
    {
        ((DefaultListModel) sections.get(boulder.getSection().toInt()).getModel()).addElement(boulder.getId());
    }

    private void removeBoulder(Boulder boulder)
    {
        DefaultListModel listModel = (DefaultListModel) sections.get(boulder.getSection().toInt()).getModel();
        listModel.removeElement(boulder.getId());
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
