/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers;

import boulder_trainings_app.data.Const;
import boulder_trainings_app.ui.containers.components.Sections;
import java.awt.Dimension;
import static java.awt.image.ImageObserver.HEIGHT;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Fabian Rauscher
 */
public class LeftContainer extends JPanel
{
    public LeftContainer()
    {
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Sections sections = new Sections();
        sections.setPreferredSize(new Dimension(Const.MIN_WIDTH / 5, HEIGHT));
        super.add(sections);
    }
}
