package Project.Model;

import java.util.ArrayList;
import java.util.Scanner;

public class GraphModel
{
    private byte[][] field;
    private Information[] attended;
    private ArrayList<Triplet> steps = new ArrayList<>();

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

    private int getSize()
    {
        return attended.length;
    }

    private byte isEdge(int i, int j)
    {
        return field[i][j];
    }

    private void addEdge(int i, int j)
    {
        field[i][j] = 2;
    }

    private void removeEdge (int i, int j){
        field[i][j] = 0;
    }

    private void check(int i)
    {
        attended[i].setAttended(true);
    }

    private void uncheck(int i)
    {
        attended[i].setAttended(false);
    }

    public int stepsNumber(){
        return steps.size();
    }

    private boolean hasUnattended()
    {
        for (int i = 0; i < getSize(); i++){
            if (!attended[i].isAttended()) return true;
        }
        return false;
    }

    public String writeMatrix()
    {
        StringBuilder result = new StringBuilder();
        for (byte[] current : field)
        {
            for (byte edge : current)
                result.append(edge).append(" ");
            result.append("\n");
        }
        return result.toString();
    }

    public void transite()
    {
        steps.add(0, null);
        int i = 0;
        while (this.hasUnattended()){
            stepFwd(i);
        }
    }

    private Triplet searchNext(Triplet start){
        while (this.hasUnattended())
            for (int j = 0; j < getSize(); j++){
                for (int i = 0; i < getSize(); i++)
                    if (j != i)
                        for (int k = 0; k < getSize(); k++)
                            if (j != k)
                                if ((this.isEdge(i, j) != 0) && (this.isEdge(j, k) != 0) && (this.isEdge(i, k) == 0)) {
                                    Triplet next = new Triplet(i, j, k);
                                    if (!next.equals(start)) return next;
                                }
                check(j);
            }
        return null;
    }

    private void enclose(Triplet triplet){
        if (triplet != null) {
            this.addEdge(triplet.start, triplet.end);
            this.uncheck(triplet.start);
            this.uncheck(triplet.end);
        }
    }

    private void reopen(Triplet triplet){
        if (triplet != null){
            this.removeEdge(triplet.start, triplet.end);
            this.uncheck(triplet.start);
            this.uncheck(triplet.end);
        }
    }

    public void stepFwd(int stepNum){
        steps.add(0, null);
        steps.add(stepNum + 1, searchNext(steps.get(stepNum)));
        enclose(steps.get(stepNum + 1));
    }

    public void stepBack(int stepNum){
        steps.add(0, null);
        reopen(steps.get(stepNum));
        steps.remove(stepNum);
    }

    public void backToOriginal(int stepCurrent){
        if (steps.get(stepCurrent) != null) {
            stepBack(stepCurrent);
            backToOriginal(stepCurrent - 1);
        }

    }

    public static GraphModel restore(Scanner input)
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
