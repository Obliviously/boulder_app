/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers;

import boulder_trainings_app.ui.containers.components.View3d;
import com.jme3.system.JmeCanvasContext;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Fabian Rauscher
 */
public class MiddleContainer extends JPanel
{

    private View3d view3d;
    private JmeCanvasContext ctx;

    public MiddleContainer()
    {
        super.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        view3d = new View3d();
        view3d.createCanvas();
        ctx = (JmeCanvasContext) view3d.getContext();
        ctx.setSystemListener(view3d);
        super.add(ctx.getCanvas());

        super.addComponentListener(new ComponentListener()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                Dimension dim = getSize();
                //this fixes a small offset that appears after resizing 
                ctx.getCanvas().setBounds(new Rectangle(dim));

                ctx.getCanvas().setPreferredSize(dim);
                ctx.getCanvas().setSize(dim);
                view3d.updateSize(dim);
            }

            @Override
            public void componentMoved(ComponentEvent e)
            {
            }

            @Override
            public void componentShown(ComponentEvent e)
            {
            }

            @Override
            public void componentHidden(ComponentEvent e)
            {
            }
        });       
    }
}
