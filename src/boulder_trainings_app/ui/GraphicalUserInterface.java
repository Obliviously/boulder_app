/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui;

import boulder_trainings_app.ui.containers.BottomContainer;
import boulder_trainings_app.ui.containers.LeftContainer;
import boulder_trainings_app.ui.containers.MiddleContainer;
import boulder_trainings_app.ui.containers.RightContainer;
import boulder_trainings_app.ui.containers.TopContainer;
import boulder_trainings_app.ui.containers.MiddleContainer;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 * This class represents the GUI for the Application.
 *
 * @author Fabian Rauscher
 */
public class GraphicalUserInterface
{
    private final String title;
    private final int minWidth;
    private final int minHeight;

    public GraphicalUserInterface(String title, int minWidth, int minHeight)
    {
        this.title = title;
        this.minWidth = minWidth;
        this.minHeight = minHeight;
    }

    public void display()
    {
        java.awt.EventQueue.invokeLater(() ->
        {
            JFrame frame = new JFrame();
            frame.setTitle(title);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setUndecorated(true);
            GraphicalUserInterface.loadComponents(frame);
            GraphicalUserInterface.addListeners(frame);
            frame.setVisible(true);
            frame.setSize(minWidth, minHeight);
            frame.setMinimumSize(new Dimension(minWidth, minHeight));
        });
    }

    /**
     * Generates and adds all the components for the ui.
     */
    private static void loadComponents(JFrame frame)
    {
        Container pane = frame.getContentPane();
        final int HGAP = 5;
        final int VGAP = 5;
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

    private static void addListeners(JFrame frame)
    {
        frame.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    System.exit(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
            }
        });
    }
}
