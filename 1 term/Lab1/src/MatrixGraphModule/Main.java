package MatrixGraphModule;

import java.util.Scanner;

public class Main {

    public static void showMenu() {
        System.out.println("\nPossible options: ");
        System.out.println("1 - show Graph\n2 - add Vertex\n3 - remove Vertex\n4 - add Line\n5 - remove Line\n6 - check graph for connectivity\n7 - get data stored in vertex\n8 - get distance between vertexes\n9 - BFS\n0 - stop program\n");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Integer[] arr = new Integer[] {7, 8, 11};
        MatrixGraph<Integer> matrixGraph = new MatrixGraph<Integer>(arr);
        matrixGraph.init();
        showMenu();
        int userChoise = Integer.parseInt(scanner.nextLine());
        int vertexIndex, lineIndex1, lineIndex2;
        while (true) {
            switch (userChoise) {
                case 0:
                    System.out.println("Closing...");
                    return;
                case 1:
                    matrixGraph.showMatrixGraph();
                    break;
                case 2:
                    System.out.println("Vertex data to add:");
                    vertexIndex = Integer.parseInt(scanner.nextLine());
                    matrixGraph.addVertex(vertexIndex);
                    break;
                case 3:
                    System.out.println("Vertex index to remove:");
                    vertexIndex = Integer.parseInt(scanner.nextLine());
                    matrixGraph.removeVertex(vertexIndex);
                    break;
                case 4:
                    System.out.println("Line creation process...");
                    System.out.print("First vertex:");
                    lineIndex1 = Integer.parseInt(scanner.nextLine());
                    System.out.print("Second vertex:");
                    lineIndex2 = Integer.parseInt(scanner.nextLine());
                    matrixGraph.addGraphLine(lineIndex1, lineIndex2);
                    break;
                case 5:
                    System.out.print("First vertex:");
                    lineIndex1 = Integer.parseInt(scanner.nextLine());
                    System.out.print("Second vertex:");
                    lineIndex2 = Integer.parseInt(scanner.nextLine());
                    matrixGraph.removeGraphLine(lineIndex1, lineIndex2);
                    break;
                case 6:
                    System.out.println(matrixGraph.checkConnectivity() ? "Graph is connected!" : "Graph is not connected!");
                    break;
                case 7:
                    System.out.println("Vertex index:");
                    vertexIndex = Integer.parseInt(scanner.nextLine());
                    matrixGraph.getVertexData(vertexIndex);
                    break;
                case 8:
                    System.out.print("First vertex:");
                    lineIndex1 = Integer.parseInt(scanner.nextLine());
                    System.out.print("Second vertex:");
                    lineIndex2 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Distance between " + lineIndex1 + " and " + lineIndex2 + " is " + matrixGraph.getDistance(lineIndex1, lineIndex2));
                    break;
                case 9:
                    System.out.println("Index of vertex to begin:");
                    vertexIndex = Integer.parseInt(scanner.nextLine());
                    matrixGraph.BFS(vertexIndex);
                    break;
            }
            showMenu();
            userChoise = Integer.parseInt(scanner.nextLine());
        }
    }
}
