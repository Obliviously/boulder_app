/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui;

import boulder_trainings_app.ApplicationState;
import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.ui.containers.BottomContainer;
import boulder_trainings_app.ui.containers.LeftContainer;
import boulder_trainings_app.ui.containers.RightContainer;
import boulder_trainings_app.ui.containers.TopContainer;
import boulder_trainings_app.ui.containers.MiddleContainer;
import boulder_trainings_app.utils.Payload;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JFrame;

/**
 * This class represents the GUI for the Application.
 *
 * @author Fabian Rauscher
 */
public class SwingUserInterface implements GraphicalUserInterface, StateChanged
{
    @Override
    public void display(String title, int minWidth, int minHeight)
    {
        java.awt.EventQueue.invokeLater(() ->
        {
            JFrame frame = new JFrame();
            frame.setTitle(title);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setUndecorated(true);
            loadComponents(frame);
            frame.setVisible(true);
            frame.setSize(minWidth, minHeight);
            frame.setMinimumSize(new Dimension(minWidth, minHeight));
        });
    }

    @Override
    public void addBoulder(Boulder boulder)
    {
        components.forEach((c) -> c.addBoulder(boulder));
    }

    @Override
    public void removeBoulder(Boulder boulder)
    {
        components.forEach((c) -> c.removeBoulder(boulder));
    }

    @Override
    public void highLightBoulder(Boulder boulder)
    {
        components.forEach((c) -> c.highLightBoulder(boulder));
    }

    @Override
    public void selectBoulder(Boulder boulder)
    {
        components.forEach((c) -> c.selectBoulder(boulder));
    }

    @Override
    public void editBoulder(Boulder boulder)
    {
        components.forEach((c) -> c.editBoulder(boulder));
    }

    @Override
    public void saveBoulder(Boulder boulder)
    {
        components.forEach((c) -> c.saveBoulder(boulder));
    }

    @Override
    public void changeState(ProgramState programState)
    {
        components.forEach((c) -> c.changeState(programState));
    }

    private void loadComponents(JFrame frame)
    {
        Container pane = frame.getContentPane();
        final int HGAP = 5;
        final int VGAP = 0;
        BorderLayout borderLayout = new BorderLayout(HGAP, VGAP);
        pane.setLayout(borderLayout);
        TopContainer top = new TopContainer();
        MiddleContainer middle = new MiddleContainer();
        LeftContainer left = new LeftContainer();
        RightContainer right = new RightContainer();
        BottomContainer bottom = new BottomContainer();
        pane.add(top, BorderLayout.PAGE_START);
        pane.add(left, BorderLayout.LINE_START);
        pane.add(middle, BorderLayout.CENTER);
        pane.add(right, BorderLayout.LINE_END);
        pane.add(bottom, BorderLayout.PAGE_END);

        components.add(top);
        components.add(middle);
        components.add(left);
        components.add(right);
        components.add(bottom);
    }
}
