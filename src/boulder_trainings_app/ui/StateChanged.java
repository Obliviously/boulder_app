/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.enums.ProgramState;
import java.util.ArrayList;

/**
 *
 * @author Fabian Rauscher
 */
public interface StateChanged
{
    public final ArrayList<StateChanged> components = new ArrayList<>();

    public void addBoulder(Boulder boulder);

    public void removeBoulder(Boulder boulder);

    public void highLightBoulder(Boulder boulder);

    public void selectBoulder(Boulder boulder);

    public void editBoulder(Boulder boulder);

    public void saveBoulder(Boulder boulder);

    public void changeState(ProgramState programState);
}
