/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.enums.ProgramState;
import static boulder_trainings_app.ui.StateDependent.components;
import boulder_trainings_app.utils.Consts;
import boulder_trainings_app.ui.containers.components.View3d;
import com.jme3.system.JmeCanvasContext;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import boulder_trainings_app.ui.StateDependent;

/**
 *
 * @author Fabian Rauscher
 */
public class MiddleContainer extends JPanel implements StateDependent
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

        components.add(view3d);
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
