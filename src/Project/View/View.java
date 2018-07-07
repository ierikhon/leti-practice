package Project.View;


import Project.Model.GraphModel;
import Project.Model.Information;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import java.awt.*;

public class View
{
    private Graph graph = new SingleGraph("Graph", false, true);
    private Component canvas;

    public Component getCanvas() {
        return canvas;
    }

    public View(Component canvas)
    {
        this.canvas = canvas;
    }

    public void updateGraph(GraphModel model)
    {
        byte[][] field = model.getField();
        Information[] attended = model.getAttended();

        int j = 0;
        for (byte[] current : field)
        {
            for (int i = 0; i < current.length; i++)
                if ((current[i] == 1)||(current[i] == 2))
                {
                    String node2 = attended[i].getNodeName();
                    String node1 = attended[j].getNodeName();
                    graph.addEdge(node1 + node2, node1, node2, true);
                    graph.getNode(node1).setAttribute("xy", 10*Math.cos(2*Math.PI/current.length*i), 10*Math.sin(2*Math.PI/current.length*i));
                    graph.getNode(node1).addAttribute("ui.label", graph.getNode(node1).getId());

                }
                else
                {
                    String node = attended[i].getNodeName();
                    graph.addEdge(node + "0", node, "", true);
                    graph.getEdge(node + "0").addAttribute("ui.hide");
                    graph.getNode(node).setAttribute("xy", 10*Math.cos(2*Math.PI/current.length*i), 10*Math.sin(2*Math.PI/current.length*i));
                    graph.getNode(node).addAttribute("ui.label", graph.getNode(node).getId());
                    graph.getNode("").setAttribute("ui.hide");
                }
            j++;
        }
        graph.addAttribute("ui.stylesheet", "node {" +
                "        fill-color: green;" +
                "        size: 15px;" +
                "        stroke-mode: plain;" +
                "        stroke-color: black;" +
                "        stroke-width: 5px;" +
                "    }");
        canvas = (new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD)).addDefaultView(false);
    }
}
