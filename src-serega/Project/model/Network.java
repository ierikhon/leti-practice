package Project.model;

class Network {

    private static byte[][] field;
    private static byte[] attended;

    Network()
    {
        field = new byte[][]{{0, 0, 1, 1}, {0, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}};
        attended = new byte[4];
    }

    private static int size(){
        return field.length;
    }

    private byte isEdge(int i, int j){
        return field[i][j];
    }

    private void addEdge(int i, int j){
        field[i][j] = 1;
    }

    private void check(int i){
        attended[i] = 1;
    }

    private void uncheck(int i){
        attended[i] = 0;
    }

    private boolean hasUnattended(){
        for (int i = 0; i < Network.size(); i++){
            if (attended[i] == 0) return true;
        }
        return false;
    }

    void write(){
        for (int i = 0; i < Network.size(); i++) {
            for (int j = 0; j < Network.size(); j++) {
                System.out.print(Network.field[i][j]+" ");
            }
            System.out.println();
        }
    }

    void Transite(){
        int i, j, k;
        while (this.hasUnattended()) {
            for (j = 0; j < size(); j++) {
                for (i = 0; i < size(); i++) {
                    if (j != i) {
                        for (k = 0; k < size(); k++) {
                            if (j != k) {
                                if ((this.isEdge(i, j) == 1)&&(this.isEdge(j, k) == 1)&&(this.isEdge(i, k) == 0)) {
                                    this.addEdge(i, k);
                                    this.uncheck(i);
                                    this.uncheck(k);
                                }
                            }
                        }
                    }
                }
                this.check(j);
            }
        }
    }

}
