/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.components;

import boulder_trainings_app.BoulderManager;
import boulder_trainings_app.data.Section;
import boulder_trainings_app.jme.BoulderUpdater;
import boulder_trainings_app.jme.appstates.SelectAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
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
import org.joda.time.DateTime;

/**
 *
 * @author Fabian Rauscher
 */
public class View3d extends SimpleApplication
{

    private BoulderUpdater boulderUpdater;

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

        initInputMappings();

        initCrossHairs();

        initWorld();

        //initial state
        this.getStateManager().attach(new SelectAppState());

    }

    private void initWorld()
    {

        boulderUpdater = new BoulderUpdater(this);

        BoulderManager.getInstance().loadBoulder(DateTime.now(), Section.EIGHT);

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

    /**
     * A centred plus sign to help the player aim.
     */
    protected void initCrossHairs()
    {
        setDisplayStatView(false);
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText ch = new BitmapText(guiFont, false);
        ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        ch.setText("+"); // crosshairs
        ch.setLocalTranslation( // center
                settings.getWidth() / 2 - ch.getLineWidth() / 2,
                settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
        guiNode.attachChild(ch);
    }

}
