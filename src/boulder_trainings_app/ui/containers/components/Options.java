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
public class Options extends JPanel
{
    public Options()
    {
        super();
        super.add(new JLabel("Options"));
        super.setBackground(Color.GRAY);
    }
}
