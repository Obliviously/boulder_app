/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.data.enums;

/**
 *
 * @author Fabian Rauscher
 */
public enum BoulderGrade
{
    ONE("1", "VB"),
    TWO("2", "VB"),
    THREE("3", "VB"),
    FOUR("4", "V0"),
    FOUR_PLUS("4+", "V0+"),
    FIVE("5", "V1"),
    FIVE_PLUS("5+", "V2"),
    SIX_A("6A", "V2"),
    SIX_A_PLUS("6A+", "V3"),
    SIX_B("6B", "V4"),
    SIX_B_PLUS("6B+", "V5"),
    SIX_C("6C", "V5"),
    SIX_C_PLUS("6C+", "V6"),
    SEVEN_A("7A", "V7"),
    SEVEN_A_PLUS("7A+", "V7"),
    SEVEN_B("7B", "V8"),
    SEVEN_B_PLUS("7B+", "V8"),
    SEVEN_C("7C", "V9"),
    SEVEN_C_PLUS("7C+", "V10"),
    EIGHT_A("8A", "V11"),
    EIGHT_A_PLUS("8A+", "V12"),
    EIGHT_B("8B", "V13"),
    EIGHT_B_PLUS("8B+", "V14"),
    EIGHT_C("8C", "V15"),
    EIGHT_C_PLUS("8C+", "V16"),
    NINE_A("9A", "V17");

    private final String frenchGrade;
    private final String usaGrade;

    private BoulderGrade(String frenchGrade, String usaGrade)
    {
        this.frenchGrade = frenchGrade;
        this.usaGrade = usaGrade;
    }

    public String getFrenchGrade()
    {
        return this.frenchGrade;
    }

    public String getUsaGrade()
    {
        return this.usaGrade;
    }
}
