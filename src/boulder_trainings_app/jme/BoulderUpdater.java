package boulder_trainings_app.jme;

import boulder_trainings_app.BoulderManager;
import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.BoulderList;
import boulder_trainings_app.data.Payload;
import boulder_trainings_app.jme.utils.MeshUtils;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is responsible for the visualization of the currrent boulders in
 * the 3dView.
 *
 *
 * @author Fabian Rauscher
 */
public class BoulderUpdater implements Observer
{
    private final HashMap<String, Node> bouldersMap = new HashMap<>();
    private final Node rootNode;
    private final AssetManager assetManager;

    public BoulderUpdater(SimpleApplication app)
    {
        this.rootNode = app.getRootNode();
        this.assetManager = app.getAssetManager();
        BoulderManager.getInstance().getBoulderList().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if (o instanceof BoulderList)
        {
            if (arg instanceof Payload)
            {
                Payload payload = (Payload) arg;
                Boulder boulder;
                ArrayList<Boulder> boulderList;
                switch (payload.getState())
                {
                case ADDED_BOULDER:
                    boulder = (Boulder) payload.getData();
                    addBoulder(boulder);

                    break;
                case REMOVED_BOULDER:
                    boulder = (Boulder) payload.getData();
                    removeBoulder(boulder);

                    break;
                case ADDED_BOULDER_LIST:
                    boulderList = (ArrayList<Boulder>) payload.getData();

                    for (Boulder b : boulderList)
                    {
                        addBoulder(b);
                    }
                    break;
                case REMOVED_BOULDER_LIST:
                    boulderList = (ArrayList<Boulder>) payload.getData();

                    for (Boulder b : boulderList)
                    {
                        removeBoulder(b);
                    }

                    break;
                case HIGHLIGHT_BOULDER:
                    highLightBoulder(bouldersMap.get((String) payload.getData()));
                    break;
                default:
                    break;
                }
            }
        }
    }

    private void highLightBoulder(Node boulderNode)
    {
        new Thread(() ->
        {
            Material oldMat;
            Material highlightMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
            highlightMat.setColor("Color", ColorRGBA.White);

            for (Spatial s : boulderNode.getChildren())
            {
                Geometry geom = (Geometry) s;
                oldMat = ((Geometry) s).getMaterial();
                geom.setMaterial(highlightMat);
                try
                {
                    Thread.sleep(200);
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(BoulderUpdater.class.getName()).log(Level.SEVERE, null, ex);
                }
                geom.setMaterial(oldMat);
            }
            BoulderManager.getInstance().getBoulderList().getBoulderById(boulderNode.getName()).setHighlighted(false);
        }
        ).start();
    }

    private void removeBoulder(Boulder boulder)
    {
        destroyBoulder(bouldersMap.remove(boulder.getId()));
    }

    private void addBoulder(Boulder boulder)
    {
        bouldersMap.put(boulder.getId(), createBoulder(boulder));
    }

    private Node createBoulder(Boulder boulder)
    {
        Node boulderNode = new Node();
        boulderNode.setName(boulder.getId());
        Material defaultMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");

        Geometry geom;
        Vector3f previousPoint = null;
        for (Vector3f vector : boulder.getPositions())
        {
            geom = MeshUtils.createMark(vector, boulder.getId(), defaultMaterial, boulder.getColor());
            boulderNode.attachChild(geom);
            if (previousPoint != null)
            {
                geom = MeshUtils.createLineBetween(vector, previousPoint, boulder.getId(), defaultMaterial, boulder.getColor());
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
