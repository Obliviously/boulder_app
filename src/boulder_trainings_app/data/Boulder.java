/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.data;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import org.joda.time.DateTime;

/**
 *
 * @author Fabian Rauscher
 */
public class Boulder implements Serializable
{
    private String id = UUID.randomUUID().toString();
    private DateTime date = new DateTime();
    private Section section = Section.ONE;
    private BoulderColor color = BoulderColor.PINK;
    private final ArrayList<Vector3f> positions = new ArrayList<>();
    private boolean isHighlighted = false;
    private String name = null;

    public Boulder()
    {
    }

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
        this.color = color;
    }

    public String getId()
    {
        return id;
    }

    public Section getSection()
    {
        return section;
    }

    public void setSection(Section section)
    {
        this.section = section;
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
        this.name = name;
    }

}
