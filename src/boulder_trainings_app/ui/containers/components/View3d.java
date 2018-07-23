package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.controller.BoulderController;
import boulder_trainings_app.controller.SelectionController;
import boulder_trainings_app.controller.StateController;
import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.engine.jme.utils.BoulderUpdater;
import boulder_trainings_app.engine.jme.appstates.SelectAppState;
import boulder_trainings_app.engine.jme.utils.AbstractInputController;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.material.TechniqueDef.LightMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.ssao.SSAOFilter;
import com.jme3.scene.Spatial;
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
    private boolean isEnabled = false;
    
    private RigidBodyControl landscape;
    private CharacterControl player;
    private BulletAppState bulletAppState;
    private boolean left = false, right = false, up = false, down = false;
    private boolean walkMode = true;

    //Temporary vectors used on each frame.
    //They here to avoid instanciating new vectors on each frame
    private final Vector3f walkDirection = new Vector3f();
    private final Vector3f camDir = new Vector3f();
    private final Vector3f camLeft = new Vector3f();
    
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
        setUpLight();

        //initial state
        this.getStateManager().attach(new SelectAppState());
    }
    
    public void enableWalkMode(boolean enable)
    {
        this.walkMode = enable;
    }
    
    public void setInput(boolean enable)
    {
        isEnabled = enable;
        flyCam.setEnabled(enable);
        flyCam.setMoveSpeed(30f);
        setMouseCursor(!enable);
        setCrossHair(enable);
    }
    
    public void setEnabled(boolean enabled)
    {
        this.isEnabled = enabled;
    }
    
    public boolean isEnabled()
    {
        return this.isEnabled;
    }
    
    private void setMouseCursor(boolean enabled)
    {
        mouseInput.setCursorVisible(enabled);
    }
    
    private void initWorld()
    {
        Spatial gym = assetManager.loadModel("Models/boulderhalle.blend");
        
        CollisionShape sceneShape
                = CollisionShapeFactory.createMeshShape(gym);
        landscape = new RigidBodyControl(sceneShape, 0);
        gym.addControl(landscape);
        
        CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f, 2f, 1);
        player = new CharacterControl(capsuleShape, 0.05f);
        player.setJumpSpeed(20);
        player.setFallSpeed(30);
        player.setGravity(new Vector3f(0, -30f, 0));
        player.setPhysicsLocation(new Vector3f(0, 10, 0));
        
        rootNode.attachChild(gym);
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        bulletAppState.getPhysicsSpace().add(landscape);
        bulletAppState.getPhysicsSpace().add(player);
        
    }
    
    private void setUpLight()
    {
        renderManager.setPreferredLightMode(LightMode.SinglePass);
        renderManager.setSinglePassLightBatchSize(16);
        
        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.2f));
        rootNode.addLight(al);
        
        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        viewPort.addProcessor(fpp);
        SSAOFilter ssaoFilter = new SSAOFilter(12.94f, 43.93f / 2, 0.33f, 0.60f);
        fpp.addFilter(ssaoFilter);
    }
    
    @Override
    public void simpleUpdate(float tpf)
    {
        if (walkMode)
        {
            camDir.set(cam.getDirection()).multLocal(0.3f);
            camLeft.set(cam.getLeft()).multLocal(0.2f);
            walkDirection.set(0, 0, 0);
            if (left)
            {
                walkDirection.addLocal(camLeft);
            }
            if (right)
            {
                walkDirection.addLocal(camLeft.negate());
            }
            if (up)
            {
                walkDirection.addLocal(camDir);
            }
            if (down)
            {
                walkDirection.addLocal(camDir.negate());
            }
            player.setWalkDirection(walkDirection);
            cam.setLocation(player.getPhysicsLocation());
        }
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
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
    }

    /**
     * A centred plus sign to help the player aim.
     */
    private void setCrossHair(boolean enable)
    {
        if (!enable)
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
            inputManager.addListener(this, "MOUSE_LEFT_CLICK");
            inputManager.addListener(this, "Left");
            inputManager.addListener(this, "Right");
            inputManager.addListener(this, "Up");
            inputManager.addListener(this, "Down");
            inputManager.addListener(this, "Jump");
            
        }
        
        @Override
        public void cleanUp()
        {
            inputManager.removeListener(this);
        }
        
        @Override
        public void onAction(String name, boolean isPressed, float tpf)
        {
            if (isEnabled)
            {
                if (name.equals("EXIT_3DVIEW") && !isPressed)
                {
                    updateSize(parentContainer.getSize());
                    setInput(false);
                }
                
                if (name.equals("DELETE") && !isPressed)
                {
                    BoulderController.getInstance().deleteBoulder(SelectionController.getInstance().getSelectedBoulder());
                    SelectionController.getInstance().selectBoulder(null);
                }
            }
            
            if (name.equals("MOUSE_LEFT_CLICK") && !isPressed)
            {
                updateSize(parentContainer.getSize());
                setInput(true);
            }
            
            if (name.equals("SELECT_MODE") && !isPressed)
            {
                StateController.getInstance().changeState(ProgramState.SELECT);
            }
            if (name.equals("EDIT_MODE") && !isPressed)
            {
                StateController.getInstance().changeState(ProgramState.EDIT);
            }
            if (name.equals("CREATE_MODE") && !isPressed)
            {
                SelectionController.getInstance().selectBoulder(null);
                StateController.getInstance().changeState(ProgramState.CREATE);
            }
            
            if (name.equals("Left"))
            {
                left = isPressed;
            }
            if (name.equals("Right"))
            {
                right = isPressed;
            }
            if (name.equals("Up"))
            {
                up = isPressed;
            }
            if (name.equals("Down"))
            {
                down = isPressed;
            }
            
            if (name.equals("Jump"))
            {
                if (isPressed)
                {
                    player.jump(new Vector3f(0, 10f, 0));
                }
            }
        }
    }
}
