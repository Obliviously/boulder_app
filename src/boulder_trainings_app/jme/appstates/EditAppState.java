/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.jme.appstates;

import boulder_trainings_app.BoulderManager;
import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.jme.utils.AbstractInputController;
import boulder_trainings_app.jme.utils.MeshUtils;
import boulder_trainings_app.ui.containers.components.View3d;
import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import java.util.ArrayList;

/**
 *
 * @author Fabian Rauscher
 */
public class EditAppState extends BaseAppState
{
    private View3d app;
    private InputController input;

    private Vector3f contactPoint = null;
    private ArrayList<Vector3f> selectedVertices = null;

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

    private class InputController extends AbstractInputController implements AnalogListener, ActionListener
    {
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
                    CollisionResult closest = results.getClosestCollision();
                    contactPoint = closest.getContactPoint();
                    selectedVertices = MeshUtils.calcFlatArea(closest);
                    //VertexUtils.changeColorOfVertices(closest.getGeometry(), selectedVertices);
                }
            }
        }

        @Override
        public void onAction(String name, boolean isPressed, float tpf)
        {
            if (name.equals("SWITCH_MODE") && !isPressed)
            {
                getStateManager().detach(getState(EditAppState.class));
                getStateManager().attach(new SelectAppState());
            }

            if (name.equals("MOUSE_LEFT_CLICK") && selectedVertices != null && contactPoint != null)
            {
                Boulder boulder = BoulderManager.createBoulder(contactPoint);

                getStateManager().detach(getState(EditAppState.class));
                getStateManager().attach(new CreateBoulderAppState(boulder, selectedVertices));
            }
        }

        @Override
        public void setUpInput()
        {
            app.getInputManager().addListener(this, "MOUSE_LEFT_CLICK");
            app.getInputManager().addListener(this, "MOUSE_MOVE");
            app.getInputManager().addListener(this, "SWITCH_MODE");
        }

        @Override
        public void cleanUpInput()
        {
            app.getInputManager().removeListener(this);
        }

    }
}
