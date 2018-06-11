/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.containers;

import boulder_trainings_app.data.Const;
import boulder_trainings_app.data.Payload;
import boulder_trainings_app.data.ProgramData;
import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.ui.containers.components.BoulderEdit;
import boulder_trainings_app.ui.containers.components.BoulderSelect;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author Fabian Rauscher
 */
public class RightContainer extends JPanel implements Observer
{
    private final BoulderSelect boulderInfo;
    private final BoulderEdit boulderEdit;

    public RightContainer()
    {
        super();
        ProgramData.getInstance().addObserver(this);

        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setPreferredSize(new Dimension(Const.MIN_WIDTH / 5, HEIGHT));

        boulderInfo = new BoulderSelect();
        boulderEdit = new BoulderEdit();

        super.add(boulderInfo);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        if (o instanceof ProgramData)
        {
            if (arg instanceof Payload)
            {
                Payload payload = (Payload) arg;
                switch (payload.getState())
                {
                case PROGRAM_STATE_CHANGED:
                    ProgramState state = (ProgramState) payload.getData();
                    this.removeAll();
                    if (state == ProgramState.EDIT)
                    {
                        this.add(boulderEdit);
                        this.repaint();
                        this.validate();                        
                    }
                    if (state == ProgramState.SELECT)
                    {
                        this.add(boulderInfo);
                        this.repaint();
                        this.validate();
                    }
                    break;
                default:
                    break;
                }
            }
        }
    }
}
