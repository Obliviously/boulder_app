/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.data;

import boulder_trainings_app.data.enums.BoulderColor;
import boulder_trainings_app.data.enums.BoulderGrade;
import boulder_trainings_app.data.enums.BoulderType;
import boulder_trainings_app.data.enums.BoulderSection;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.UUID;
import org.joda.time.DateTime;

/**
 *
 * @author Fabian Rauscher
 */
public class Boulder extends Observable implements Serializable
{
    private final String ID = UUID.randomUUID().toString();
    private DateTime date = new DateTime();
    private BoulderSection section = BoulderSection.ONE;
    private BoulderColor color = BoulderColor.PINK;
    private final ArrayList<Vector3f> positions = new ArrayList<>();
    private boolean isHighlighted = false;
    private String name = "NAME";
    private BoulderGrade grade;
    private BoulderType type;

    public ArrayList<Vector3f> getPositions()
    {
        return new ArrayList<>(positions);
    }

    public boolean addPosition(Vector3f position)
    {
        return positions.add(position);

    }

    public Vector3f getLastPosition()
    {
        return positions.get(positions.size() - 1);
    }

    public Vector3f getNthLastPosition(int n)
    {
        if (n > positions.size())
        {
            return null;
        }
        return positions.get(positions.size() - n);
    }

    public BoulderColor getColor()
    {
        return this.color;
    }

    public void setColor(BoulderColor color)
    {
        setChanged();
        this.color = color;
        notifyObservers();
    }

    public String getId()
    {
        return ID;
    }

    public BoulderSection getSection()
    {
        return section;
    }

    public void setSection(BoulderSection section)
    {
        setChanged();
        this.section = section;
        notifyObservers();
    }

    public void setDate(DateTime date)
    {
        this.date = date;
    }

    public DateTime getDate()
    {
        return this.date;
    }

    public void setHighlighted(boolean isHighlighted)
    {
        this.isHighlighted = isHighlighted;
    }

    public boolean isHighlighted()
    {
        return this.isHighlighted;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        setChanged();
        this.name = name;
        notifyObservers();
    }

    public BoulderGrade getGrade()
    {
        return this.grade;
    }

    public void setGrade(BoulderGrade grade)
    {
        this.grade = grade;
    }

    public BoulderType getType()
    {
        return this.type;
    }

    public void setType(BoulderType type)
    {
        this.type = type;
    }

    @Override
    public int hashCode()
    {
        return this.positions.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return (obj instanceof Boulder) && (((Boulder) obj).getId()).equals(this.getId());
    }

}
