/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app;

import boulder_trainings_app.data.Data;
import boulder_trainings_app.ui.GraphicalUserInterface;

/**
 *
 * @author Fabian Rauscher
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

    /**
     * Starts the application.
     */
    public void start(Data data)
    {
        this.userInterface.observe(data);
        this.userInterface.display();
    }

    public static void main(String[] args)
    {
        Boulder_App app = new Boulder_App();
        Data data = new Data();
        app.start(data);
    }
}
