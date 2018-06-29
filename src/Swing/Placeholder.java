package Swing;

import javax.swing.*;
import java.awt.*;

public class Placeholder extends JTextField
{
    private String phText;

    Placeholder(String text)
    {
        super();
        phText = text;
    }


    protected void paintComponent(java.awt.Graphics g)
    {
        super.paintComponent(g);
        if(getText().isEmpty()){
            Graphics2D g2 = (Graphics2D)g.create();
            g2.setBackground(Color.BLACK);
            g2.setColor(Color.gray);
            g2.drawString(phText, 7, 14);
            g2.dispose();
        }
    }
}
