package boulder_trainings_app.ui.containers.components;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author Fabian Rauscher
 */
public class WeekStatisticDialog extends JDialog
{
    JButton okButton;

    public WeekStatisticDialog(Frame parent)
    {
        super(parent, "Statistics", true);
        super.setLayout(new FlowLayout());

        okButton = new JButton("Ok");
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(okButton);

        okButton.addActionListener((ActionEvent ae) ->
        {
            this.dispose();
        });

        super.add(panel);
        super.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
}
