package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.controller.BoulderController;
import boulder_trainings_app.controller.UserController;
import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.BoulderStatistic;
import boulder_trainings_app.ui.utils.DateLabelFormatter;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.joda.time.DateTime;

/**
 *
 * @author Fabian
 */
public class AddCompletionDialog extends JDialog
{
    private final String ATTEMPTSSTRING = "Attempts";
    private final String FLASHEDSTRING = "Flashed";
    private final String ONSIGHTEDSTRING = "Onsighted";
    
    public AddCompletionDialog(Frame parent, Boulder boulder)
    {
        super(parent, "Add Completion", true);
        super.setLayout(new FlowLayout());
        JLabel heading = new JLabel(boulder.getName());

        JRadioButton attemptsButton = new JRadioButton(ATTEMPTSSTRING);
        attemptsButton.setSelected(true);
        JRadioButton flashedButton = new JRadioButton(FLASHEDSTRING);
        JRadioButton onsightedButton = new JRadioButton(ONSIGHTEDSTRING);
        ButtonGroup group = new ButtonGroup();
        group.add(attemptsButton);
        group.add(flashedButton);
        group.add(onsightedButton);

        JLabel dateLabel = new JLabel("Date:");
        UtilDateModel dateModel = new UtilDateModel();
        Properties p = new Properties();
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        JTextField attemptsField = new JTextField();

        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        //You can only flash or onsight at your first completion
        BoulderStatistic bs = UserController.getInstance().getStatistic(boulder.getId());
        if (!bs.isCompleted())
        {
            flashedButton.setEnabled(false);
            onsightedButton.setEnabled(false);
        }

        JPanel panel = new JPanel(new GridLayout(20, 2));

        panel.add(heading);

        
        
        panel.add(attemptsButton);
        panel.add(attemptsField);
        panel.add(flashedButton);
        panel.add(onsightedButton);

        panel.add(dateLabel);

        panel.add(datePicker);

        panel.add(addButton);

        panel.add(cancelButton);

        cancelButton.addActionListener(
                (ActionEvent ae) ->
        {
            AddCompletionDialog.this.dispose();
        }
        );

        addButton.addActionListener(
                new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae
            )
            {
                int attempts = Integer.parseInt(attemptsField.getText());
                boolean flashed = flashedBox.isSelected();
                boolean onsight = onsightBox.isSelected();
                DateTime date = new DateTime(dateModel.getYear(), dateModel.getMonth(), dateModel.getDay(), 0, 0, 0, 0);
                UserController.getInstance().addCompletion(boulder.getId(), attempts, flashed, onsight, date);
                BoulderController.getInstance().updateBoulder(boulder);
                AddCompletionDialog.this.dispose();
            }
        }
        );

        panel.setSize(new Dimension(500, 500));
        panel.setPreferredSize(new Dimension(500, 500));

        super.add(panel);

        this.pack();

        this.setVisible(
                true);
    }
}
