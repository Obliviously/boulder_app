/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.ApplicationState;
import boulder_trainings_app.utils.Consts;
import boulder_trainings_app.utils.Payload;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author fabian
 */
public class BoulderSelect extends JPanel implements Observer
{
    private final JTextField nameTextField;

    public BoulderSelect()
    {
        super();
        super.setLayout(new GridLayout(10, 2));

        ApplicationState.getInstance().addObserver(this);

        JLabel nameLabel = new JLabel(Consts.BOULDER_NAME_LABEL);
        JLabel colorLabel = new JLabel(Consts.BOULDER_COLOR_LABEL);
        JLabel sectionLabel = new JLabel(Consts.BOULDER_SECTION_LABEL);
        JLabel dateLabel = new JLabel(Consts.BOULDER_DATE_LABEL);

        nameTextField = new JTextField();
        nameTextField.setEnabled(false);

        super.add(nameLabel);
        super.add(nameTextField);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if (o instanceof ApplicationState)
        {
            if (arg instanceof Payload)
            {
                Payload payload = (Payload) arg;
                Boulder boulder;
                ArrayList<Boulder> boulderList;
                switch (payload.getState())
                {
                case SELECT_BOULDER:
                    boulder = (Boulder) payload.getData();
                    selectBoulder(boulder);
                    break;

                default:
                    break;
                }
            }
        }
    }

    private void selectBoulder(Boulder boulder)
    {
        nameTextField.setBackground(boulder.getColor().toColor());
        nameTextField.setText(boulder.getId());
    }
}
