package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.ApplicationState;
import boulder_trainings_app.ui.utils.DateLabelFormatter;
import java.awt.BorderLayout;
import java.util.Properties;
import javax.swing.JPanel;
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

    public DateSelect()
    {
        super();
        super.setLayout(new BorderLayout());
        dateModel = new UtilDateModel();
        dateModel.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(dateModel, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setSize(datePanel.getSize());
        super.add(datePicker);

        dateModel.addChangeListener((ChangeEvent e) ->
        {
            DateTime date = new DateTime(dateModel.getYear(), dateModel.getMonth() + 1, dateModel.getDay(), 0, 0, 0, 0);
            ApplicationState.getInstance().loadBoulder(date);
        });
    }
}
