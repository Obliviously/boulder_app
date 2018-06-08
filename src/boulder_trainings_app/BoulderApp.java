/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app;

import boulder_trainings_app.data.Data;
import boulder_trainings_app.ui.GraphicalUserInterface;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Fabian Rauscher
 */
public class BoulderApp
{
    private final Path dataPath = Paths.get(".\\data\\");

    private final String PROGRAM_NAME = "Boulder Trainings App";
    private final int WIDTH = 640;
    private final int HEIGHT = 360;

    private final GraphicalUserInterface userInterface;

    public BoulderApp()
    {
        BoulderManager boulderManager = BoulderManager.getInstance();
        boulderManager.setDataPath(dataPath);

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
        BoulderApp app = new BoulderApp();
        Data data = new Data();
        app.start(data);
    }
}
