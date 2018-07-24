package boulder_trainings_app.controller;

import boulder_trainings_app.BoulderFileManager;
import boulder_trainings_app.controller.interfaces.BoulderDependent;
import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.enums.BoulderSection;
import boulder_trainings_app.data.enums.ProgramState;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javafx.collections.SetChangeListener;
import org.joda.time.DateTime;

/**
 *
 * @author Fabian Rauscher
 */
public class BoulderController
{
    private static final Logger LOGGER = Logger.getLogger(BoulderController.class.getName());

    private static BoulderController instance;
    private final Set<Boulder> boulderList = new HashSet<>();
    private static DateTime gymDate = null;

    private BoulderController()
    {
        BoulderDependent.COMPONENTS.addListener((SetChangeListener.Change<? extends BoulderDependent> c) ->
        {
            BoulderDependent d = (BoulderDependent) c.getElementAdded();
            for (Boulder b : boulderList)
            {
                d.addBoulder(b);
            }
        });
    }

    public static BoulderController getInstance()
    {
        if (BoulderController.instance == null)
        {
            BoulderController.instance = new BoulderController();
        }
        return BoulderController.instance;
    }

    public void addBoulder(Boulder boulder)
    {
        if (boulderList.add(boulder))
        {
            BoulderDependent.COMPONENTS.forEach((c) -> c.addBoulder(boulder));
        }
    }

    public void addBoulders(ArrayList<Boulder> boulderList)
    {
        boulderList.forEach((b) -> addBoulder(b));
    }

    public void removeBoulders(Set<Boulder> boulderList)
    {
        boulderList.forEach((b) -> BoulderDependent.COMPONENTS.forEach((c) ->
        {
            c.removeBoulder(b);
            this.boulderList.remove(b);
        }
        ));
    }

    public void removeAllBoulders()
    {
        boulderList.forEach((b) -> BoulderDependent.COMPONENTS.forEach((c) ->
        {
            c.removeBoulder(b);
            boulderList.remove(b);
        }
        ));
    }

    public void saveBoulder(Boulder boulder)
    {
        BoulderFileManager.saveBoulder(boulder);
    }

    public void updateBoulder(Boulder boulder)
    {
        if (!boulderList.contains(boulder))
        {
            addBoulder(boulder);
        }
        else
        {
            BoulderDependent.COMPONENTS.forEach((c) -> c.updateBoulder(boulder));
        }
    }

    public void loadBoulder(DateTime date)
    {
        if (gymDate == null || !date.equals(gymDate))
        {
            gymDate = date;
            removeBoulders(boulderList);
            addBoulders(BoulderFileManager.loadBoulder(gymDate));
        }
    }

    public void removeSection(BoulderSection section)
    {
        for (Boulder boulder : boulderList)
        {
            if (boulder.getSection().equals(section))
            {
                deleteBoulder(boulder);
            }
            boulderList.remove(boulder);
        }
    }

    public Boulder getBoulderById(String boulderId)
    {
        for (Boulder b : boulderList)
        {
            if (b.getId().equals(boulderId))
            {
                return b;
            }
        }
        return null;
    }

    public void deleteBoulder(Boulder boulder)
    {
        if (boulder != null)
        {
            boulderList.remove(boulder);
            BoulderFileManager.deleteBoulder(boulder);
            BoulderDependent.COMPONENTS.forEach((c) -> c.removeBoulder(boulder));
        }
    }

    public DateTime getDate()
    {
        return gymDate;
    }

}
