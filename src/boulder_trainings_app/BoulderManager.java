/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.BoulderList;
import boulder_trainings_app.data.Const;
import boulder_trainings_app.data.enums.Section;
import com.jme3.math.Vector3f;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joda.time.DateTime;

/**
 *
 * @author Fabian Rauscher
 */
public class BoulderManager
{
    public static Boulder createBoulder(Vector3f startPoint)
    {
        Boulder boulder = new Boulder();
        boulder.addPosition(startPoint);
        BoulderList.getInstance().setNewBoulder(boulder);
        return boulder;
    }

    public static void saveBoulder(Boulder boulder)
    {
        Path path = Paths.get(Const.DATAPATH.toString(), boulder.getDate().getYear() + "", "" + boulder.getDate().getWeekOfWeekyear(), "" + boulder.getSection().toInt(), boulder.getId() + ".boulder");
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
        BoulderList.getInstance().addBoulder(boulder);
    }

    public static void loadBoulder(DateTime date)
    {
        for (Section section : Section.values())
        {
            loadBoulderSection(date, section);
        }
    }

    public static void loadBoulderSection(DateTime date, Section section)
    {
        ArrayList<Boulder> boulders = new ArrayList<>();
        int year = date.getYear();
        final int MIN_YEAR = findMinYear();
        int week = date.getWeekOfWeekyear();

        Path latestSectionPath = getPath(year, week, section, MIN_YEAR);
        if (latestSectionPath != null)
        {
            File[] boulderFiles = latestSectionPath.toFile().listFiles();
            FileInputStream streamIn;
            ObjectInputStream objectInputStream;
            Boulder boulder;
            for (File file : boulderFiles)
            {
                try
                {
                    streamIn = new FileInputStream(file);
                    objectInputStream = new ObjectInputStream(streamIn);
                    boulder = (Boulder) objectInputStream.readObject();
                    boulders.add(boulder);
                }
                catch (FileNotFoundException ex)
                {
                    Logger.getLogger(BoulderManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IOException | ClassNotFoundException ex)
                {
                    Logger.getLogger(BoulderManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            BoulderList.getInstance().addBoulders(boulders);
        }
    }

    private static int findMinYear()
    {
        int minYear = Integer.MAX_VALUE;
        int fileYear;
        for (File f : Const.DATAPATH.toFile().listFiles())
        {
            fileYear = Integer.parseInt(f.getName());
            if (fileYear < minYear)
            {
                minYear = fileYear;
            }
        }
        return minYear;
    }

    private static Path getPath(int year, int week, Section section, int MIN_YEAR)
    {
        Path path = Paths.get(Const.DATAPATH.toString(), year + "", week + "", section.toInt() + "");

        if (path.toFile().exists())
        {
            return path;
        }
        if (week == 1)
        {
            if (year <= MIN_YEAR)
            {
                return null;
            }
            week = getNumberOfWeeksForYear(year - 1);
            year--;
        }
        else
        {
            week--;
        }
        return getPath(year, week, section, MIN_YEAR);
    }

    private static int getNumberOfWeeksForYear(int year)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, 1, 1);
        return calendar.getActualMaximum(Calendar.WEEK_OF_YEAR);
    }
}
