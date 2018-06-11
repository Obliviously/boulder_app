/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.data;

import com.jme3.math.ColorRGBA;
import java.awt.Color;

/**
 *
 * @author Fabian Rauscher
 */
public enum BoulderColor
{
    PINK(Color.PINK),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    ORANGE(Color.ORANGE),
    RED(Color.RED),
    BLACK(Color.BLACK),
    WHITE(Color.WHITE),
    BROWN(new Color(176, 82, 2));
    
    Color color;
    ColorRGBA colorRGBA;
    
    private BoulderColor(Color color)
    {
        this.color = color;
        this.colorRGBA = new ColorRGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        
    }
    
    public Color toColor()
    {
        return this.color;
    }
    
    public ColorRGBA toColorRGBA()
    {
        return this.colorRGBA;
    }
}
