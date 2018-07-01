package Swing;

import javax.swing.*;

public class ControlPanel extends JPanel
{
    public JButton getButStart()
    {
        return butStart;
    }

    public JButton getButStop()
    {
        return butStop;
    }

    public JButton getButBack()
    {
        return butBack;
    }

    public JButton getButForward()
    {
        return butForward;

    }

    public JButton getButAddNE()
    {
        return butAddNE;
    }

    private final JButton butStart = createButton ("Start Algorithm", 100, 50);
    private final JButton butStop = createButton ("Stop and Reset Algorithm", 100, 120);
    private final JCheckBox sbs = createCheckbox ("Step-by-step Solution", 180, 175);
    private final JButton butBack = createButton("Step Back", 0, 0);
    private final JButton butForward = createButton("Step Forward", 0, 0);
    private final JLabel grEd = createMessage ("Graph edit", 215, 315);
    private final JTextField node1name = createTextEdit ("Enter node name here",75, 360, 135, 30);
    private final JTextField node2name = createTextEdit("Enter node name here",280, 360, 135, 30);
    private final JButton butAddNE = createButton("Add Node / Edge", 100, 410);
    private final JLabel matrixMes = createMessage("Matrix(optional)", 205, 480);
    private final JTextField matrix = createTextEdit("Matrix will be here",50, 520, 400, 400);

    public ControlPanel()
    {
        super(null);

        butBack.setBounds(50, 240, 150, 50);
        butForward.setBounds(300, 240,150, 50);

        add(butStart);
        add(butStop);
        add(sbs);
        add(butBack);
        add(butBack);
        add(grEd);
        add(node1name);
        add(node2name);
        add(butAddNE);
        add(matrixMes);
        add(matrix);
    }

    private JTextField createTextEdit (String text, int x, int y, int w, int h)
    {
        JTextField ta = new Placeholder(text);
        ta.setBounds(x, y, w, h);
        ta.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        return ta;
    }

    private JLabel createMessage (String text, int x, int y)
    {
        JLabel te = new JLabel(text);
        te.setBounds(x, y, 300, 50);
        te.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        return te;
    }

    private JCheckBox createCheckbox (String text, int x, int y)
    {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setBounds(x, y, 300, 50);
        checkBox.setFocusPainted(false);
        checkBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        return checkBox;
    }

    private JButton createButton (String text, int x, int y)
    {
        JButton button = new JButton(text);
        button.setBounds(x, y, 300, 50);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        return button;
    }
}
