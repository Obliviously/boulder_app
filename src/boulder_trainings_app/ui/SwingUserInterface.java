package boulder_trainings_app.ui;

import boulder_trainings_app.ui.containers.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * This class represents the GUI for the Application.
 *
 * @author Fabian Rauscher
 */
public class SwingUserInterface implements GraphicalUserInterface
{
    private JFrame frame;
    
    @Override
    public void display(String title, int minWidth, int minHeight)
    {
        java.awt.EventQueue.invokeLater(() ->
        {
            frame = new JFrame();
            frame.setTitle(title);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setUndecorated(true);
            loadComponents();
            frame.setSize(minWidth, minHeight);
            frame.setMinimumSize(new Dimension(minWidth, minHeight));
            frame.setVisible(true);
        });
    }
    
    private void loadComponents()
    {
        final int HGAP = 5;
        final int VGAP = 0;
        BorderLayout borderLayout = new BorderLayout(HGAP, VGAP);
        frame.setLayout(borderLayout);        
        TopContainer top = new TopContainer();
        MiddleContainer middle = new MiddleContainer();
        LeftContainer left = new LeftContainer();
        RightContainer right = new RightContainer();
        BottomContainer bottom = new BottomContainer();
        frame.add(top, BorderLayout.PAGE_START);
        frame.add(left, BorderLayout.LINE_START);
        frame.add(middle, BorderLayout.CENTER);
        frame.add(right, BorderLayout.LINE_END);
        frame.add(bottom, BorderLayout.PAGE_END);
    }   
}
