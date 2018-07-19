/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.ApplicationState;
import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.utils.Consts;
import boulder_trainings_app.data.enums.BoulderColor;
import boulder_trainings_app.data.enums.BoulderGrade;
import boulder_trainings_app.data.enums.BoulderType;
import boulder_trainings_app.data.enums.BoulderSection;
import boulder_trainings_app.data.enums.ProgramState;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.joda.time.DateTime;
import boulder_trainings_app.ui.StateDependent;
import boulder_trainings_app.ui.utils.DateLabelFormatter;

/**
 *
 * @author Fabian Rauscher
 */
public class BoulderEdit extends JPanel implements StateDependent
{
    private Boulder boulder;

    private final JTextField nameTextField;
    private final UtilDateModel dateModel;
    private final JDatePanelImpl datePanel;
    private final JDatePickerImpl datePicker;
    private final JComboBox colorComboBox;
    private final JComboBox typeComboBox;
    private final JComboBox gradeComboBox;
    private final JComboBox sectionComboBox;
    private final JButton saveButton;

    public BoulderEdit()
    {
        super();
        super.setLayout(new BorderLayout());
        JPanel propertiesContainer = new JPanel(new GridLayout(20, 2));

        JLabel nameLabel = new JLabel(Consts.BOULDER_NAME_LABEL);
        nameTextField = new JTextField();

        JLabel dateLabel = new JLabel(Consts.BOULDER_DATE_LABEL);
        dateModel = new UtilDateModel();
        dateModel.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(dateModel, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        JLabel colorLabel = new JLabel(Consts.BOULDER_COLOR_LABEL);
        colorComboBox = new JComboBox(BoulderColor.values());

        JLabel typeLabel = new JLabel(Consts.BOULDER_TYPE_LABEL);
        typeComboBox = new JComboBox(BoulderType.values());

        JLabel gradeLabel = new JLabel(Consts.BOULDER_GRADE_LABEL);
        gradeComboBox = new JComboBox(BoulderGrade.values());

        JLabel sectionLabel = new JLabel(Consts.BOULDER_SECTION_LABEL);
        sectionComboBox = new JComboBox(BoulderSection.values());

        saveButton = new JButton(Consts.BOULDER_SAVE_BUTTON);

        propertiesContainer.add(nameLabel);
        propertiesContainer.add(nameTextField);
        propertiesContainer.add(dateLabel);
        propertiesContainer.add(datePicker);
        propertiesContainer.add(colorLabel);
        propertiesContainer.add(colorComboBox);
        propertiesContainer.add(typeLabel);
        propertiesContainer.add(typeComboBox);
        propertiesContainer.add(gradeLabel);
        propertiesContainer.add(gradeComboBox);
        propertiesContainer.add(sectionLabel);
        propertiesContainer.add(sectionComboBox);

        super.add(propertiesContainer, BorderLayout.CENTER);
        super.add(saveButton, BorderLayout.SOUTH);

        setViewEnabled(false);

        nameTextField.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                if (boulder != null)
                {
                    updateName();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                if (boulder != null)
                {
                    updateName();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {
                if (boulder != null)
                {
                    updateName();
                }
            }

        });

        dateModel.addChangeListener((ChangeEvent e) ->
        {
            if (boulder != null)
            {
                updateDate();
            }
        });
        colorComboBox.addActionListener((ActionEvent e) ->
        {
            if (boulder != null)
            {
                updateColor();
            }
        });
        typeComboBox.addActionListener((ActionEvent e) ->
        {
            if (boulder != null)
            {
                updateType();
            }
        });
        gradeComboBox.addActionListener((ActionEvent e) ->
        {
            if (boulder != null)
            {
                updateGrade();
            }
        });
        sectionComboBox.addActionListener((ActionEvent e) ->
        {
            if (boulder != null)
            {
                updateSection();
            }
        });

        saveButton.addActionListener((ActionEvent e) ->
        {
            if (boulder != null)
            {
                ApplicationState.getInstance().saveBoulder(boulder);
            }
        });

        COMPONENTS.add(this);
    }

    @Override
    public void updateBoulder(Boulder boulder)
    {
    }

    private void updateName()
    {
        System.out.println("boulder_trainings_app.ui.containers.components.BoulderEdit.updateName()");
        boulder.setName(nameTextField.getText());
    }

    private void updateDate()
    {
        DateTime date = new DateTime(dateModel.getYear(), dateModel.getMonth(), dateModel.getDay(), 0, 0, 0, 0);
        boulder.setDate(date);
    }

    private void updateColor()
    {
        boulder.setColor((BoulderColor) colorComboBox.getSelectedItem());
    }

    private void updateGrade()
    {
        boulder.setGrade((BoulderGrade) gradeComboBox.getSelectedItem());

    }

    private void updateSection()
    {
        boulder.setSection((BoulderSection) sectionComboBox.getSelectedItem());

    }

    private void updateType()
    {
        boulder.setType((BoulderType) typeComboBox.getSelectedItem());

    }

    @Override
    public void addBoulder(Boulder boulder)
    {
    }

    @Override
    public void removeBoulder(Boulder boulder)
    {
    }

    @Override
    public void highLightBoulder(Boulder boulder)
    {
    }

    @Override
    public void selectBoulder(Boulder boulder)
    {
        setViewEnabled(true);
        this.boulder = boulder;
        initBoulder();
    }

    @Override
    public void deselect()
    {
        boulder = null;
        setViewEnabled(false);
        nameTextField.setText("");
        dateModel.setDate(0, 0, 0);
        colorComboBox.setSelectedItem(null);
        typeComboBox.setSelectedItem(null);
        gradeComboBox.setSelectedItem(null);
        sectionComboBox.setSelectedItem(null);
    }

    private void setViewEnabled(boolean enabled)
    {
        nameTextField.setEnabled(enabled);
        datePanel.setEnabled(enabled);
        datePicker.setEnabled(enabled);
        colorComboBox.setEnabled(enabled);
        typeComboBox.setEnabled(enabled);
        gradeComboBox.setEnabled(enabled);
        sectionComboBox.setEnabled(enabled);
    }

    @Override
    public void editBoulder(Boulder boulder)
    {
        setViewEnabled(true);
        this.boulder = boulder;
        initBoulder();
    }

    @Override
    public void changeState(ProgramState programState)
    {
    }

    private void initBoulder()
    {
        nameTextField.setText(boulder.getName());
        DateTime date = boulder.getDate();
        dateModel.setDate(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth());
        colorComboBox.setSelectedItem(boulder.getColor());
        typeComboBox.setSelectedItem(boulder.getType());
        gradeComboBox.setSelectedItem(boulder.getGrade());
        sectionComboBox.setSelectedItem(boulder.getSection());
    }
}
