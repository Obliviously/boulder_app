/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.ApplicationState;
import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.ui.BoulderDependent;
import boulder_trainings_app.ui.SelectDependent;
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
import java.awt.Font;
import javax.swing.UIManager;

/**
 *
 * @author Fabian Rauscher
 */
public class Sections extends JPanel implements SelectDependent, BoulderDependent
{

    private final HashMap<Integer, JList> sections = new HashMap();
    private final HashMap<Integer, JButton> buttons = new HashMap();
    private final HashMap<Integer, JLabel> listSizes = new HashMap();

    public Sections()
    {
        super();

        super.setLayout(new GridBagLayout());
        //create 8 sections
        for (int i = 0; i < 8; i++)
        {
            JPanel section = new JPanel();
            JLabel label = new JLabel("Section: " + (i + 1));
            JLabel listSize = new JLabel(" | (0)");
            listSizes.put(i + 1, listSize);

            JButton toggleButton = new JButton("+");
            toggleButton.setOpaque(false);
            toggleButton.setContentAreaFilled(false);
            toggleButton.setBorderPainted(false);
            toggleButton.setFont(new Font("Arial", Font.BOLD, 15));
            buttons.put(i + 1, toggleButton);

            DefaultListModel listModel = new DefaultListModel<>();
            JList<String> list = new JList<>(listModel);
            list.setBackground(UIManager.getColor("Panel.background"));
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            list.setVisibleRowCount(-1);
            list.setFixedCellHeight(20);
            list.setVisible(false);

            sections.put(i + 1, list);

            section.setLayout(new BorderLayout(0, 0));
            section.add(listSize, BorderLayout.CENTER);
            section.add(label, BorderLayout.LINE_START);
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
                        ApplicationState.getInstance().selectBoulder(ApplicationState.getInstance().getBoulderById(htmlToBoulderId(list.getSelectedValue())));
                    }
                }
            });

            toggleButton.addActionListener((ActionEvent e) ->
            {
                JButton button = (JButton) e.getSource();
                if (!list.isVisible())
                {
                    list.setVisible(true);
                    button.setText("-");
                }
                else
                {
                    list.setVisible(false);
                    button.setText("+");
                }
            });

        }
        SelectDependent.COMPONENTS.add(this);
        BoulderDependent.COMPONENTS.add(this);
    }

    @Override
    public void addBoulder(Boulder boulder)
    {
        JList list = sections.get(boulder.getSection().toInt());
        ((DefaultListModel) list.getModel()).addElement(boulderToHtml(boulder.getName(), boulder.getId()));

        JLabel listSize = listSizes.get(boulder.getSection().toInt());
        listSize.setText(" | (" + (list.getModel().getSize()) + ")");
    }

    @Override
    public void removeBoulder(Boulder boulder)
    {
        JList list = sections.get(boulder.getSection().toInt());
        DefaultListModel listModel = (DefaultListModel) list.getModel();
        listModel.removeElement(boulderToHtml(boulder.getName(), boulder.getId()));

        JLabel listSize = listSizes.get(boulder.getSection().toInt());
        listSize.setText(" | (" + (list.getModel().getSize()) + ")");
    }

    private String boulderToHtml(String name, String id)
    {
        return "<html><span boulder_id='" + id + "'>" + name + "</span></html>";
    }

    private String htmlToBoulderId(String html)
    {
        System.out.println(html);
        html = html.replaceAll("'>.*", "");
        html = html.replaceAll(".*='", "");
        System.out.println(html);
        return html;
    }

    @Override
    public void selectBoulder(Boulder boulder)
    {
        if (boulder != null)
        {
            JList list = sections.get(boulder.getSection().toInt());
            if (!list.isVisible())
            {
                buttons.get(boulder.getSection().toInt()).doClick();
            }
            list.setSelectedValue(boulderToHtml(boulder.getName(), boulder.getId()), true);
        }
        else
        {
            sections.forEach((key, list) -> list.clearSelection());
        }
    }

    @Override
    public void updateBoulder(Boulder boulder)
    {
    }
}
