package boulder_trainings_app.ui.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
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

    public static Point getCenteredPosition(int minWidth, int minHeight)
    {
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - minWidth) / 2;
        final int y = (screenSize.height - minHeight) / 2;
        return new Point(x, y);
    }
}
