/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.data.enums;

import com.jme3.math.ColorRGBA;
import java.awt.Color;

/**
 *
 * @author Fabian Rauscher
 */
public enum BoulderColor
{

    PINK(new Color(255, 0, 255)),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    ORANGE(new Color(255, 109, 25)),
    RED(Color.RED),
    BLACK(Color.BLACK),
    WHITE(Color.WHITE),
    BROWN(new Color(114, 69, 43));

    Color color;
    ColorRGBA colorRGBA;

    private BoulderColor(Color color)
    {
        this.color = color;
        this.colorRGBA = new ColorRGBA(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha());
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
