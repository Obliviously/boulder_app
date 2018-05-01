/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui;

import boulder_trainings_app.data.Data;
import boulder_trainings_app.ui.components.Bottom;
import boulder_trainings_app.ui.components.Middle;
import boulder_trainings_app.ui.components.Top;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 * This class represents the GUI for the Application.
 *
 * @author fabian
 */
public class GraphicalUserInterface extends JFrame
{
    public GraphicalUserInterface(String title, int width, int height)
    {
        super(title);
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setUndecorated(true);
        this.loadComponents(this.getContentPane());

        this.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e){}
 

            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    System.exit(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e){}
        });
    }

    public void display()
    {
        this.setVisible(true);
    }

    public void observe(Data data)
    {

    }

    /**
     * Generates and adds all the components for the ui.
     */
    private void loadComponents(Container pane)
    {
        final int HGAP = 5;
        final int VGAP = 5;
        BorderLayout borderLayout = new BorderLayout(HGAP, VGAP);
        pane.setLayout(borderLayout);
        Top top = new Top();
        Middle middle = new Middle();
        Bottom bottom = new Bottom();
        pane.add(top, BorderLayout.PAGE_START);
        pane.add(middle, BorderLayout.CENTER);
        pane.add(bottom, BorderLayout.PAGE_END);

    }
}
