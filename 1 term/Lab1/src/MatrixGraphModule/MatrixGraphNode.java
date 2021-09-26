package MatrixGraphModule;

public class MatrixGraphNode<T> {
    private T data;
    private boolean isConnected;

    MatrixGraphNode(T data, boolean connected) {
        this.data = data;
        this.isConnected = connected;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setConnected(boolean connected) {
        this.isConnected = connected;
    }

    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public String toString() {
        return "MatrixGraphModule.MatrixGraphNode{" +
                "data=" + data +
                ", isConnected=" + isConnected +
                '}';
    }
}
