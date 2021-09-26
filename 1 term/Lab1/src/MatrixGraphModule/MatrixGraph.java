package MatrixGraphModule;

import java.util.*;

public class MatrixGraph<T> {
    MatrixGraphNode<T>[][] matrixGraph;

    MatrixGraph(T[] dataArr) {

        MatrixGraphNode[][] matrixGraph = new MatrixGraphNode[dataArr.length][dataArr.length];

        for (int i = 0; i < dataArr.length; i++) {
            for (int j = 0; j < dataArr.length; j++) {
                if (i == j) {
                    matrixGraph[i][j] = new MatrixGraphNode<>(dataArr[i], true);
                    continue;
                }
                    matrixGraph[i][j] = new MatrixGraphNode<>(dataArr[i], false);
            }
        }

        this.matrixGraph = matrixGraph;
    }

    void init() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < this.matrixGraph.length ; i++) {
            String userChoise;
            System.out.println("You can add connection with other vertexes in range from 0 to " +
                    (this.matrixGraph.length - 1) + ", this one is " + i + ".So, you do? y/n");
            userChoise = scanner.nextLine();
            while (!userChoise.equals("n")) {
                int index;
                System.out.println("Choose the index of vertex to connect: ");
                index = Integer.parseInt(scanner.nextLine());
                if (!matrixGraph[i][index].isConnected()) {
                    matrixGraph[i][index].setConnected(true);
                    matrixGraph[index][i].setConnected(true);
                } else {
                    System.out.println("Already connected!");
                }
                System.out.println("Want to continue? y/n");
                userChoise = scanner.nextLine();
            }
        }
    }

    void showMatrixGraph() {
        for (int i = 0; i < this.matrixGraph.length; i++) {
            for (int j = 0; j < this.matrixGraph.length; j++) {
                System.out.print((this.matrixGraph[i][j].isConnected() ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

    void addGraphLine(int firstVertex, int secondVertex) {
        if (!matrixGraph[firstVertex][secondVertex].isConnected()) {
            matrixGraph[firstVertex][secondVertex].setConnected(true);
            matrixGraph[secondVertex][firstVertex].setConnected(true);
        }
        System.out.println("Successfully connected!");
    }

    void removeGraphLine(int firstVertex, int secondVertex) {
        if (matrixGraph[firstVertex][secondVertex].isConnected()) {
            matrixGraph[firstVertex][secondVertex].setConnected(false);
            matrixGraph[secondVertex][firstVertex].setConnected(false);
        }
        System.out.println("Successfully deleted!");
    }

    void addVertex(T data) {
        Scanner scanner = new Scanner(System.in);

        MatrixGraphNode[][] matrixGraph = new MatrixGraphNode[this.matrixGraph.length + 1][this.matrixGraph.length + 1];

        //Copy prev Graph info
        for (int i = 0; i < matrixGraph.length - 1; i++) {
            System.arraycopy(this.matrixGraph[i], 0, matrixGraph[i], 0, matrixGraph.length - 1);
        }

        for (int i = 0; i < matrixGraph.length; i++) {
            if (i == matrixGraph.length - 1) {
                matrixGraph[i][matrixGraph.length - 1] = new MatrixGraphNode(data, true);
                break;
            }
            matrixGraph[matrixGraph.length - 1][i] = new MatrixGraphNode(data, false);
            matrixGraph[i][matrixGraph.length - 1] = new MatrixGraphNode(this.matrixGraph[i][0].getData(), false);
        }

        System.out.println("Added new vertex!Okay, what about connections?(y/n)");
        while (!scanner.nextLine().equals("n")) {
            int index;
            System.out.println("Choose the index of vertex to connect: ");
            index = Integer.parseInt(scanner.nextLine());

            matrixGraph[matrixGraph.length - 1][index].setConnected(true);
            matrixGraph[index][matrixGraph.length - 1].setConnected(true);

            System.out.println("Want to continue? y/n");
        }

        this.matrixGraph = matrixGraph;

    }

    void removeVertex(int vertexNum) {
        MatrixGraphNode[][] matrixGraph = new MatrixGraphNode[this.matrixGraph.length - 1][this.matrixGraph.length - 1];
        int p = 0 ;

        for (int i = 0; i < this.matrixGraph.length; i++) {
            if (i == vertexNum) continue;
            int q = 0;
            for (int j = 0; j < this.matrixGraph.length; j++) {
                if (j == vertexNum) continue;
                matrixGraph[p][q] = this.matrixGraph[i][j];
                q++;
            }
            p++;
        }
        System.out.println("Graph vertex #" + vertexNum +  " successfully deleted");
        this.matrixGraph = matrixGraph;
    }

    T getVertexData(int vertexNum) {
        System.out.println("Data " + this.matrixGraph[vertexNum][0].getData());
        return this.matrixGraph[vertexNum][0].getData();
    }

    boolean checkConnectivity() {
        HashSet<Integer> visited = new HashSet<>();
        for (int i = 0; i < this.matrixGraph.length; i++) {
            for (int j = 0; j < this.matrixGraph.length; j++) {
                if (this.matrixGraph[i][j].isConnected() && i != j) {
                    visited.add(i);
                    visited.add(j);
                }
            }
        }
        return visited.size() == this.matrixGraph.length;
    }

    int getDistance(int src, int dest) {
        HashSet<Integer> visitedBefore = new HashSet<>();
        ArrayList<Integer> results = new ArrayList<>();
        this.getWays(src, dest, 1, results, visitedBefore);
        return Collections.min(results);
    }

    void getWays(int line, int dest, int curSize, ArrayList<Integer> results, HashSet<Integer> visitedBefore) {
        for (int i = 0; i < this.matrixGraph.length; i++) {
            if (line == i || visitedBefore.contains(i)) continue;
            if (matrixGraph[line][i].isConnected()) {
                if (i == dest) {
                    results.add(curSize);
                    return;
                }
                visitedBefore.add(i);
                this.getWays(i, dest, curSize++, results, visitedBefore);
            }
        }
    }

    void BFS(int start) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        HashSet<Integer> visited = new HashSet<>();

        deque.addLast(start);
        visited.add(start);
        System.out.println("BFS: ");
        while(!deque.isEmpty()) {
            int index = deque.pollFirst();
            System.out.print(index + " ");
            for (int i = 0; i < this.matrixGraph.length; i++) {
                if (matrixGraph[index][i].isConnected() && i != index && !visited.contains(i)) {
                    visited.add(i);
                    deque.addLast(i);
                }
            }
        }
    }
}
