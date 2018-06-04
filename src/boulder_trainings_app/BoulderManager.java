/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.Section;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

/**
 *
 * @author Fabian Rauscher
 */
public class BoulderManager
{
    private static BoulderManager instance;
    private Path dataPath;

    private BoulderManager()
    {
    }

    public static BoulderManager getInstance()
    {
        if (BoulderManager.instance == null)
        {
            BoulderManager.instance = new BoulderManager();
        }
        return BoulderManager.instance;
    }

    public void setDataPath(Path dataPath)
    {
        this.dataPath = dataPath;
    }

    public void save(Boulder boulder)
    {
        Path path = Paths.get(dataPath.toString(), boulder.getSection().toInt() + "", boulder.getDate(), boulder.getId() + ".boulder");
        FileOutputStream fout;
        path.toFile().getParentFile().mkdirs();
        try
        {
            fout = new FileOutputStream(path.toFile());
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(boulder);
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(BoulderManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {

        }

    }

    public ArrayList<Boulder> loadBoulders(Section section, DateTime date)
    {
        //TODO
        return new ArrayList<Boulder>();
    }

}
