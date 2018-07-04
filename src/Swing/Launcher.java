package Swing;


import Project.Controller.Controller;
import Project.Model.GraphModel;
import Project.View.View;

import org.graphstream.ui.view.*;



import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Launcher extends JFrame
{
    private static ControlPanel cPanel = new ControlPanel();
    private static Component canvas = new JPanel();
    private static JPanel rootPanel = new JPanel();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Launcher().setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Launcher() throws IOException {

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

        initListeners();

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


    private void initListeners() throws IOException {
        Scanner scanner = new Scanner(Paths.get("InputData.dat"));
        GraphModel model = GraphModel.restore(scanner);
        View view = new View(model, this);

        Viewer viewer = new Viewer(view.getGraph(), Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        canvas = viewer.addDefaultView(false);
        canvas.setPreferredSize(new Dimension(1000, 1000));

        Controller controller = new Controller (model, view);

    }

}
