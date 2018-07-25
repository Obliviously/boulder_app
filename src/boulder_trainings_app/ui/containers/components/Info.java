/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.utils.Consts;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import boulder_trainings_app.controller.interfaces.StateDependent;

/**
 *
 * @author fabian
 */
public class Info extends JPanel implements StateDependent
{
    JLabel programState;
    JLabel programName;
    JLabel programVersion;

    public Info()
    {
        super();

        programState = new JLabel(ProgramState.SELECT.toString());
        programName = new JLabel(Consts.PROGRAM_NAME);
        programVersion = new JLabel(Consts.PROGRAM_VERSION);

        super.add(programState);
        super.add(programName);
        super.add(programVersion);
        super.setBackground(Color.GRAY);

        COMPONENTS.add(this);
    }

    @Override
    public void changeState(ProgramState programState)
    {
        this.programState.setText(programState.toString());
        this.validate();
    }
}
