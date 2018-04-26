/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app;

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
    }

    public void display()
    {
        this.setVisible(true);
    }
}
