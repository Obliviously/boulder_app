/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.ApplicationState;
import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.utils.Consts;
import boulder_trainings_app.utils.Payload;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import boulder_trainings_app.ui.StateDependent;

/**
 *
 * @author fabian
 */
public class BoulderSelect extends JPanel implements StateDependent
{
    private final JTextField nameTextField;

    public BoulderSelect()
    {
        super();
        super.setLayout(new GridLayout(10, 2));

        JLabel nameLabel = new JLabel(Consts.BOULDER_NAME_LABEL);
        JLabel colorLabel = new JLabel(Consts.BOULDER_COLOR_LABEL);
        JLabel sectionLabel = new JLabel(Consts.BOULDER_SECTION_LABEL);
        JLabel dateLabel = new JLabel(Consts.BOULDER_DATE_LABEL);

        nameTextField = new JTextField();
        nameTextField.setEnabled(false);

        super.add(nameLabel);
        super.add(nameTextField);
    }

    public void selectBoulder(Boulder boulder)
    {
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
    public void saveBoulder(Boulder boulder)
    {
    }

    @Override
    public void changeState(ProgramState programState)
    {
    }
}
