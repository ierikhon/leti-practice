package Project.Model;

import java.util.Scanner;

public class GraphModel
{
    private  byte[][] field;
    private  Information[] attended;

    public byte[][] getField()
    {
        return field;
    }
    public Information[] getAttended()
    {
        return attended;
    }

    private GraphModel(byte[][] data, String[] names)
    {
        field = data;
        attended = new Information[data[0].length];
        for (int i = 0; i < attended.length; i++)
        {
            attended[i] = new Information();
            attended[i].setNodeName(names[i]);
            attended[i].setAttended(false);
        }


    }

    private  int getSize()
    {
        return attended.length;
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

    public void writeMatrix()
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

    public static GraphModel restore (Scanner input)
    {
        int nodeAmount = input.nextInt();
        String[] info = new String[nodeAmount];
        input.nextLine();
        for (int i = 0; i < nodeAmount; i++)
            info[i] = input.nextLine();

        byte[][] matrix = new byte[nodeAmount][nodeAmount];
        for (int i = 0; i < nodeAmount; i++)
            for (int j = 0; j < nodeAmount; j++)
                matrix[i][j] = (byte)input.nextInt();

        return new GraphModel(matrix, info);
    }
}
