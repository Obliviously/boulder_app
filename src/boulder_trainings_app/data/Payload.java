/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.data;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabian Rauscher
 */
public class Payload
{
    private static final Logger LOGGER = Logger.getLogger(Payload.class.getName());

    public enum State
    {
        ADDED_BOULDER(Boulder.class),
        REMOVED_BOULDER(Boulder.class),
        ADDED_BOULDER_LIST(ArrayList.class),
        REMOVED_BOULDER_LIST(ArrayList.class),
        HIGHLIGHT_BOULDER(String.class),
        SELECT_BOULDER(Boulder.class);

        Class dataClass;

        private State(Class dataClass)
        {
            this.dataClass = dataClass;
        }

        public Class getDataClass()
        {
            return this.dataClass;
        }
    };

    private State state;
    private Object data;

    public Payload(State state, Object data)
    {

        this.state = state;
        this.data = data;
        if (data.getClass() != state.getDataClass())
        {
            LOGGER.log(Level.SEVERE, "Data object of payload doesn't match the expected type!");
        }
    }

    public State getState()
    {
        return state;
    }

    public Object getData()
    {
        return data;
    }

}
