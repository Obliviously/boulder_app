package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.controller.UserController;
import boulder_trainings_app.data.User;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Fabian Rauscher
 */
public class CreateUserDialog extends JDialog
{
    private final JLabel heading;
    private final JLabel nameLabel;
    private final JTextField nameField;
    private final JButton addButton;
    private final JButton cancelButton;

    public CreateUserDialog(Frame parent)
    {
        super(parent, "Create User", true);
        super.setLayout(new FlowLayout());
        heading = new JLabel("Hello! Whats your name?");

        nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        addButton = new JButton("Create");
        cancelButton = new JButton("Exit");

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(heading);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(addButton);
        panel.add(cancelButton);

        cancelButton.addActionListener((ActionEvent ae) ->
        {
            System.exit(0);
        });

        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String name = nameField.getText();
                if (!name.isEmpty())
                {
                    User user = new User(name);
                    UserController.getInstance().setUser(user);
                    CreateUserDialog.this.dispose();
                }
            }
        });
        super.add(panel);
        super.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        super.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
}
