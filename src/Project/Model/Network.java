package Project.Model;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

class Network
{
    private  byte[][] field;
    private  Information[] attended;
    private  Graph graph = new SingleGraph("Resulting Graph", false, true);

    public byte[][] getField()
    {
        return field;
    }

    public Information[] getAttended()
    {
        return attended;
    }

    public Graph getGraph()
    {
        return graph;
    }

    public Network(byte[][] data, String[] names)
    {
        field = data;
        attended = new Information[data[0].length];
        for (int i = 0; i < attended.length; i++)
        {
            attended[i].setNodeName(names[i]);
            attended[i].setAttended(false);
        }

        graphFromMatrix();
    }

    private void graphFromMatrix()
    {
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

    private  int getSize()
    {
        return field.length;
    }

    private byte isEdge(int i, int j)
    {
        return field[i][j];
    }

    private void addEdge(int i, int j)
    {
        field[i][j] = 1;
    }

    private void check(int i)
    {
        attended[i].setAttended(true);
    }

    private void uncheck(int i)
    {
        attended[i].setAttended(false);
    }

    private boolean hasUnattended()
    {
        for (int i = 0; i < getSize(); i++){
            if (!attended[i].isAttended()) return true;
        }
        return false;
    }

    void writeMatrix()
    {
        for (int i = 0; i < getSize(); i++)
        {
            for (int j = 0; j < getSize(); j++)
            {
                System.out.print(field[i][j]+" ");
            }
            System.out.println();
        }
    }

    void Transite()
    {
        int i, j, k;
        while (this.hasUnattended())
            for (j = 0; j < getSize(); j++)
            {
                for (i = 0; i < getSize(); i++)
                    if (j != i)
                        for (k = 0; k < getSize(); k++)
                            if (j != k)
                                if ((this.isEdge(i, j) == 1)&&(this.isEdge(j, k) == 1)&&(this.isEdge(i, k) == 0))
                                {
                                    this.addEdge(i, k);
                                    this.uncheck(i);
                                    this.uncheck(k);
                                }
                this.check(j);
            }

    }

}

class Information
{
    private boolean wasAttended;
    private String nodeName = "";

    String getNodeName()
    {
        return nodeName;
    }

    void setAttended(boolean attended)
    {
        wasAttended = attended;
    }

    void setNodeName(String nodeName)
    {
        this.nodeName = nodeName;
    }

    boolean isAttended()
    {
        return wasAttended;
    }
}