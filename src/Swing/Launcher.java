package Swing;


import Project.Controller.Controller;
import Project.Model.GraphModel;
import Project.View.View;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Launcher extends JFrame
{
    private static ControlPanel cPanel = new ControlPanel();
    private static Component canvas = new JPanel();
    private static JPanel rootPanel = new JPanel();
    private static File file;

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(Launcher::new);
    }

    private Launcher ()
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

        setVisible(true);
        setLocationRelativeTo(null);

        openItem.addActionListener(e-> {
            try {
                fromfile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
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

        View view = new View(canvas);
        GraphModel model = GraphModel.restore(new Scanner(Paths.get(file.getName())));
        Controller controller = new Controller(view, model);
        controller.updated();

        cPanel.addStartButtonListener(e -> controller.start());
        cPanel.addStopButtonListener(e -> controller.stop(file.getName()));
        cPanel.addForwardButtonListener(e -> controller.forward());
        cPanel.addBackButtonListener(e -> controller.back());

        Timer timer = new Timer(100, e -> {
            if (controller.wasUpdated())
            {
                rootPanel.remove(canvas);
                canvas = controller.getView().getCanvas();
                canvas.setPreferredSize(getCanvasActualSize(Toolkit.getDefaultToolkit().getScreenSize()));
                rootPanel.add(canvas, BorderLayout.WEST);
                rootPanel.revalidate();
                canvas.repaint();

                cPanel.getMatrix().setText(controller.matrixUpdated());
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    private void fromfile() throws IOException
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Graph data", "dat");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal != JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        file = chooser.getSelectedFile();
        initListeners();
    }
}

