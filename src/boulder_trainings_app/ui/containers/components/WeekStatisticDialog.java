package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.controller.BoulderController;
import boulder_trainings_app.controller.UserController;
import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.BoulderStatistic;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Fabian Rauscher
 */
public class WeekStatisticDialog extends JDialog
{

    JLabel completedValue;
    JLabel flashedValue;
    JLabel onsightedValue;

    public WeekStatisticDialog(Frame parent)
    {
        super(parent, "Statistics", true);
        super.setLayout(new BorderLayout());

        JLabel headline = new JLabel("Statistics for:");
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd.MMMM,yyyy");
        JLabel dateValue = new JLabel(fmt.print(BoulderController.getInstance().getDate()));

        JLabel completedLabel = new JLabel("Completed");
        completedValue = new JLabel();
        JLabel flashedLabel = new JLabel("Flashed");
        flashedValue = new JLabel();
        JLabel onsightedLabel = new JLabel("Onsighted");
        onsightedValue = new JLabel();
        JButton okButton = new JButton("CLOSE");

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(headline);
        panel.add(dateValue);
        panel.add(completedLabel);
        panel.add(completedValue);
        panel.add(flashedLabel);
        panel.add(flashedValue);
        panel.add(onsightedLabel);
        panel.add(onsightedValue);

        okButton.addActionListener((ActionEvent ae) ->
        {
            this.dispose();
        });

        calculateStatistics();
        super.add(panel, BorderLayout.CENTER);
        super.add(okButton, BorderLayout.SOUTH);

        super.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    private void calculateStatistics()
    {
        Set<Boulder> boulderSet = BoulderController.getInstance().getBoulder();
        UserController userController = UserController.getInstance();
        BoulderStatistic boulderStatistic;
        int boulderCount = boulderSet.size();
        int completedCounter = 0;
        int flashedCounter = 0;
        int onsightedCounter = 0;

        for (Boulder boulder : boulderSet)
        {
            boulderStatistic = userController.getStatistic(boulder.getId());
            if (boulderStatistic != null && boulderStatistic.isCompleted())
            {
                completedCounter++;
                if (boulderStatistic.flashed())
                {
                    flashedCounter++;
                }
                if (boulderStatistic.onsighted())
                {
                    onsightedCounter++;
                }
            }
        }
        completedValue.setText(completedCounter + "/" + boulderCount);
        flashedValue.setText(flashedCounter + "/" + completedCounter);
        onsightedValue.setText(onsightedCounter + "/" + completedCounter);
    }
}
