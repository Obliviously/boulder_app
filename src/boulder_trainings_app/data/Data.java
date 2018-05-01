/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.data;

import java.util.Observable;

/**
 *
 * @author fabian
 */
public class Data extends Observable
{    
    private boolean data = false;

    public void changeData()
    {
        setChanged();
        data = !data;
        notifyAll();
    }

    public boolean getData()
    {
        return this.data;
    }
    
}
