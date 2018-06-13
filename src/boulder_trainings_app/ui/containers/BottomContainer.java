/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.ui.containers.components.Info;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import boulder_trainings_app.ui.StateDependent;

/**
 *
 * @author fabian
 */
public class BottomContainer extends JPanel implements StateDependent
{
    public BottomContainer()
    {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.ipadx = 0;
        c.ipady = 0;
        Info info = new Info();
        this.setLayout(gbl);
        this.add(info, c);

        components.add(info);
    }

    @Override
    public void addBoulder(Boulder boulder)
    {
        components.forEach((c) -> c.addBoulder(boulder));
    }

    @Override
    public void removeBoulder(Boulder boulder)
    {
        components.forEach((c) -> c.removeBoulder(boulder));
    }

    @Override
    public void highLightBoulder(Boulder boulder)
    {
        components.forEach((c) -> c.highLightBoulder(boulder));
    }

    @Override
    public void selectBoulder(Boulder boulder)
    {
        components.forEach((c) -> c.selectBoulder(boulder));
    }

    @Override
    public void editBoulder(Boulder boulder)
    {
        components.forEach((c) -> c.editBoulder(boulder));
    }

    @Override
    public void saveBoulder(Boulder boulder)
    {
        components.forEach((c) -> c.saveBoulder(boulder));
    }

    @Override
    public void changeState(ProgramState programState)
    {
        components.forEach((c) -> c.changeState(programState));
    }
}
