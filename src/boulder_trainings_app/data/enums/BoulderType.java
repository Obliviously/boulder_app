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
public enum BoulderType
{
    JUGS("Jugs"), MINI_JUGS("Mini Jugs"), SLOPERS("Slopers"), POCKETS("Pockets"), PINCHES("Pinches"), CRIMPS("Crimps"), EDGES("Edges");

    private final String name;

    private BoulderType(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
