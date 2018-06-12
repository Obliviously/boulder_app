/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.data;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Fabian Rauscher
 */
public class Const
{
    public final static Path DATAPATH = Paths.get(".\\data\\");
    public final static String PROGRAM_NAME = "Boulder Trainings App";
    public final static int MIN_WIDTH = 1280;
    public final static int MIN_HEIGHT = 720;

    //BoulderInfo GUI
    public final static String BOULDER_NAME_LABEL = "Name";
    public final static String BOULDER_COLOR_LABEL = "Color";
    public final static String BOULDER_SECTION_LABEL = "Section";
    public final static String BOULDER_DATE_LABEL = "Date";
    public final static String BOULDER_TYPE_LABEL = "Type";
    public final static String BOULDER_GRADE_LABEL = "Grade";

}
