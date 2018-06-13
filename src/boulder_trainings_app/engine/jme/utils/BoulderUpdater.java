package boulder_trainings_app.engine.jme.utils;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.ApplicationState;
import boulder_trainings_app.utils.Payload;
import boulder_trainings_app.engine.jme.utils.MeshUtils;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * This class is responsible for the visualization of the currrent boulders in
 * the 3dView.
 *
 *
 * @author Fabian Rauscher
 */
public class BoulderUpdater
{
    private final HashMap<String, Node> bouldersMap = new HashMap<>();
    private final Node rootNode;
    private final AssetManager assetManager;
    private final ColorRGBA SELECTION_COLOR = ColorRGBA.DarkGray;

    private Boulder selectedBoulder = null;

    public BoulderUpdater(SimpleApplication app)
    {
        this.rootNode = app.getRootNode();
        this.assetManager = app.getAssetManager();
    }

    public void selectBoulder(Boulder boulder)
    {
        if (selectedBoulder != null)
        {
            deselectBoulder(selectedBoulder);
        }
        Node boulderNode = bouldersMap.get(boulder.getId());
        Material selectionMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        selectionMat.setColor("Color", SELECTION_COLOR);

        for (Spatial s : boulderNode.getChildren())
        {
            Geometry geom = (Geometry) s;
            geom.setMaterial(selectionMat);
        }
        selectedBoulder = boulder;
    }

    public void deselectBoulder(Boulder boulder)
    {
        Node boulderNode = bouldersMap.get(boulder.getId());
        Material selectionMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        selectionMat.setColor("Color", boulder.getColor().toColorRGBA());

        for (Spatial s : boulderNode.getChildren())
        {
            Geometry geom = (Geometry) s;
            geom.setMaterial(selectionMat);
        }
        selectedBoulder = null;
    }

    public void highLightBoulder(Boulder boulder)
    {
        //TODO
    }

    public void removeBoulder(Boulder boulder)
    {
        destroyBoulder(bouldersMap.remove(boulder.getId()));
    }

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

    private void destroyBoulder(Node boulderNode)
    {
        rootNode.detachChildNamed(boulderNode.getName());
    }
}
