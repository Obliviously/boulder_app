package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.controller.BoulderController;
import boulder_trainings_app.ui.utils.DateLabelFormatter;
import java.awt.GridLayout;
import java.util.Properties;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.joda.time.DateTime;

/**
 *
 * @author Fabian Rauscher
 */
public class DateSelect extends JPanel
{
    private final UtilDateModel dateModel;
    private final JDatePanelImpl datePanel;
    private final JDatePickerImpl datePicker;
    private final JLabel headline;

    public DateSelect()
    {
        super();
        super.setLayout(new GridLayout(2, 1));

        headline = new JLabel("SELECT GYM STATE AT:");
        headline.setHorizontalAlignment(JLabel.CENTER);
        super.add(headline);
        dateModel = new UtilDateModel();
        dateModel.setSelected(true);
        Properties p = new Properties();
        datePanel = new JDatePanelImpl(dateModel, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.getJFormattedTextField().setHorizontalAlignment(JTextField.CENTER);
        super.add(datePicker);

        dateModel.addChangeListener((ChangeEvent e) ->
        {
            DateTime date = new DateTime(dateModel.getYear(), dateModel.getMonth() + 1, dateModel.getDay(), 0, 0, 0, 0);
            BoulderController.getInstance().loadBoulder(date);
        });
    }
}
