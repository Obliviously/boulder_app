package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.ui.utils.DateLabelFormatter;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Fabian
 */
public class AddCompletionDialog extends JDialog
{
    private JLabel heading;
    private JLabel attemptsLabel;
    private JTextField attemptsField;
    private JLabel flashedLabel;
    private JCheckBox flashedBox;
    private JLabel onsightLabel;
    private JCheckBox onsightBox;

    private JLabel dateLabel;
    private final UtilDateModel dateModel;
    private final JDatePanelImpl datePanel;
    private final JDatePickerImpl datePicker;

    private JButton addButton;
    private JButton cancelButton;

    public AddCompletionDialog(Frame parent, String name)
    {
        super(parent, "Add Completion", true);
        super.setLayout(new FlowLayout());
        heading = new JLabel(name);

        attemptsLabel = new JLabel("Attempts:");
        attemptsField = new JTextField();

        flashedLabel = new JLabel("Flashed:");
        flashedBox = new JCheckBox();

        onsightLabel = new JLabel("Onsight:");
        onsightBox = new JCheckBox();

        dateLabel = new JLabel("Date:");
        dateModel = new UtilDateModel();
        dateModel.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(dateModel, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");

        JPanel panel = new JPanel(new GridLayout(20, 2));
        panel.add(heading);
        panel.add(attemptsLabel);
        panel.add(attemptsField);
        panel.add(flashedLabel);
        panel.add(flashedBox);
        panel.add(onsightLabel);
        panel.add(onsightBox);
        panel.add(dateLabel);
        panel.add(datePicker);
        panel.add(addButton);
        panel.add(cancelButton);

        cancelButton.addActionListener((ActionEvent ae) ->
        {
            AddCompletionDialog.this.dispose();
        });
        
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                
            }
        });

        panel.setSize(new Dimension(500, 500));
        panel.setPreferredSize(new Dimension(500, 500));

        super.add(panel);
        this.pack();
        this.setVisible(true);
    }
}
