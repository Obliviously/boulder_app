/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers;

import boulder_trainings_app.utils.Consts;
import boulder_trainings_app.ui.containers.components.View3d;
import com.jme3.system.JmeCanvasContext;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Fabian Rauscher
 */
public class MiddleContainer extends JPanel
{
    private final View3d view3d;
    private final JmeCanvasContext ctx;

    public MiddleContainer()
    {
        super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        super.setPreferredSize(new Dimension(3 * Consts.MIN_WIDTH / 5, HEIGHT));

        view3d = new View3d(this);
        view3d.createCanvas();
        ctx = (JmeCanvasContext) view3d.getContext();
        ctx.setSystemListener(view3d);

        super.add(ctx.getCanvas());

        ctx.getCanvas().addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                //To make sure input is always captured by the jme
                ctx.getCanvas().getParent().requestFocus();
                ctx.getCanvas().getParent().requestFocusInWindow();
            }
        });
    }
}
