/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui;

import boulder_trainings_app.ui.containers.BottomContainer;
import boulder_trainings_app.ui.containers.LeftContainer;
import boulder_trainings_app.ui.containers.RightContainer;
import boulder_trainings_app.ui.containers.TopContainer;
import boulder_trainings_app.ui.containers.MiddleContainer;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * This class represents the GUI for the Application.
 *
 * @author Fabian Rauscher
 */
public class SwingUserInterface implements GraphicalUserInterface
{
    @Override
    public void display(String title, int minWidth, int minHeight)
    {
        java.awt.EventQueue.invokeLater(() ->
        {
            JFrame frame = new JFrame();
            frame.setTitle(title);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setUndecorated(true);
            SwingUserInterface.loadComponents(frame);
            frame.setVisible(true);
            frame.setSize(minWidth, minHeight);
            frame.setMinimumSize(new Dimension(minWidth, minHeight));
        });
    }

    private static void loadComponents(JFrame frame)
    {
        Container pane = frame.getContentPane();
        final int HGAP = 5;
        final int VGAP = 0;
        BorderLayout borderLayout = new BorderLayout(HGAP, VGAP);
        pane.setLayout(borderLayout);
        TopContainer top = new TopContainer();
        MiddleContainer middle = new MiddleContainer();
        LeftContainer left = new LeftContainer();
        RightContainer right = new RightContainer();
        BottomContainer bottom = new BottomContainer();
        pane.add(top, BorderLayout.PAGE_START);
        pane.add(left, BorderLayout.LINE_START);
        pane.add(middle, BorderLayout.CENTER);
        pane.add(right, BorderLayout.LINE_END);
        pane.add(bottom, BorderLayout.PAGE_END);
    }
}
