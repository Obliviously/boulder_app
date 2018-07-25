package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.controller.StateController;
import boulder_trainings_app.controller.interfaces.StateDependent;
import boulder_trainings_app.data.enums.ProgramState;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Fabian Rauscher
 */
public class ModeSelection extends JPanel implements StateDependent
{
    JButton selectButton;
    JButton editButton;
    JButton createButton;

    public ModeSelection()
    {
        super();
        super.setLayout(new GridLayout());
        selectButton = new JButton("SELECT");
        editButton = new JButton("EDIT");
        createButton = new JButton("CREATE");

        super.add(selectButton);
        super.add(editButton);
        super.add(createButton);

        selectButton.addActionListener((ActionEvent ae) ->
        {
            StateController.getInstance().changeState(ProgramState.SELECT);
        });
        editButton.addActionListener((ActionEvent ae) ->
        {
            StateController.getInstance().changeState(ProgramState.EDIT);
        });
        createButton.addActionListener((ActionEvent ae) ->
        {
            StateController.getInstance().changeState(ProgramState.CREATE);
        });

        StateDependent.COMPONENTS.add(this);
    }

    @Override
    public void changeState(ProgramState programState)
    {
        switch (programState)
        {
        case SELECT:
            selectButton.setEnabled(false);
            editButton.setEnabled(true);
            createButton.setEnabled(true);
            break;
        case EDIT:
            selectButton.setEnabled(true);
            editButton.setEnabled(false);
            createButton.setEnabled(true);
            break;
        case CREATE:
            selectButton.setEnabled(true);
            editButton.setEnabled(true);
            createButton.setEnabled(false);
            break;
        default:
            break;
        }
    }
}
