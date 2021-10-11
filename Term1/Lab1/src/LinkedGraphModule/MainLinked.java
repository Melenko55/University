package LinkedGraphModule;

import java.util.Scanner;
import java.util.regex.Pattern;

public class MainLinked {
    public static boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    public static int checkForNum(String str) {
        Scanner scanner = new Scanner(System.in);
        while (!isNumeric(str)) {
            System.out.println("Please input integer number: ");
            str = scanner.nextLine();
        }
        return Integer.parseInt(str);

    }

    public static void showMenu() {
        System.out.println("\nPossible options: ");
        System.out.println("1 - show Graph\n2 - add Vertex\n3 - remove Vertex\n4 - add Line\n5 - remove Line\n6 - check graph for connectivity\n7 - get data stored in vertex\n8 - get distance between vertexes\n9 - BFS\n0 - stop program\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer[] arr = new Integer[] {7, 8, 9, 10, 25};
        ListGraph<Integer> linkGraph = new ListGraph<Integer>(arr);

        showMenu();
        int userChoise = checkForNum(scanner.nextLine());
        int vertexIndex, lineIndex1, lineIndex2;
        while (true) {
            switch (userChoise) {
                case 0:
                    System.out.println("Closing...");
                    return;
                case 1:
                    linkGraph.showGraph();
                    break;
                case 2:
                    System.out.println("Vertex data to add:");
                    vertexIndex = checkForNum(scanner.nextLine());
                    linkGraph.addVertex(vertexIndex);
                    break;
                case 3:
                    System.out.println("Vertex index to remove:");
                    vertexIndex = checkForNum(scanner.nextLine());
                    linkGraph.removeVertex(vertexIndex);
                    break;
                case 4:
                    System.out.println("Line creation process...");
                    System.out.print("First vertex:");
                    lineIndex1 = checkForNum(scanner.nextLine());
                    System.out.print("Second vertex:");
                    lineIndex2 = checkForNum(scanner.nextLine());
                    linkGraph.addLine(lineIndex1, lineIndex2);
                    break;
                case 5:
                    System.out.print("First vertex:");
                    lineIndex1 = checkForNum(scanner.nextLine());
                    System.out.print("Second vertex:");
                    lineIndex2 = checkForNum(scanner.nextLine());
                    linkGraph.removeLine(lineIndex1, lineIndex2);
                    break;
                case 6:
                    System.out.println(linkGraph.checkConnectivity(0) ? "Graph is connected!" : "Graph is not connected!");
                    break;
                case 7:
                    System.out.println("Vertex index:");
                    vertexIndex = checkForNum(scanner.nextLine());
                    linkGraph.getVertexData(vertexIndex);
                    break;
                case 8:
                    System.out.print("First vertex:");
                    lineIndex1 = checkForNum(scanner.nextLine());
                    System.out.print("Second vertex:");
                    lineIndex2 = checkForNum(scanner.nextLine());
                    System.out.println("Distance between " + lineIndex1 + " and " + lineIndex2 + " is " + linkGraph.getDistance(lineIndex1, lineIndex2));
                    break;
                case 9:
                    System.out.println("Index of vertex to begin:");
                    vertexIndex = checkForNum(scanner.nextLine());
                    linkGraph.checkConnectivity(vertexIndex);
                    break;
            }
            showMenu();
            userChoise = checkForNum(scanner.nextLine());
        }
    }
}
