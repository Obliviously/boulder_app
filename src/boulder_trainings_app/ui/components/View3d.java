/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.components;

import boulder_trainings_app.jme.appstates.SelectAppState;
import boulder_trainings_app.jme.utils.VertexUtils;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;

/**
 *
 * @author Fabian Rauscher
 */
public class View3d extends SimpleApplication
{
    public View3d()
    {
        super();
        AppSettings appSetting = new AppSettings(true);
        appSetting.setFrameRate(60);
        this.showSettings = false;
        this.setSettings(appSetting);
    }

    @Override
    public void simpleInitApp()
    {
        setDisplayFps(false);
        setDisplayStatView(false);
        //flyCam.setDragToRotate(true);

        //innitialize input mappings
        initInputMappings();
        
        //load world
        initWorld();

        //initial state
        this.getStateManager().attach(new SelectAppState());

    }

    private void initWorld()
    {
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Green);
        Geometry boxGeo;
        Node box = (Node) assetManager.loadModel("Models/box.blend");
        boxGeo = (Geometry) (((Node) ((Node) box.getChild(0)).getChild(0)).getChild(0));
        boxGeo.setMaterial(mat);
        rootNode.attachChild(box);
    }

    private void initInputMappings()
    {
        inputManager.addMapping("MOUSE_MOVE", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping("MOUSE_MOVE", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        inputManager.addMapping("MOUSE_LEFT_CLICK", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping("SWITCH_MODE", new KeyTrigger(KeyInput.KEY_TAB));
        inputManager.addMapping("SAVE_BOULDER", new KeyTrigger(KeyInput.KEY_S));
    }

}
