package boulder_trainings_app.ui.containers;

import boulder_trainings_app.utils.Consts;
import boulder_trainings_app.ui.containers.components.View3d;
import com.jme3.system.JmeCanvasContext;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

        ctx.getCanvas().addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent me)
            {
            }

            @Override
            public void mousePressed(MouseEvent me)
            {
                //To make sure input is captured by the jme
                ctx.getCanvas().getParent().requestFocus();
                ctx.getCanvas().getParent().requestFocusInWindow();
            }

            @Override
            public void mouseReleased(MouseEvent me)
            {
            }

            @Override
            public void mouseEntered(MouseEvent me)
            {
            }

            @Override
            public void mouseExited(MouseEvent me)
            {
            }
        });
    }
}
