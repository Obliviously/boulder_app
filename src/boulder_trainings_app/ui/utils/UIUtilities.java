package boulder_trainings_app.ui.utils;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Window;
import javax.swing.SwingUtilities;

/**
 *
 * @author Fabian Rauscher
 */
public class UIUtilities
{
    public static Frame getParentFrame(Component parentWindow)
    {
        Frame parentFrame = null;

        while (parentFrame == null)
        {
            parentWindow = SwingUtilities.windowForComponent(parentWindow);
            if (parentWindow instanceof Frame)
            {
                parentFrame = (Frame) parentWindow;
            }
        }
        return parentFrame;
    }
}
