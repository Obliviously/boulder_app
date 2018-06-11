/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.jme.BoulderUpdater;
import boulder_trainings_app.jme.appstates.SelectAppState;
import boulder_trainings_app.jme.utils.AbstractInputController;
import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Fabian Rauscher
 */
public class View3d extends SimpleApplication
{

    //For the initial crosshair position caclculation (Couldnt figure out another way to do this).
    private final JPanel parentContainer;
    private BoulderUpdater boulderUpdater;
    private final InputController input;
    private BitmapText crossHair;
    private boolean isMouseVisible = true;
    private boolean isCrossHairVisible = false;

    public View3d(JPanel parentContainer)
    {
        super();
        this.parentContainer = parentContainer;

        AppSettings appSetting = new AppSettings(true);
        appSetting.setFrameRate(60);

        this.showSettings = false;

        super.setSettings(appSetting);
        this.input = new InputController();
    }

    @Override
    public void simpleInitApp()
    {
        setDisplayFps(false);
        setDisplayStatView(false);
        flyCam.setEnabled(false);

        initInputMappings();
        input.setUpInput();

        initCrossHair();

        initWorld();

        //initial state
        this.getStateManager().attach(new SelectAppState());

    }

    public void toogleInput()
    {
        flyCam.setEnabled(!flyCam.isEnabled());
        toggleMouseCursor();
        toggleCrossHair();
    }

    private void toggleMouseCursor()
    {
        mouseInput.setCursorVisible(!isMouseVisible);
        isMouseVisible = !isMouseVisible;
    }

    private void initWorld()
    {
        boulderUpdater = new BoulderUpdater(this);
        Node box = (Node) assetManager.loadModel("Models/box.blend");
        rootNode.attachChild(box);
    }

    private void initInputMappings()
    {
        inputManager.addMapping("MOUSE_MOVE", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping("MOUSE_MOVE", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        inputManager.addMapping("MOUSE_LEFT_CLICK", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping("SWITCH_MODE", new KeyTrigger(KeyInput.KEY_TAB));
        inputManager.addMapping("SAVE_BOULDER", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("EXIT_3DVIEW", new KeyTrigger(KeyInput.KEY_ESCAPE));
    }

    /**
     * A centred plus sign to help the player aim.
     */
    private void toggleCrossHair()
    {
        if (isCrossHairVisible)
        {
            guiNode.detachChild(crossHair);
        }
        else
        {
            //TODO: The factors are somewhat hacky. I don't know why the calculation is of without them.
            crossHair.setLocalTranslation( // center
                    (settings.getWidth() / 2.0f) - 1.2f * (crossHair.getLineWidth() / 2.0f),
                    (settings.getHeight() / 2.0f) + 1.15f * (crossHair.getLineHeight() / 2.0f), 0);

            guiNode.attachChild(crossHair);
        }
        isCrossHairVisible = !isCrossHairVisible;
    }

    private void initCrossHair()
    {
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        crossHair = new BitmapText(guiFont, false);
        crossHair.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        crossHair.setText("+");
    }

    public void updateSize(Dimension dim)
    {
        settings.setHeight((int) dim.getHeight());
        settings.setWidth((int) dim.getWidth());
    }

    private class InputController extends AbstractInputController implements ActionListener
    {
        @Override
        public void setUpInput()
        {
            inputManager.addListener(this, "EXIT_3DVIEW");
        }

        @Override
        public void cleanUpInput()
        {
            inputManager.removeListener(this);
        }

        @Override
        public void onAction(String name, boolean isPressed, float tpf)
        {
            if (name.equals("EXIT_3DVIEW") && !isPressed)
            {
                updateSize(parentContainer.getSize());
                toogleInput();
            }
        }

    }

}
