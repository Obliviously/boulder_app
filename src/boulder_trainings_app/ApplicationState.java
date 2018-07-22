package boulder_trainings_app;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.enums.BoulderSection;
import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.ui.BoulderDependent;
import boulder_trainings_app.ui.SelectDependent;
import boulder_trainings_app.ui.StateDependent;
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
public class ApplicationState
{
    private static final Logger LOGGER = Logger.getLogger(ApplicationState.class.getName());

    private final Set<Boulder> boulderList = new HashSet<>();
    private ProgramState programState = ProgramState.SELECT;
    private static ApplicationState instance;
    private static Boulder selectedBoulder = null;
    private static DateTime gymDate = null;

    private ApplicationState()
    {
        BoulderDependent.COMPONENTS.addListener((SetChangeListener.Change<? extends BoulderDependent> c) ->
        {
            BoulderDependent bd = (BoulderDependent) c.getElementAdded();
            for (Boulder b : boulderList)
            {
                bd.addBoulder(b);
            }
        });
    }

    public static ApplicationState getInstance()
    {
        if (ApplicationState.instance == null)
        {
            ApplicationState.instance = new ApplicationState();
        }
        return ApplicationState.instance;
    }

    public void changeState(ProgramState programState)
    {
        if (this.programState != programState)
        {

            StateDependent.COMPONENTS.forEach((c) -> c.changeState(programState));

            if (programState == ProgramState.CREATE && selectedBoulder != null)
            {
                selectBoulder(null);
            }
            if (this.programState == ProgramState.CREATE)
            {
                selectBoulder(null);
            }

            this.programState = programState;
        }
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

        if (boulderList.contains(boulder))
        {
            BoulderDependent.COMPONENTS.forEach((c) -> c.updateBoulder(boulder));
        }
        else
        {
            addBoulder(boulder);
        }
        changeState(ProgramState.SELECT);
        selectBoulder(boulder);
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

    /**
     * Selects the given boulder. If boulder is null everything gets deselected.
     *
     * @param boulder The boulder to select.
     *
     */
    public void selectBoulder(Boulder boulder)
    {
        SelectDependent.COMPONENTS.forEach(c -> c.selectBoulder(boulder));
        selectedBoulder = boulder;
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

    public void deleteBoulder()
    {
        System.out.println("boulder_trainings_app.ApplicationState.deleteBoulder()");
        deleteBoulder(selectedBoulder);
    }

    public void deleteBoulder(Boulder boulder)
    {
        if (boulder != null)
        {
            if (boulder == selectedBoulder)
            {
                selectedBoulder = null;
            }
            boulderList.remove(boulder);
            BoulderFileManager.deleteBoulder(boulder);
            BoulderDependent.COMPONENTS.forEach((c) -> c.removeBoulder(boulder));
            SelectDependent.COMPONENTS.forEach((c) -> c.selectBoulder(null));
        }
    }

    public DateTime getDate()
    {
        return gymDate;
    }
}
