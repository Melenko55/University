package LinkedGraphModule;

import java.util.*;

public class ListGraph<T> {
    LinkedList<ListGraphNode<T>> listOfVertexes = new LinkedList<>();

    ListGraph(T[] data) {
        for (int i = 0; i < data.length; i++) {
            this.listOfVertexes.add(new ListGraphNode<>(data[i], i));
        }
    }

    void showGraph() {
        for (ListGraphNode<T> listOfVertex : this.listOfVertexes) {
            System.out.print(listOfVertex);
        }
    }

    void addLine(int vertex1, int vertex2) {
        if (vertex1 == vertex2) {
            System.out.println("Vertex is already connected with itself!");
            return;
        }
        this.listOfVertexes.get(vertex1).addConnection(vertex2);
        this.listOfVertexes.get(vertex2).addConnection(vertex1);
        System.out.println("Succesfully added line between " + vertex1 + " and " + vertex2 + "\n");
    }

    void removeLine(int vertex1, int vertex2) {
        if (vertex1 == vertex2) {
            System.out.println("Vertex is always connected with itself!");
            return;
        }
        this.listOfVertexes.get(vertex1).removeConnection(vertex2, false);
        this.listOfVertexes.get(vertex2).removeConnection(vertex1, false);
        System.out.println("Succesfully removed line between " + vertex1 + " and " + vertex2 + "\n");
    }

    void addVertex(T data) {
        Scanner scanner = new Scanner(System.in);
        ListGraphNode<T> addition = new ListGraphNode<>(data, this.listOfVertexes.size());
        System.out.println("Create some connections?(y/n)");
        String option = scanner.nextLine();
        while (!option.equals("n")) {
            System.out.println("Chooose index of vertex to connect from 0 to " + this.listOfVertexes.size() + '\n');
            int index = Integer.parseInt(scanner.nextLine());
            addition.addConnection(index);
            this.listOfVertexes.get(index).addConnection(this.listOfVertexes.size());
            System.out.println("Want to continue?(y/n)");
            option = scanner.nextLine();
        }
        this.listOfVertexes.addLast(addition);
    }

    void removeVertex(int vertexId) {
        for (ListGraphNode<T> vertex : this.listOfVertexes) {
            vertex.removeConnection(vertexId, true);
        }
        this.listOfVertexes.remove(vertexId);
        for (int i = 0; i < this.listOfVertexes.size(); i++) {
            this.listOfVertexes.get(i).setVertexId(i);
        }
    }

    boolean checkConnectivity(int vertexBegin) {
        HashSet<Integer> visited = new HashSet<>();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        visited.add(vertexBegin);
        deque.addLast(vertexBegin);

        while (!deque.isEmpty()) {
            int id = deque.pollFirst();
            System.out.print(id + " ");
            for (Integer element : this.listOfVertexes.get(id).connectedVertex) {
                if (!visited.contains(element)) {
                    deque.addLast(element);
                    visited.add(element);
                }
            }
        }

        return visited.size() == this.listOfVertexes.size();
    }

    int getDistance(int vertex1, int vertex2) {
        ArrayList<Integer> visited = new ArrayList<>();
        ArrayList<Integer> results = new ArrayList<>();

        this.getWays(vertex1, vertex2, 0, results, visited);
        System.out.println("Results :" + results);
        return Collections.min(results);
    }

    void getWays(int line, int dest, int curSize, ArrayList<Integer> results, ArrayList<Integer> visitedBefore) {
        for (int vertex : this.listOfVertexes.get(line).connectedVertex) {
            if (visitedBefore.contains(vertex)) continue;
            if (vertex == dest) {
                results.add(++curSize);
                return;
            }
            visitedBefore.add(vertex);
            getWays(vertex, dest, ++curSize, results, visitedBefore);
        }
    }

    void getVertexData(int vertexId) {
        System.out.println(this.listOfVertexes.get(vertexId).getData());
    }

}
