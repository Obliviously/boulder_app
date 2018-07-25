package boulder_trainings_app.engine.jme.utils;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.engine.jme.appstates.CreateAppState;
import boulder_trainings_app.engine.jme.appstates.EditAppState;
import boulder_trainings_app.engine.jme.appstates.SelectAppState;
import boulder_trainings_app.controller.interfaces.BoulderDependent;
import boulder_trainings_app.controller.interfaces.StateDependent;
import boulder_trainings_app.ui.containers.components.View3d;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.HashMap;
import boulder_trainings_app.controller.interfaces.SelectionDependent;

/**
 * This class is responsible for the visualization of the currrent boulders in
 * the 3dView.
 *
 * @author Fabian Rauscher
 */
public class BoulderUpdater implements StateDependent, BoulderDependent, SelectionDependent
{
    private final View3d view3d;
    private final HashMap<String, Node> bouldersMap = new HashMap<>();
    private final Node rootNode;
    private final AssetManager assetManager;
    private final AppStateManager stateManager;
    private final ColorRGBA SELECTION_COLOR = ColorRGBA.Cyan;
    private Class currentStateClass = SelectAppState.class;

    private Boulder selectedBoulder = null;

    public BoulderUpdater(View3d app)
    {
        this.view3d = app;
        this.rootNode = app.getRootNode();
        this.assetManager = app.getAssetManager();
        this.stateManager = app.getStateManager();
        StateDependent.COMPONENTS.add(this);
        BoulderDependent.COMPONENTS.add(this);
        SelectionDependent.COMPONENTS.add(this);
    }

    @Override
    public void selectBoulder(Boulder boulder)
    {
        if (selectedBoulder != null)
        {
            changeBoulderColor(selectedBoulder, selectedBoulder.getColor().toColorRGBA());
        }
        if (boulder != null)
        {
            changeBoulderColor(boulder, boulder.getColor().toColorRGBA().add(ColorRGBA.White.mult(0.5f)));
        }
        selectedBoulder = boulder;
    }

    private void changeBoulderColor(Boulder boulder, ColorRGBA color)
    {
        Node boulderNode = bouldersMap.get(boulder.getId());
        if (boulderNode != null)
        {
            Material selectionMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            selectionMat.setColor("Color", color);

            for (Spatial s : boulderNode.getChildren())
            {
                Geometry geom = (Geometry) s;
                geom.setMaterial(selectionMat);
            }
        }
    }

    @Override
    public void removeBoulder(Boulder boulder)
    {
        destroyBoulder(bouldersMap.remove(boulder.getId()));
    }

    @Override
    public void addBoulder(Boulder boulder)
    {
        bouldersMap.put(boulder.getId(), createBoulder(boulder));
    }

    public Node createBoulder(Boulder boulder)
    {
        Node boulderNode = new Node();

        boulderNode.setName(boulder.getId());
        Material defaultMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");

        Geometry geom;
        Vector3f previousPoint = null;
        for (Vector3f vector : boulder.getPositions())
        {
            geom = MeshUtils.createMark(vector, boulder.getId(), defaultMaterial, boulder.getColor().toColorRGBA());
            boulderNode.attachChild(geom);
            if (previousPoint != null)
            {
                geom = MeshUtils.createLineBetween(vector, previousPoint, boulder.getId(), defaultMaterial, boulder.getColor().toColorRGBA());
                boulderNode.attachChild(geom);
            }
            previousPoint = vector;
        }
        rootNode.attachChild(boulderNode);
        return boulderNode;
    }

    @Override
    public void changeState(ProgramState programState)
    {
        switch (programState)
        {
        case SELECT:
            stateManager.detach(stateManager.getState(currentStateClass));
            stateManager.attach(new SelectAppState());
            view3d.enableWalkMode(true);
            currentStateClass = SelectAppState.class;
            break;
        case EDIT:
            stateManager.detach(stateManager.getState(currentStateClass));
            stateManager.attach(new EditAppState());
            view3d.enableWalkMode(true);
            currentStateClass = EditAppState.class;
            break;
        case CREATE:
            stateManager.detach(stateManager.getState(currentStateClass));
            stateManager.attach(new CreateAppState());
            view3d.enableWalkMode(false);
            currentStateClass = CreateAppState.class;
            break;
        default:
            break;
        }
    }

    @Override
    public void updateBoulder(Boulder boulder)
    {
        if (bouldersMap.containsKey(boulder.getId()))
        {
            destroyBoulder(bouldersMap.get(boulder.getId()));
            bouldersMap.put(boulder.getId(), createBoulder(boulder));
        }
    }

    @SuppressWarnings("empty-statement")
    private void destroyBoulder(Node boulderNode)
    {
        while (rootNode.detachChildNamed(boulderNode.getName()) != -1);
    }
}
