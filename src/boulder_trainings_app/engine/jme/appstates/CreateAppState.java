/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.engine.jme.appstates;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.ApplicationState;
import boulder_trainings_app.engine.jme.utils.AbstractInputController;
import boulder_trainings_app.engine.jme.utils.MeshUtils;
import boulder_trainings_app.engine.jme.utils.VertexUtils;
import boulder_trainings_app.ui.containers.components.View3d;
import com.jme3.app.Application;
import com.jme3.app.state.BaseAppState;
import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.material.Material;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabian Rauscher
 */
public class CreateAppState extends BaseAppState
{
    private static final Logger LOGGER = Logger.getLogger(CreateAppState.class.getName());

    private View3d app;
    private InputController input;
    private Node rootNode;
    private AssetManager assetManager;

    private Boulder boulder = null;
    private ArrayList<Vector3f> currSelectedVertices = null;
    private Material defaultMaterial;

    private final String GEO_NAME = "temp_geo";
    private Geometry geom;

    @Override
    protected void initialize(Application app)
    {
        this.app = (View3d) app;
        this.input = new InputController();
        input.setUp();
        this.rootNode = this.app.getRootNode();
        this.assetManager = this.app.getAssetManager();
        this.defaultMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
    }

    private void extendBoulderTo(CollisionResult closest)
    {
        Vector3f contactPoint = closest.getContactPoint();
        ArrayList<Vector3f> newSelectedVertices = MeshUtils.calcFlatArea(closest);
        //New point is on the same flat polygon as the last one.
        if (newSelectedVertices.containsAll(currSelectedVertices))
        {
            geom = MeshUtils.createMark(contactPoint, GEO_NAME, defaultMaterial, boulder.getColor().toColorRGBA());
            rootNode.attachChild(geom);
            boulder.addPosition(contactPoint);
            geom = MeshUtils.createLineBetween(boulder.getNthLastPosition(2), boulder.getNthLastPosition(1), GEO_NAME, defaultMaterial, boulder.getColor().toColorRGBA());
            rootNode.attachChild(geom);
        }
        else
        {
            /**
             * New point is NOT on the same flat polygon as the last one. Now we
             * have to distinguish between three cases.
             * 1. New and old have no vertices in common -> Cant connect the
             * points.
             * 2. New and old have one vertex in common -> The points are
             * connected over this vertex.
             * 3. New and old have 2 vertices in common -> Calculate the closest
             * point on the line (that is created by these vertices) to the old
             * point and use it as the connecting point.
             */
            ArrayList<Vector3f> tempSelectedVertices = new ArrayList<>(newSelectedVertices);
            newSelectedVertices.retainAll(currSelectedVertices);
            switch (newSelectedVertices.size())
            {
            case 0:
                LOGGER.log(Level.INFO, "Selection is invalid! Can only connect adjacent polygons.");
                break;
            case 1:
                Geometry mark = MeshUtils.createMark(newSelectedVertices.get(0), GEO_NAME, defaultMaterial, boulder.getColor().toColorRGBA());
                rootNode.attachChild(mark);
                boulder.addPosition(newSelectedVertices.get(0));

                mark = MeshUtils.createMark(contactPoint, GEO_NAME, defaultMaterial, boulder.getColor().toColorRGBA());
                rootNode.attachChild(mark);
                boulder.addPosition(contactPoint);

                geom = MeshUtils.createLineBetween(boulder.getNthLastPosition(3), boulder.getNthLastPosition(2), GEO_NAME, defaultMaterial, boulder.getColor().toColorRGBA());
                rootNode.attachChild(geom);
                geom = MeshUtils.createLineBetween(boulder.getNthLastPosition(2), boulder.getNthLastPosition(1), GEO_NAME, defaultMaterial, boulder.getColor().toColorRGBA());
                rootNode.attachChild(geom);

                currSelectedVertices = tempSelectedVertices;
                break;
            case 2:
                Vector3f lastContactPoint = boulder.getLastPosition();
                Vector3f closestContactPoint = VertexUtils.calcClosestPointOnLine(newSelectedVertices.get(0), newSelectedVertices.get(1), lastContactPoint);

                mark = MeshUtils.createMark(closestContactPoint, GEO_NAME, defaultMaterial, boulder.getColor().toColorRGBA());
                rootNode.attachChild(mark);
                boulder.addPosition(closestContactPoint);
                mark = MeshUtils.createMark(contactPoint, GEO_NAME, defaultMaterial, boulder.getColor().toColorRGBA());
                rootNode.attachChild(mark);
                boulder.addPosition(contactPoint);

                geom = MeshUtils.createLineBetween(boulder.getNthLastPosition(3), boulder.getNthLastPosition(2), GEO_NAME, defaultMaterial, boulder.getColor().toColorRGBA());
                rootNode.attachChild(geom);
                geom = MeshUtils.createLineBetween(boulder.getNthLastPosition(2), boulder.getNthLastPosition(1), GEO_NAME, defaultMaterial, boulder.getColor().toColorRGBA());
                rootNode.attachChild(geom);

                currSelectedVertices = tempSelectedVertices;
                break;
            default:
                LOGGER.log(Level.WARNING, "This should not happens!");
                break;
            }
        }
    }

    @Override
    @SuppressWarnings("empty-statement")
    protected void cleanup(Application app)
    {
        while (rootNode.detachChildNamed(GEO_NAME) != -1);
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
        @SuppressWarnings("empty-statement")
        public void onAction(String name, boolean isPressed, float tpf)
        {
            if (name.equals("MOUSE_LEFT_CLICK") && !isPressed)
            {
                CollisionResults results = new CollisionResults();
                Ray ray = new Ray(app.getCamera().getLocation(), app.getCamera().getDirection());
                app.getRootNode().collideWith(ray, results);

                if (results.size() > 0)
                {
                    CollisionResult closest = results.getClosestCollision();
                    if (boulder == null)
                    {
                        boulder = new Boulder();
                        boulder.addPosition(closest.getContactPoint());
                        ApplicationState.getInstance().addBoulder(boulder);
                        ApplicationState.getInstance().editBoulder(boulder);
                        currSelectedVertices = MeshUtils.calcFlatArea(closest);
                    }
                    else
                    {
                        extendBoulderTo(results.getClosestCollision());
                    }
                }
            }
        }

        @Override
        public void setUp()
        {
            app.getInputManager().addListener(this, "MOUSE_LEFT_CLICK");
            app.getInputManager().addListener(this, "MOUSE_MOVE");
            app.getInputManager().addListener(this, "SWITCH_MODE");
        }

        @Override
        public void cleanUp()
        {
            app.getInputManager().removeListener(this);
        }
    }
}
