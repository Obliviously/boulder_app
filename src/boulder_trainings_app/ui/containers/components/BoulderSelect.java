package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.controller.UserController;
import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.utils.Consts;
import javax.swing.JLabel;
import javax.swing.JPanel;
import boulder_trainings_app.ui.utils.UIUtilities;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import boulder_trainings_app.controller.interfaces.SelectionDependent;
import boulder_trainings_app.controller.interfaces.UserDependent;
import boulder_trainings_app.data.BoulderCompletion;
import boulder_trainings_app.data.BoulderStatistic;
import boulder_trainings_app.data.User;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Fabian Rauscher
 */
public class BoulderSelect extends JPanel implements SelectionDependent, UserDependent
{
    private final JPanel propertiesContainer;

    private final JLabel nameValue;
    private final JLabel dateValue;
    private final JLabel colorValue;
    private final JLabel typeValue;
    private final JLabel gradeValue;
    private final JLabel sectionValue;
    private final JButton addCompletion;
    private final String[] tableColumnNames =
    {
        "Date",
        "Attempts"
    };

    private final JTable completionsTable;
    private final JScrollPane tableScrollPane;
    private final JLabel nothingSelectedMessage;
    private final JLabel flashOnsightLabel;

    private Boulder boulder = null;

    private Frame parentFrame;

    public BoulderSelect()
    {
        super();
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel nameLabel = new JLabel(Consts.BOULDER_NAME_LABEL);
        nameValue = new JLabel();
        JLabel dateLabel = new JLabel(Consts.BOULDER_DATE_LABEL);
        dateValue = new JLabel();
        JLabel colorLabel = new JLabel(Consts.BOULDER_COLOR_LABEL);
        colorValue = new JLabel();
        JLabel typeLabel = new JLabel(Consts.BOULDER_TYPE_LABEL);
        typeValue = new JLabel();
        JLabel gradeLabel = new JLabel(Consts.BOULDER_GRADE_LABEL);
        gradeValue = new JLabel();
        JLabel sectionLabel = new JLabel(Consts.BOULDER_SECTION_LABEL);
        sectionValue = new JLabel();
        flashOnsightLabel = new JLabel();

        TableModel tableModel = new DefaultTableModel(tableColumnNames, 0);
        completionsTable = new JTable(tableModel);
        completionsTable.setEnabled(false);
        Dimension tableDim = new Dimension(250, 100);

        tableScrollPane = new JScrollPane(completionsTable);
        tableScrollPane.setSize(tableDim);
        tableScrollPane.setPreferredSize(tableDim);

        addCompletion = new JButton("Add Completion");

        addCompletion.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                parentFrame = UIUtilities.getParentFrame(BoulderSelect.this);
                new AddCompletionDialog(parentFrame, boulder);
            }
        });

        nothingSelectedMessage = new JLabel(Consts.NOTHING_SELECTED_MESSAGE);
        nothingSelectedMessage.setFont(new Font("Arial", Font.BOLD, 20));
        nothingSelectedMessage.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        super.add(nothingSelectedMessage);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 1;

        propertiesContainer = new JPanel(gbl);

        c.gridy = 0;
        c.gridx = 0;
        propertiesContainer.add(nameLabel, c);
        c.gridx = 1;
        propertiesContainer.add(nameValue, c);

        c.gridy = 1;
        c.gridx = 0;
        propertiesContainer.add(dateLabel, c);
        c.gridx = 1;
        propertiesContainer.add(dateValue, c);

        c.gridy = 2;
        c.gridx = 0;
        propertiesContainer.add(colorLabel, c);
        c.gridx = 1;
        propertiesContainer.add(colorValue, c);

        c.gridy = 3;
        c.gridx = 0;
        propertiesContainer.add(typeLabel, c);
        c.gridx = 1;
        propertiesContainer.add(typeValue, c);

        c.gridy = 4;
        c.gridx = 0;
        propertiesContainer.add(gradeLabel, c);
        c.gridx = 1;
        propertiesContainer.add(gradeValue, c);

        c.gridy = 5;
        c.gridx = 0;
        propertiesContainer.add(sectionLabel, c);
        c.gridx = 1;
        propertiesContainer.add(sectionValue, c);

        c.gridy = 6;
        c.gridx = 0;
        c.gridwidth = 2;
        propertiesContainer.add(flashOnsightLabel,c);

        c.gridy = 7;
        c.gridx = 0;
        c.gridwidth = 2;
        propertiesContainer.add(addCompletion, c);

        c.gridy = 8;
        c.gridx = 0;
        c.gridwidth = 10;
        propertiesContainer.add(tableScrollPane, c);

        SelectionDependent.COMPONENTS.add(this);
        UserDependent.COMPONENTS.add(this);
    }

    @Override
    public void selectBoulder(Boulder boulder)
    {
        if (boulder != this.boulder)
        {
            this.removeAll();
            if (boulder != null)
            {
                this.add(propertiesContainer);
                this.boulder = boulder;
                initValues();
            }
            else
            {
                this.boulder = null;
                this.add(nothingSelectedMessage);
            }
            this.repaint();
            this.validate();
        }
    }

    @Override
    public void setUser(User user)
    {
    }

    @Override
    public void updateUser(User user)
    {
        initValues();
    }

    private void initValues()
    {
        nameValue.setText(boulder.getName());
        dateValue.setText(boulder.getDate().toString());
        colorValue.setText(boulder.getColor().toString());
        typeValue.setText(boulder.getType().toString());
        gradeValue.setText(boulder.getGrade().toString());
        sectionValue.setText(boulder.getSection().toString());

        //Update completions list
        BoulderStatistic bs = UserController.getInstance().getStatistic(boulder.getId());
        if (bs != null)
        {
            if (bs.flashed())
            {
                flashOnsightLabel.setText("FLASHED");
                if (bs.onsighted())
                {
                    flashOnsightLabel.setText("ONSIGHTED");
                }
            }
            else
            {
                flashOnsightLabel.setText("NOT FLASHED OR ONSIGHTED");
            }

            ArrayList<BoulderCompletion> completions = bs.getCompletions();
            DefaultTableModel model = (DefaultTableModel) completionsTable.getModel();
            model.setRowCount(completions.size());

            for (int i = 0; i < completions.size(); i++)
            {
                BoulderCompletion bd = completions.get(i);
                model.setValueAt(bd.getDate(), i, 0);
                model.setValueAt(bd.getAttempts(), i, 1);
            }
        }

    }
}
