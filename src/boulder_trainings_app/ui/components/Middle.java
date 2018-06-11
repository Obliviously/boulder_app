/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boulder_trainings_app.ui.components;

import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author fabian
 */
public class Middle extends JPanel
{
    public Middle()
    {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(gbl);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 0;
        c.ipadx = 0;
        c.gridy = 0;
        c.weighty = 1.0;
        //c.insets = new Insets(0, 3, 0, 3);

        c.weightx = 0.25;
        c.gridx = 0;
        Sections sections = new Sections();
        sections.setPreferredSize(new Dimension(1, 1));
        this.add(sections, c);
        
        c.weightx = 0.5;
        c.gridx = 1;
        
        View3dContainer view3dContainer = new View3dContainer();
        view3dContainer.setPreferredSize(new Dimension(1, 1));
        
        this.add(view3dContainer, c);
        
        c.weightx = 0.25;
        c.gridx = 2;
        BoulderInfo statistics = new BoulderInfo();
        statistics.setPreferredSize(new Dimension(1, 1));
        
        this.add(statistics, c);
    }
    
}
