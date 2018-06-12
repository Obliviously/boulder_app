/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.ApplicationState;
import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.ui.StateChanged;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author Fabian Rauscher
 */
public class Sections extends JPanel implements StateChanged
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
    }

    public void addBoulder(Boulder boulder)
    {
        ((DefaultListModel) sections.get(boulder.getSection().toInt()).getModel()).addElement(boulder.getId());
    }

    public void removeBoulder(Boulder boulder)
    {
        DefaultListModel listModel = (DefaultListModel) sections.get(boulder.getSection().toInt()).getModel();
        listModel.removeElement(boulder.getId());
    }

    @Override
    public void highLightBoulder(Boulder boulder)
    {
    }

    @Override
    public void selectBoulder(Boulder boulder)
    {
        JList list = sections.get(boulder.getSection().toInt());
        if (!list.isVisible())
        {
            buttons.get(boulder.getSection().toInt()).doClick();
        }
        list.setSelectedValue(boulder.getId(), true);
    }

    @Override
    public void editBoulder(Boulder boulder)
    {
    }

    @Override
    public void saveBoulder(Boulder boulder)
    {
    }

    @Override
    public void changeState(ProgramState programState)
    {
    }
}
