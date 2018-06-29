package Swing;

import javax.swing.*;

public class ControlPanel extends JPanel
{
    public ControlPanel()
    {
        super(null);
        add(createButton ("Start Algorithm", 100, 50));
        add(createButton ("Stop and Reset Algorithm", 100, 120));
        add(createCheckbox ("Step-by-step Solution", 180, 175));
        JButton temp = createButton("Step Back", 0, 0);
        temp.setBounds(50, 240, 150, 50);
        add(temp);
        temp = createButton("Step Forward", 0, 0);
        temp.setBounds(300, 240,150, 50);
        add(temp);
        add(createMessage ("Graph edit", 215, 315));
        add(createTextEdit ("Enter node name here",75, 360, 135, 30));
        add(createTextEdit("Enter node name here",280, 360, 135, 30));
        add(createButton("Add Node / Edge", 100, 410));
        add(createMessage("Matrix(optional)", 205, 465));
        add(createTextEdit("Matrix will be here",0, 500, 500, 500));
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
