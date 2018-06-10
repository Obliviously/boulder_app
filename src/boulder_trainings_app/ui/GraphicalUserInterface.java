/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui;

import boulder_trainings_app.ui.components.Bottom;
import boulder_trainings_app.ui.components.Middle;
import boulder_trainings_app.ui.components.Top;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 * This class represents the GUI for the Application.
 *
 * @author fabian
 */
public class GraphicalUserInterface
{
    private String title;
    private int width;
    private int height;

    public GraphicalUserInterface(String title, int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void display()
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                JFrame frame = new JFrame();
                frame.setSize(width, height);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setAlwaysOnTop(true);
                frame.setMinimumSize(new Dimension(width, height));
                //this.setUndecorated(true);
                GraphicalUserInterface.loadComponents(frame);
                GraphicalUserInterface.addListeners(frame);
                frame.setVisible(true);
            }
        });
    }

    /**
     * Generates and adds all the components for the ui.
     */
    private static void loadComponents(JFrame frame)
    {
        Container pane = frame.getContentPane();
        final int HGAP = 0;
        final int VGAP = 0;
        BorderLayout borderLayout = new BorderLayout(HGAP, VGAP);
        pane.setLayout(borderLayout);
        Top top = new Top();
        Middle middle = new Middle();
        Bottom bottom = new Bottom();
        pane.add(top, BorderLayout.PAGE_START);
        pane.add(middle, BorderLayout.CENTER);
        pane.add(bottom, BorderLayout.PAGE_END);
        frame.pack();
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
