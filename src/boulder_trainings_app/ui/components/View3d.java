/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.components;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import java.awt.Dimension;

/**
 *
 * @author fabian
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
        flyCam.setDragToRotate(true);

        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
    }

}
