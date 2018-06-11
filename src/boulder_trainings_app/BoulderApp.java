/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app;

import boulder_trainings_app.data.Const;
import boulder_trainings_app.data.Section;
import boulder_trainings_app.ui.GraphicalUserInterface;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.joda.time.DateTime;

/**
 *
 * @author Fabian Rauscher
 */
public class BoulderApp
{
    private final GraphicalUserInterface userInterface;

    public BoulderApp()
    {       
        this.userInterface = new GraphicalUserInterface(Const.PROGRAM_NAME, Const.MIN_WIDTH, Const.MIN_HEIGHT);
    }
  
    public void start()
    {
        this.userInterface.display();
        BoulderManager.loadBoulder(DateTime.now());
    }

    public static void main(String[] args)
    {
        BoulderApp app = new BoulderApp();
        app.start();
    }
}
