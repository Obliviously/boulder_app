package boulder_trainings_app.ui.containers.components;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Fabian Rauscher
 */
public class WelcomeDialog extends JDialog
{
    private final JLabel introLabel;
    private final JButton okButton;

    public WelcomeDialog(Frame parent)
    {
        super(parent, "Welcome", true);
        super.setLayout(new GridLayout(1, 1, 50, 50));

        introLabel = new JLabel("<html>Welcome to this awesome app.<br><br>"
                + "This app lets you explore your favorite boulder gym <br>"
                + " and manage your personal progress.</html>");

        okButton = new JButton("Ok");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(introLabel, BorderLayout.CENTER);
        panel.add(okButton, BorderLayout.SOUTH);

        okButton.addActionListener((ActionEvent ae) ->
        {
            WelcomeDialog.this.dispose();
        });
        super.add(panel);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
