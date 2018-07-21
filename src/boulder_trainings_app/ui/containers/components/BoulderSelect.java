package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.utils.Consts;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import boulder_trainings_app.ui.StateDependent;
import java.awt.BorderLayout;
import java.awt.Font;

/**
 *
 * @author fabian
 */
public class BoulderSelect extends JPanel implements StateDependent

{
    private final JPanel properties;
    private final JTextField nameTextField;

    private final JLabel nothingSelectedMessage;
    private boolean nothingSelected = true;

    public BoulderSelect()
    {
        super();
        COMPONENTS.add(this);

        super.setLayout(new BorderLayout());

        nothingSelectedMessage = new JLabel(Consts.NOTHING_SELECTED_MESSAGE);
        nothingSelectedMessage.setFont(new Font("Arial", Font.BOLD, 20));
        nothingSelectedMessage.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        properties = new JPanel();
        properties.setLayout(new GridLayout(0, 2));

        JLabel nameLabel = new JLabel(Consts.BOULDER_NAME_LABEL);
        JLabel colorLabel = new JLabel(Consts.BOULDER_COLOR_LABEL);
        JLabel sectionLabel = new JLabel(Consts.BOULDER_SECTION_LABEL);
        JLabel dateLabel = new JLabel(Consts.BOULDER_DATE_LABEL);
        properties.add(nameLabel);
        properties.add(colorLabel);
        properties.add(sectionLabel);
        properties.add(dateLabel);

        nameTextField = new JTextField();
        nameTextField.setEnabled(false);
        properties.add(nameTextField);

        super.add(nothingSelectedMessage, BorderLayout.CENTER);
    }

    @Override
    public void selectBoulder(Boulder boulder)
    {
        if (nothingSelected)
        {
            this.remove(nothingSelectedMessage);
            this.add(properties);
            this.repaint();
            this.validate();
            nothingSelected = false;
        }
        nameTextField.setBackground(boulder.getColor().toColor());
        nameTextField.setText(boulder.getId());
    }

    @Override
    public void addBoulder(Boulder boulder)
    {
    }

    @Override
    public void removeBoulder(Boulder boulder)
    {
    }

    @Override
    public void highLightBoulder(Boulder boulder)
    {
    }

    @Override
    public void editBoulder(Boulder boulder)
    {
    }

    @Override
    public void changeState(ProgramState programState)
    {
    }

    @Override
    public void deselect()
    {
        if (!nothingSelected)
        {
            this.remove(properties);
            this.add(nothingSelectedMessage);
            this.repaint();
            this.validate();
            nothingSelected = true;
        }
    }

    @Override
    public void updateBoulder(Boulder boulder)
    {
    }
}
