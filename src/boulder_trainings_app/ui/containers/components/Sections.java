/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.ApplicationState;
import boulder_trainings_app.utils.Payload;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.paint.Color;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author Fabian Rauscher
 */
public class Sections extends JPanel implements Observer
{

    private final HashMap<Integer, JList> sections = new HashMap();
    private final HashMap<Integer, JButton> buttons = new HashMap();

    public Sections()
    {
        super();

        super.setLayout(new GridBagLayout());
        //create 8 sections
        for (int i = 0; i < 8; i++)
        {
            JPanel section = new JPanel();
            JLabel label = new JLabel("Section: " + (i + 1));
            JButton toggleButton = new JButton("+");
            buttons.put(i + 1, toggleButton);

            DefaultListModel listModel = new DefaultListModel<>();
            JList<String> list = new JList<>(listModel);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            list.setVisibleRowCount(-1);
            list.setFixedCellHeight(20);       
            list.setVisible(false);

            sections.put(i + 1, list);

            section.setLayout(new BorderLayout(0, 0));
            section.add(label, BorderLayout.CENTER);
            section.add(toggleButton, BorderLayout.LINE_END);
            section.add(list, BorderLayout.SOUTH);

            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = i;
            c.weightx = 1.0;
            c.weighty = 0.0;
            c.ipady = 10;
            c.ipadx = 0;
            c.anchor = GridBagConstraints.NORTHWEST;
            c.fill = GridBagConstraints.HORIZONTAL;

            super.add(section, c);

            list.addListSelectionListener((ListSelectionEvent e) ->
            {
                if (!e.getValueIsAdjusting())
                {
                    //ignore deselecting items
                    if (list.getSelectedValue() != null)
                    {
                        //deselect all other lists
                        sections.forEach((key, otherList) ->
                        {
                            if (!otherList.equals(e.getSource()))
                            {
                                otherList.clearSelection();
                            }
                        });
                        ApplicationState.getInstance().selectBoulder(list.getSelectedValue());
                    }
                }
            });

            toggleButton.addActionListener((ActionEvent e) ->
            {
                list.setVisible(!list.isVisible());
            });

        }

        ApplicationState boulderList = ApplicationState.getInstance();
        boulderList.addObserver(this);
        for (Boulder b : boulderList.getBoulderList())
        {
            addBoulder(b);
        }
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if (o instanceof ApplicationState)
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
