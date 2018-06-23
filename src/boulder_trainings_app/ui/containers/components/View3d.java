package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.ApplicationState;
import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.engine.jme.utils.BoulderUpdater;
import boulder_trainings_app.engine.jme.appstates.SelectAppState;
import boulder_trainings_app.engine.jme.utils.AbstractInputController;
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
        boulderUpdater = new BoulderUpdater(this);
        setPauseOnLostFocus(false);
        setDisplayFps(false);
        setDisplayStatView(false);
        flyCam.setEnabled(false);

        initInputMappings();
        input.setUp();

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
        Node box = (Node) assetManager.loadModel("Models/box.blend");
        rootNode.attachChild(box);
    }

    private void initInputMappings()
    {
        inputManager.addMapping("MOUSE_MOVE", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping("MOUSE_MOVE", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        inputManager.addMapping("MOUSE_LEFT_CLICK", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping("SELECT_MODE", new KeyTrigger(KeyInput.KEY_1));
        inputManager.addMapping("EDIT_MODE", new KeyTrigger(KeyInput.KEY_2));
        inputManager.addMapping("CREATE_MODE", new KeyTrigger(KeyInput.KEY_3));
        inputManager.addMapping("EXIT_3DVIEW", new KeyTrigger(KeyInput.KEY_ESCAPE));
        inputManager.addMapping("DELETE", new KeyTrigger(KeyInput.KEY_DELETE));

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
        public void setUp()
        {
            inputManager.addListener(this, "EXIT_3DVIEW");
            inputManager.addListener(this, "SELECT_MODE");
            inputManager.addListener(this, "EDIT_MODE");
            inputManager.addListener(this, "CREATE_MODE");
            inputManager.addListener(this, "DELETE");

        }

        @Override
        public void cleanUp()
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
            if (name.equals("SELECT_MODE") && !isPressed)
            {
                ApplicationState.getInstance().changeState(ProgramState.SELECT);
            }
            if (name.equals("EDIT_MODE") && !isPressed)
            {
                ApplicationState.getInstance().changeState(ProgramState.EDIT);
            }
            if (name.equals("CREATE_MODE") && !isPressed)
            {
                ApplicationState.getInstance().changeState(ProgramState.CREATE);
            }
            if (name.equals("DELETE") && !isPressed)
            {
                ApplicationState.getInstance().removeSelectedBoulder();
            }
        }
    }
}
