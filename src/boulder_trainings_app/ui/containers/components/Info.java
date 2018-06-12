/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.utils.Consts;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author fabian
 */
public class Info extends JPanel
{
    public Info()
    {
        super();
        super.add(new JLabel(Consts.PROGRAM_VERSION));
        super.add(new JLabel(Consts.PROGRAM_NAME));
        super.setBackground(Color.GRAY);

    }
}
