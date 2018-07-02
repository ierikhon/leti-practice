package Project.View;

import Swing.ControlPanel;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.*;



import javax.swing.*;
import java.awt.*;

public class View extends JFrame
{
    private static ControlPanel cPanel = new ControlPanel();

    public static ControlPanel getcPanel()
    {
        return cPanel;
    }

    public static Component getCanvas()
    {
        return canvas;
    }

    private static Component canvas;


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

        Graph graph = new SingleGraph("Tutorial", false, true);

        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        canvas = viewer.addDefaultView(false);
        canvas.setPreferredSize(new Dimension(1000, 1000));


        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.X_AXIS));
        rootPanel.setPreferredSize(new Dimension(1500, 940));
        rootPanel.add(cPanel, BorderLayout.EAST);
        rootPanel.add(canvas, BorderLayout.WEST);

        frame.setContentPane(rootPanel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(View::new);
    }

}
