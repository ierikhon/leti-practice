package Swing;

import Project.Controller.Controller;
import Project.Model.GraphModel;
import Project.View.View;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.*;



import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Launcher extends JFrame
{
    private static ControlPanel cPanel = new ControlPanel();
    private static Component canvas;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Launcher().setVisible(true));
    }

    private Launcher()
    {

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

        setJMenuBar(menuBar);
        cPanel.setPreferredSize(new Dimension(500, 1000));

        Graph graph = new SingleGraph("Tutorial", false, true);

        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        canvas = viewer.addDefaultView(false);
        canvas.setPreferredSize(new Dimension(1000, 1000));

        initListeners();

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.X_AXIS));
        rootPanel.setPreferredSize(new Dimension(1500, 940));
        rootPanel.add(cPanel, BorderLayout.EAST);
        rootPanel.add(canvas, BorderLayout.WEST);

        setContentPane(rootPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initListeners()
    {
        Scanner scanner = new Scanner(System.in);
        GraphModel model = GraphModel.restore(scanner);

        View view = new View(model);
        Graph graph = view.getGraph();
        Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        canvas = viewer.addDefaultView(false);
        canvas.setPreferredSize(new Dimension(1000, 1000));

        Controller controller = new Controller (model, view);
    }
}
