package Project.model;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(Paths.get("InputData.dat"));
        GraphModel model = GraphModel.restore(scanner);
        model.writeMatrix();
        System.out.println();
        model.transite();
        model.writeMatrix();
        System.out.println();
        model.backToOriginal(model.stepsNumber()-1);
        model.writeMatrix();

    }
}
