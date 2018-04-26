/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app;

import javax.swing.JFrame;

/**
 *
 * @author fabian
 */
public class Boulder_App
{
    private final String PROGRAM_NAME = "Boulder Trainings App";
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private GraphicalUserInterface userInterface;

    public Boulder_App()
    {
        this.userInterface = new GraphicalUserInterface(PROGRAM_NAME, WIDTH, HEIGHT);
    }

    public void start()
    {
        this.userInterface.display();
    }

    public static void main(String[] args)
    {
        Boulder_App app = new Boulder_App();
        app.start();
    }
}
