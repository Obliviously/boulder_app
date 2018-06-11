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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

    public BoulderEdit()
    {
        super();

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

        this.add(nameLabel);
        this.add(nameTextField);
        this.add(dateLabel);
        this.add(datePicker);

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
