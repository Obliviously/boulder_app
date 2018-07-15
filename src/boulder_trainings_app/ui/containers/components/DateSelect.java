/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.ApplicationState;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Fabian Rauscher
 */
public class DateSelect extends JPanel
{
    private final UtilDateModel dateModel;
    private final JDatePanelImpl datePanel;
    private final JDatePickerImpl datePicker;

    public DateSelect()
    {
        super();
        super.setLayout(new BorderLayout());
        JButton dateButton = new JButton("Date...");
        super.add(dateButton);

        dateModel = new UtilDateModel();
        dateModel.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(dateModel, p);
        datePicker = new JDatePickerImpl(datePanel, new BoulderEdit.DateLabelFormatter());

        dateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            }
        });
    }
}
