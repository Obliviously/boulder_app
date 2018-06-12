/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.Const;
import boulder_trainings_app.data.Payload;
import boulder_trainings_app.data.ProgramData;
import boulder_trainings_app.data.enums.BoulderColor;
import boulder_trainings_app.data.enums.BoulderGrade;
import boulder_trainings_app.data.enums.BoulderType;
import boulder_trainings_app.data.enums.BoulderSection;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
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

/**
 *
 * @author Fabian Rauscher
 */
public class BoulderEdit extends JPanel implements Observer
{
    private Boulder boulder;

    private final JTextField nameTextField;
    private final UtilDateModel dateModel;
    private final JComboBox colorComboBox;
    private final JComboBox typeComboBox;
    private final JComboBox gradeComboBox;
    private final JComboBox sectionComboBox;

    public BoulderEdit()
    {
        super();

        super.setLayout(new GridLayout(0, 2));

        ProgramData.getInstance().addObserver(this);

        JLabel nameLabel = new JLabel(Const.BOULDER_NAME_LABEL);
        nameTextField = new JTextField();

        JLabel dateLabel = new JLabel(Const.BOULDER_DATE_LABEL);
        dateModel = new UtilDateModel();
        Properties p = new Properties();
//        p.put("text.today", "Today");
//        p.put("text.month", "Month");
//        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        JLabel colorLabel = new JLabel(Const.BOULDER_COLOR_LABEL);
        colorComboBox = new JComboBox(BoulderColor.values());

        JLabel typeLabel = new JLabel(Const.BOULDER_TYPE_LABEL);
        typeComboBox = new JComboBox(BoulderType.values());

        JLabel gradeLabel = new JLabel(Const.BOULDER_GRADE_LABEL);
        gradeComboBox = new JComboBox(BoulderGrade.values());

        JLabel sectionLabel = new JLabel(Const.BOULDER_SECTION_LABEL);
        sectionComboBox = new JComboBox(BoulderSection.values());

        super.add(nameLabel);
        super.add(nameTextField);
        super.add(dateLabel);
        super.add(datePicker);
        super.add(colorLabel);
        super.add(colorComboBox);
        super.add(typeLabel);
        super.add(typeComboBox);
        super.add(gradeLabel);
        super.add(gradeComboBox);
        super.add(sectionLabel);
        super.add(sectionComboBox);

        nameTextField.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                updateName();
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                updateName();
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {
                updateName();
            }

        });

        dateModel.addChangeListener((ChangeEvent e) ->
        {
            updateDate();
        });
        colorComboBox.addActionListener((ActionEvent e) ->
        {
            updateColor();
        });
        typeComboBox.addActionListener((ActionEvent e) ->
        {
            updateType();
        });
        gradeComboBox.addActionListener((ActionEvent e) ->
        {
            updateGrade();
        });
        sectionComboBox.addActionListener((ActionEvent e) ->
        {
            updateSection();
        });

    }

    private void updateName()
    {
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
    public void update(Observable o, Object arg)
    {
        if (o instanceof ProgramData)
        {
            if (arg instanceof Payload)
            {
                Payload payload = (Payload) arg;
                switch (payload.getState())
                {
                case EDIT_BOULDER:
                    boulder = (Boulder) payload.getData();
                    initBoulder();
                    break;

                default:
                    break;
                }
            }
        }
    }

    private void initBoulder()
    {
        nameTextField.setText(boulder.getName());
        DateTime date = boulder.getDate();
        dateModel.setDate(date.getYear(), date.getMonthOfYear(), date.getDayOfMonth());
    }

    private class DateLabelFormatter extends AbstractFormatter
    {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException
        {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException
        {
            if (value != null)
            {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }
    }
}
