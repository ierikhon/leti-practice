package Project.View;

import Project.Model.GraphModel;
import Project.Model.Information;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class View
{
    public Graph getGraph() {
        return graph;
    }

    private Graph graph = new SingleGraph("Tutorial", false, true);

    public void draw (GraphModel model)
    {
        byte[][] field = model.getField();
        Information[] attended = model.getAttended();

        int j = 0;
        for (byte[] current : field)
        {
            for (int i = 0; i < current.length; i++)
                if (current[i] == 1)
                {
                    String node1 = attended[i].getNodeName();
                    String node2 = attended[j].getNodeName();
                    graph.addEdge(node1 + node2, node1, node2, true);
                    graph.getNode(node1).setAttribute("xy", 10*Math.cos(2*Math.PI/current.length*i), 10*Math.sin(2*Math.PI/current.length*i));

                }
                else
                {
                    String node = attended[i].getNodeName();
                    graph.addEdge(node + "0", node, "", true);
                    graph.getEdge(node + "0").addAttribute("ui.hide");
                    graph.getNode(node).setAttribute("xy", 10*Math.cos(2*Math.PI/current.length*i), 10*Math.sin(2*Math.PI/current.length*i));

                }
            j++;
        }
    }


    public View (GraphModel model)
    {
        draw(model);
    }

}