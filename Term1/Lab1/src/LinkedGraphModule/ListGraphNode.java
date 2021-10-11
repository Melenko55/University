package LinkedGraphModule;

import java.util.HashSet;
import java.util.Iterator;

public class ListGraphNode<T> {
    T data;
    HashSet<Integer> connectedVertex = new HashSet<>();
    int vertexId;

    ListGraphNode(T data, int vertexId) {
        this.data = data;
        this.vertexId = vertexId;
    }

    void addConnection(int vertexId) {
        this.connectedVertex.add(vertexId);
    }
    void removeConnection(int vertexId, boolean withVertex) {
        if (withVertex) {
            HashSet<Integer> connectedVertex = new HashSet<>();
            this.connectedVertex.remove(vertexId);
            for (Integer v: this.connectedVertex) {
                if (v > vertexId) {
                    connectedVertex.add(--v);
                } else {
                    connectedVertex.add(v);
                }
            }
            this.connectedVertex = connectedVertex;
        } else {
            this.connectedVertex.remove(vertexId);
        }

    }

    public T getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return this.vertexId + ": (LinkedGraphModule.ListGraphNode: " +
                "data = " + data +
                " -> connectedVertex = {" + (this.connectedVertex.size() == 0 ? "Empty": this.connectedVertex) +
                "}\n";
    }

    public HashSet<Integer> getConnectedVertex() {
        return connectedVertex;
    }

    public void setVertexId(int vertexId) {
        this.vertexId = vertexId;
    }
}
