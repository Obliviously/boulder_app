/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers;

import boulder_trainings_app.data.Const;
import boulder_trainings_app.ui.containers.components.BoulderInfo;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Fabian Rauscher
 */
public class RightContainer extends JPanel
{
    public RightContainer()
    {
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        BoulderInfo statistics = new BoulderInfo();
        super.setPreferredSize(new Dimension(Const.MIN_WIDTH / 5, HEIGHT));
        super.add(statistics);
    }
}
