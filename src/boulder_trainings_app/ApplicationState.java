package boulder_trainings_app;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.enums.BoulderSection;
import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.ui.StateDependent;
import static boulder_trainings_app.ui.StateDependent.COMPONENTS;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javafx.collections.ListChangeListener;
import org.joda.time.DateTime;

/**
 *
 * @author Fabian Rauscher
 */
public class ApplicationState
{
    private static final Logger LOGGER = Logger.getLogger(ApplicationState.class.getName());

    private final ArrayList<Boulder> boulderList = new ArrayList<>();
    private ProgramState programState = ProgramState.SELECT;
    private static ApplicationState instance;

    private ApplicationState()
    {
        COMPONENTS.addListener((ListChangeListener.Change<? extends StateDependent> c) ->
        {
            c.next();
            List<StateDependent> newItems = (List<StateDependent>) c.getAddedSubList();
            for (Boulder b : boulderList)
            {
                for (StateDependent sd : newItems)
                {
                    sd.addBoulder(b);
                }
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
            this.programState = programState;
            COMPONENTS.forEach((c) -> c.changeState(programState));
        }
    }

    public void editBoulder(Boulder boulder)
    {
        COMPONENTS.forEach((c) -> c.editBoulder(boulder));
    }

    public void addBoulder(Boulder boulder)
    {
        COMPONENTS.forEach((c) -> c.addBoulder(boulder));
    }

    public void addBoulders(ArrayList<Boulder> boulderList)
    {
        boulderList.forEach((b) -> COMPONENTS.forEach((c) -> c.addBoulder(b)));
        this.boulderList.addAll(boulderList);
    }

    public void removeBoulders(ArrayList<Boulder> boulderList)
    {
        boulderList.forEach((b) -> COMPONENTS.forEach((c) -> c.removeBoulder(b)));
        this.boulderList.addAll(boulderList);
    }

    public void saveBoulder(Boulder boulder)
    {
        BoulderFileManager.saveBoulder(boulder);
    }

    public void loadBoulder(DateTime date)
    {
        removeBoulders(boulderList);
        boulderList.clear();
        boulderList.addAll(BoulderFileManager.loadBoulder(date));
        addBoulders(boulderList);
    }

    public void removeSection(BoulderSection section)
    {
        for (Boulder boulder : boulderList)
        {
            if (boulder.getSection().equals(section))
            {
                boulderList.remove(boulder);
                removeBoulder(boulder);
            }
        }
    }

    public void selectBoulder(Boulder boulder)
    {
        if (boulder != null)
        {
            COMPONENTS.forEach((c) -> c.selectBoulder(boulder));
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

    public void highLightBoulder(Boulder boulder)
    {
        if (boulder != null && !boulder.isHighlighted())
        {
            COMPONENTS.forEach((c) -> c.highLightBoulder(boulder));
        }
    }

    public void removeBoulder(Boulder boulder)
    {
        COMPONENTS.forEach((c) -> c.removeBoulder(boulder));
    }
}
