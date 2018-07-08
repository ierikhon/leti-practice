package Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class ControlPanel extends JPanel
{
    private final JButton butStart = createButton ("Start Algorithm");
    private final JButton butStop = createButton ("Stop and Reset Algorithm");
    private final JButton butBack = createButton("Step Back");
    private final JButton butForward = createButton("Step Forward");
    private final JTextArea matrix = createTextEdit();

    JTextArea getMatrix()
    {
        return matrix;
    }

    ControlPanel()
    {
        super(null);
        matrix.setEditable(false);
        setLayout(new GridBagLayout());

        JLabel temp = createMessage("Control Panel");
        temp.setFont(new Font(temp.getFont().getName(), Font.ITALIC, 20));
        add(temp, new GridBagConstraints(0, 0, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        add(butStart, new GridBagConstraints(0, 1, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        add(butStop, new GridBagConstraints(0, 2, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        add(butBack, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        add(butForward, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        add(createMessage("Matrix"), new GridBagConstraints(0, 5, 2, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        add(matrix, new GridBagConstraints(0, 6, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
        setBorder(BorderFactory.createLineBorder(Color.GREEN, 8));

    }

    private JTextArea createTextEdit ()
    {
        JTextArea ta = new JTextArea();
        ta.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ta.setPreferredSize(new Dimension(250, 250));
        return ta;
    }

    private JLabel createMessage (String text)
    {
        JLabel te = new JLabel(text);
        te.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        te.setPreferredSize(new Dimension(125, 25));
        return te;
    }

    private JButton createButton (String text)
    {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        button.setPreferredSize(new Dimension(125, 25));
        return button;
    }

    void addStartButtonListener(ActionListener listener)
    {
        butStart.addActionListener(listener);
    }
    void addStopButtonListener(ActionListener listener) {butStop.addActionListener(listener);}
    void addForwardButtonListener(ActionListener listener) {butForward.addActionListener(listener);}
    void addBackButtonListener(ActionListener listener) {butBack.addActionListener(listener);}
}
