/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.engine.jme.appstates;

import boulder_trainings_app.ApplicationState;
import boulder_trainings_app.engine.jme.utils.AbstractInputController;
import boulder_trainings_app.ui.containers.components.View3d;
import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Ray;

/**
 *
 * @author Fabian Rauscher
 */
public class EditAppState extends BaseAppState
{
    private View3d app;
    private InputController input;

    @Override
    protected void initialize(Application app)
    {
        this.app = (View3d) app;
        this.input = new InputController();
        input.setUpInput();
    }

    @Override
    protected void cleanup(Application app)
    {
        input.cleanUpInput();
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
        public void onAction(String name, boolean isPressed, float tpf)
        {
            if (name.equals("MOUSE_LEFT_CLICK"))
            {
                CollisionResults results = new CollisionResults();
                Ray ray = new Ray(app.getCamera().getLocation(), app.getCamera().getDirection());
                app.getRootNode().collideWith(ray, results);

                if (results.size() > 0)
                {
                    CollisionResult closest = results.getClosestCollision();
                    ApplicationState.getInstance().selectBoulder(closest.getGeometry().getName());
                }
            }
        }

        @Override
        public void setUpInput()
        {
            app.getInputManager().addListener(this, "MOUSE_LEFT_CLICK");
        }

        @Override
        public void cleanUpInput()
        {
            app.getInputManager().removeListener(this);
        }

    }
}
