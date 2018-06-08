/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.components;

import com.jme3.system.JmeCanvasContext;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Fabian Rauscher
 */
public class View3dContainer extends JPanel
{

    private View3d view3d;
    private JmeCanvasContext ctx;

    public View3dContainer()
    {
        view3d = new View3d();
        view3d.createCanvas();
        ctx = (JmeCanvasContext) view3d.getContext();
        ctx.setSystemListener(view3d);
        add(ctx.getCanvas());

        this.addComponentListener(new ComponentListener()
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

        this.addMouseListener((new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                view3d.toogleInput();
                System.out.println(".mouseClicked()");
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
            }
        }));
    }
}
