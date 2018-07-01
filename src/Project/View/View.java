package Project.View;

import Swing.ControlPanel;

import javax.swing.*;
import java.awt.*;


public class View extends JFrame
{
    private final static JPanel cPanel = new ControlPanel();
    private final static Component canvas = new JPanel()
    {
        @Override
        public void paint(Graphics g)
        {
            g.setColor(Color.BLUE);
            g.fillRect(50, 50, 900, 900);
        }
    };

    private View()
    {
        JFrame frame = new JFrame("Test frame");

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        exitItem.addActionListener(e -> System.exit(0));

        JMenu aboutMenu = new JMenu("Credits");

        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);

        frame.setJMenuBar(menuBar);
        cPanel.setPreferredSize(new Dimension(500, 1000));
        canvas.setPreferredSize(new Dimension(1000, 1000));


        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.X_AXIS));
        rootPanel.setPreferredSize(new Dimension(1500, 1000));
        rootPanel.add(cPanel, BorderLayout.EAST);
        rootPanel.add(canvas, BorderLayout.WEST);

        frame.setContentPane(rootPanel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(View::new);
    }
}
