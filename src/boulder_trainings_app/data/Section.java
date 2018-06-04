/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.data;

/**
 *
 * @author Fabian Rauscher
 */
public enum Section
{
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8);
    private final int number;

    private Section(int number)
    {
        this.number = number;
    }

    public int toInt()
    {
        return this.number;
    }
}
