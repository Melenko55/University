public class Main {
    public static void main(String[] args) {
        Integer[] arr = new Integer[] {7, 8, 10, -1, 15};
        MatrixGraph<Integer> matrixGraph = new MatrixGraph<Integer>(arr);
        matrixGraph.showMatrixGraph();
        matrixGraph.removeGraphLine(0, 2);
        matrixGraph.showMatrixGraph();
        matrixGraph.addGraphLine(0, 2);
        matrixGraph.showMatrixGraph();
        matrixGraph.addVertex(21);
        matrixGraph.showMatrixGraph();
    }
}
