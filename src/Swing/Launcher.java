package Swing;


import Project.Controller.Controller;
import Project.Model.GraphModel;
import Project.View.View;

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

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            try {
                new Launcher();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Launcher () throws IOException
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

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        cPanel.setPreferredSize(getCPanelActualSize(screenSize));
        canvas.setPreferredSize(getCanvasActualSize(screenSize));


        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.X_AXIS));
        rootPanel.setPreferredSize(new Dimension(1500, 940));
        rootPanel.add(cPanel, BorderLayout.EAST);
        rootPanel.add(canvas, BorderLayout.WEST);

        setContentPane(rootPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();

        initListeners();
    }


    private Dimension getCanvasActualSize(Dimension screenSize)
    {
        Dimension actualSize = new Dimension();

        actualSize.height = screenSize.height < 1000 ? screenSize.height : 1000;
        actualSize.width = screenSize.width < 1000 ? screenSize.height : 1000;

        return actualSize;
    }


    private Dimension getCPanelActualSize(Dimension screenSize)
    {
        Dimension actualSize = new Dimension();

        actualSize.height = screenSize.height < 1000 ? screenSize.height : 1000;
        actualSize.width = screenSize.width < 500 ? screenSize.height : 500;

        return actualSize;
    }

    private void initListeners() throws IOException
    {
        setVisible(true);
        setLocationRelativeTo(null);

        View view = new View(canvas);
        GraphModel model = GraphModel.restore(new Scanner(Paths.get("Default.dat")));
        Controller controller = new Controller(view, model);
        controller.updated();

        Timer timer = new Timer(100, e -> {
            if (controller.wasUpdated())
            {
                rootPanel.remove(canvas);
                canvas = controller.getView().getCanvas();
                canvas.setPreferredSize(getCanvasActualSize(Toolkit.getDefaultToolkit().getScreenSize()));
                rootPanel.add(canvas, BorderLayout.WEST);
                rootPanel.revalidate();
            }
        });
        timer.setRepeats(true);
        timer.start();
    }
}

