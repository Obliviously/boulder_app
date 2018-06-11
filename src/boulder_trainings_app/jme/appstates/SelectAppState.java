/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.jme.appstates;

import boulder_trainings_app.data.ProgramData;
import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.jme.utils.AbstractInputController;
import boulder_trainings_app.ui.containers.components.View3d;
import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.math.Ray;

/**
 *
 * @author Fabian Rauscher
 */
public class SelectAppState extends BaseAppState
{
    private View3d app;
    private AppStateManager stateManager;
    private InputController input;

    @Override
    protected void initialize(Application app)
    {
        this.app = (View3d) app;
        this.stateManager = app.getStateManager();
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

    private class InputController extends AbstractInputController implements AnalogListener, ActionListener
    {
        @Override
        public void setUpInput()
        {
            app.getInputManager().addListener(this, "MOUSE_MOVE");
            app.getInputManager().addListener(this, "SWITCH_MODE");
            app.getInputManager().addListener(this, "MOUSE_LEFT_CLICK");
        }

        @Override
        public void cleanUpInput()
        {
            app.getInputManager().removeListener(this);
        }

        @Override
        public void onAnalog(String name, float value, float tpf)
        {
            if (name.equals("MOUSE_MOVE"))
            {
                CollisionResults results = new CollisionResults();
                Ray ray = new Ray(app.getCamera().getLocation(), app.getCamera().getDirection());
                app.getRootNode().collideWith(ray, results);

                if (results.size() > 0)
                {
                    ProgramData.getInstance().highLightBoulder(results.getClosestCollision().getGeometry().getName());
                }
            }
        }

        @Override
        public void onAction(String name, boolean isPressed, float tpf)
        {
            if (name.equals("SWITCH_MODE") && !isPressed)
            {
                ProgramData.getInstance().changeStateTo(ProgramState.EDIT);
                stateManager.detach(getState(SelectAppState.class));
                stateManager.attach(new EditAppState());
            }

            if (name.equals("MOUSE_LEFT_CLICK") && !isPressed)
            {
                CollisionResults results = new CollisionResults();
                Ray ray = new Ray(app.getCamera().getLocation(), app.getCamera().getDirection());
                app.getRootNode().collideWith(ray, results);
                if (results.size() > 0)
                {
                    ProgramData.getInstance().selectBoulder(results.getClosestCollision().getGeometry().getName());
                }
            }
        }

    }
}
