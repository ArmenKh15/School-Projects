/**
 * GraphNode class
 * 
 * @author {Armen Khachatryan}
 * @version {09/13/2024}
 */
public class GraphNode {

    private String data; // Data stored in node
    private DoubleLL<GraphNode> adjacentNodes;

    /**
     * GraphNode constructor
     * 
     * @param data
     *            string value stored in node
     */
    public GraphNode(String data) {
        this.data = data;
        this.adjacentNodes = new DoubleLL<>();
    }


    /**
     * Return string of node
     * 
     * @return string value of key in node
     */
    public String toString() {
        return data;
    }


    /**
     * Return adjacency list
     * 
     * @return adjacency list of node
     */
    public DoubleLL<GraphNode> adjacentNodes() {
        return adjacentNodes;
    }
}
