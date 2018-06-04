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
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Fabian Rauscher
 */
public class Boulder implements Serializable
{
    private final int id;
    private DateTime date = new DateTime();
    private Section section;
    private ColorRGBA color;
    private final ArrayList<Vector3f> marks = new ArrayList<>();

    public Boulder(int id, ColorRGBA color)
    {
        this.section = Section.EIGHT;
        this.id = id;
        this.color = color;
    }

    public boolean addMark(Vector3f mark)
    {
        return this.marks.add(mark);
    }

    public Vector3f getLastMark()
    {
        return marks.get(marks.size() - 1);
    }

    public Vector3f getNthLastMark(int n)
    {
        if (n >= marks.size())
        {
            return null;
        }
        return marks.get(marks.size() - n);
    }

    public ColorRGBA getColor()
    {
        return this.color;
    }

    public void setColor(ColorRGBA color)
    {
        this.color = color;
    }

    public int getId()
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

    public String getDate()
    {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy");
        return this.date.toString(dtf);
    }

}
