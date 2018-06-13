/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.enums.ProgramState;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import boulder_trainings_app.ui.StateDependent;

/**
 *
 * @author fabian
 */
public class Options extends JPanel implements StateDependent
{
    public Options()
    {
        super();
        this.add(new JLabel("Options"));
        this.setBackground(Color.GRAY);
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
    public void selectBoulder(Boulder boulder)
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
