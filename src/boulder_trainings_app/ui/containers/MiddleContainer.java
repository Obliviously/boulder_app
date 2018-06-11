/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers;

import boulder_trainings_app.data.Const;
import boulder_trainings_app.ui.containers.components.View3d;
import com.jme3.system.JmeCanvasContext;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
        super.setPreferredSize(new Dimension(3 * Const.MIN_WIDTH / 5, HEIGHT));

        view3d = new View3d(this);
        view3d.createCanvas();
        ctx = (JmeCanvasContext) view3d.getContext();
        ctx.setSystemListener(view3d);
        super.add(ctx.getCanvas());
    }

}
