package boulder_trainings_app.ui.containers.components;

import boulder_trainings_app.data.Boulder;
import boulder_trainings_app.data.enums.ProgramState;
import boulder_trainings_app.utils.Consts;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import boulder_trainings_app.ui.StateDependent;
import boulder_trainings_app.ui.utils.UIUtilities;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Fabian Rauscher
 */
public class BoulderSelect extends JPanel implements StateDependent

{
    private final JPanel propertiesContainer;

    private final JLabel nameValue;
    private final JLabel dateValue;
    private final JLabel colorValue;
    private final JLabel typeValue;
    private final JLabel gradeValue;
    private final JLabel sectionValue;
    private final JButton addCompletion;

    private final JLabel nothingSelectedMessage;
    private Boulder boulder = null;

    private Frame parentFrame;

    public BoulderSelect()
    {
        super();
        JLabel nameLabel = new JLabel(Consts.BOULDER_NAME_LABEL);
        nameValue = new JLabel();
        JLabel dateLabel = new JLabel(Consts.BOULDER_DATE_LABEL);
        dateValue = new JLabel();
        JLabel colorLabel = new JLabel(Consts.BOULDER_COLOR_LABEL);
        colorValue = new JLabel();
        JLabel typeLabel = new JLabel(Consts.BOULDER_TYPE_LABEL);
        typeValue = new JLabel();
        JLabel gradeLabel = new JLabel(Consts.BOULDER_GRADE_LABEL);
        gradeValue = new JLabel();
        JLabel sectionLabel = new JLabel(Consts.BOULDER_SECTION_LABEL);
        sectionValue = new JLabel();

        addCompletion = new JButton("Add Completion");

        addCompletion.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                parentFrame = UIUtilities.getParentFrame(BoulderSelect.this);
                AddCompletionDialog acd = new AddCompletionDialog(parentFrame, boulder.getName());
            }
        });

        nothingSelectedMessage = new JLabel(Consts.NOTHING_SELECTED_MESSAGE);
        nothingSelectedMessage.setFont(new Font("Arial", Font.BOLD, 20));
        nothingSelectedMessage.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        propertiesContainer = new JPanel(new GridLayout(20, 2));
        propertiesContainer.add(nameLabel);
        propertiesContainer.add(nameValue);
        propertiesContainer.add(dateLabel);
        propertiesContainer.add(dateValue);
        propertiesContainer.add(colorLabel);
        propertiesContainer.add(colorValue);
        propertiesContainer.add(typeLabel);
        propertiesContainer.add(typeValue);
        propertiesContainer.add(gradeLabel);
        propertiesContainer.add(gradeValue);
        propertiesContainer.add(sectionLabel);
        propertiesContainer.add(sectionValue);
        propertiesContainer.add(addCompletion);
        super.add(nothingSelectedMessage);

        COMPONENTS.add(this);
    }

    @Override
    public void selectBoulder(Boulder boulder)
    {
        this.removeAll();
        if (boulder != null)
        {
            this.add(propertiesContainer);
            this.boulder = boulder;
            initValues();
        }
        else
        {
            this.add(nothingSelectedMessage);
        }
        this.repaint();
        this.validate();
    }

    private void initValues()
    {
        nameValue.setText(boulder.getName());
        dateValue.setText(boulder.getDate().toString());
        colorValue.setText(boulder.getColor().toString());
        typeValue.setText(boulder.getType().toString());
        gradeValue.setText(boulder.getGrade().toString());
        sectionValue.setText(boulder.getSection().toString());
    }

    @Override
    public void addBoulder(Boulder boulder)
    {
    }

    @Override
    public void removeBoulder(Boulder boulder)
    {
    }

    @Override
    public void editBoulder(Boulder boulder)
    {
    }

    @Override
    public void changeState(ProgramState programState)
    {
    }

    @Override
    public void updateBoulder(Boulder boulder)
    {
    }
}
