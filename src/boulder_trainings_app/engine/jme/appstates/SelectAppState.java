package boulder_trainings_app.engine.jme.appstates;

import boulder_trainings_app.ApplicationState;
import boulder_trainings_app.engine.jme.utils.AbstractInputController;
import boulder_trainings_app.ui.containers.components.View3d;
import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Ray;

/**
 *
 * @author Fabian Rauscher
 */
public class SelectAppState extends BaseAppState
{
    private View3d app;
    private InputController input;

    @Override
    protected void initialize(Application app)
    {
        this.app = (View3d) app;
        this.input = new InputController();
        input.setUp();
    }

    @Override
    protected void cleanup(Application app)
    {
        input.cleanUp();
    }

    @Override
    protected void onEnable()
    {
    }

    @Override
    protected void onDisable()
    {
    }

    private class InputController extends AbstractInputController implements ActionListener
    {
        @Override
        public void setUp()
        {
            app.getInputManager().addListener(this, "MOUSE_LEFT_CLICK");
        }

        @Override
        public void cleanUp()
        {
            app.getInputManager().removeListener(this);
        }

        @Override
        public void onAction(String name, boolean isPressed, float tpf)
        {
            if (name.equals("MOUSE_LEFT_CLICK") && !isPressed)
            {
                CollisionResults results = new CollisionResults();
                Ray ray = new Ray(app.getCamera().getLocation(), app.getCamera().getDirection());
                app.getRootNode().collideWith(ray, results);
                if (results.size() > 0)
                {
                    ApplicationState.getInstance().selectBoulder(ApplicationState.getInstance().getBoulderById(results.getClosestCollision().getGeometry().getName()));
                }
            }
        }
    }
}
