/**
 * Graph class
 * 
 * @author {Armen Khachatryan}
 * @version {09/19/2024}
 */
public class Graph {
    private GraphNode[] nodes;
    private int capacity;
    private int size;
    private int[] parent;
    private int[] componentSize;

    /**
     * Graph constructor
     * 
     * @param initialCapacity
     *            initial capacity of graph
     */
    public Graph(int initialCapacity) {
        nodes = new GraphNode[initialCapacity];
        capacity = initialCapacity;
        size = 0;

        // Initialize structure
        parent = new int[initialCapacity];
        componentSize = new int[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            parent[i] = -1;
            componentSize[i] = 1;
        }
    }


    /**
     * Add a node to the graph
     * 
     * @param key
     *            string key of node
     */
    public void addNode(String key) {

        if (findNode(key) == null) { // Check if node already exists
            if (size == capacity) { // Check if resizing is needed
                resize();
            }
            nodes[size++] = new GraphNode(key);

            int index = size - 1;
            parent[index] = -1;
            componentSize[index] = 1;
        }
    }


    /**
     * Resize the array if needed
     */
    private void resize() {
        int newCapacity = capacity * 2;
        GraphNode[] newNodes = new GraphNode[newCapacity];
        int[] newParent = new int[newCapacity];
        int[] newComponentSize = new int[newCapacity];
        System.arraycopy(nodes, 0, newNodes, 0, capacity);
        System.arraycopy(parent, 0, newParent, 0, capacity);
        System.arraycopy(componentSize, 0, newComponentSize, 0, capacity);
        nodes = newNodes;
        parent = newParent;
        componentSize = newComponentSize;
        capacity = newCapacity;
    }


    /**
     * Find a node by its name
     * 
     * @param key
     *            string key of value
     * @return returns null of DNE
     */
    private GraphNode findNode(String key) {

        for (int i = 0; i < size; i++) {
            if (nodes[i].toString().equals(key)) {
                return nodes[i];
            }
        }
        return null;
    }


    /**
     * Add edge to node
     * 
     * @param keyArt
     *            string of artist value (node)
     * @param keySong
     *            string of song value (edge)
     */
    public void addEdge(String keyArt, String keySong) {

        // Check if either node name is null
        if (keyArt == null || keySong == null) {
            System.out.println("Invalid edge input");
            return;
        }

        // Check if adding a self-loop
        if (keyArt.equals(keySong)) {
            return;
        }

        GraphNode node1 = findNode(keyArt);
        GraphNode node2 = findNode(keySong);

        if (node1 != null && node2 != null && !edgeExists(node1, node2)) {
            node1.adjacentNodes().append(node2);
            node2.adjacentNodes().append(node1);

            // Perform union operation
            int index1 = findIndex(node1);
            int index2 = findIndex(node2);
            union(index1, index2);
        }
    }


    /**
     * Check if an edge exists between two nodes
     * 
     * @param node1
     *            first node value
     * @param node2
     *            second node/edge value
     * @return boolean representing if edge exists
     */
    private boolean edgeExists(GraphNode node1, GraphNode node2) {
        // Check if node2 is in the adjacency list of node1
        DLLNode<GraphNode> current = node1.adjacentNodes().giveHead();
        while (current != null) {
            if (current.toData().equals(node2)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }


    /**
     * Remove an edge between two nodes
     * 
     * @param keyArt
     *            node with edge
     * @param keySong
     *            edge to remove
     */
    public void removeEdge(String keyArt, String keySong) {

        GraphNode node1 = findNode(keyArt);
        GraphNode node2 = findNode(keySong);

        if (node1 != null && node2 != null) {
            node1.adjacentNodes().remove(node2);
            node2.adjacentNodes().remove(node1);
        }
    }


    /**
     * Find the index of a GraphNode
     * 
     * @param node
     *            node to find
     * @return index value of node
     */
    private int findIndex(GraphNode node) {
        for (int i = 0; i < size; i++) {
            if (nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Remove a node from the graph
     * 
     * @param key
     *            string value of node to remove
     */
    public void removeNode(String key) {

        GraphNode nodeToRemove = findNode(key);
        if (nodeToRemove != null) {
            // Remove node from adjacency lists of all adjacent nodes
            for (int i = 0; i < size; i++) {
                nodes[i].adjacentNodes().remove(nodeToRemove);
            }
            // Remove the node itself
            int index = findIndex(nodeToRemove);
            if (index != -1) {
                for (int i = index; i < size - 1; i++) {
                    nodes[i] = nodes[i + 1];
                }
                nodes[--size] = null;
            }
        }
    }


    // Return the root of curr's tree with path compression
    private int find(int curr) {
        if (parent[curr] == -1) {
            return curr; // At root
        }
        parent[curr] = find(parent[curr]); // Path compression
        return parent[curr];
    }


    // Union operation to merge sets containing elements 'a' and 'b'
    private void union(int a, int b) {
        int root1 = find(a); // Find root of node a
        int root2 = find(b); // Find root of node b
        if (root1 != root2) { // Merge with weighted union
            if (componentSize[root2] > componentSize[root1]) {
                parent[root1] = root2;
                componentSize[root2] += componentSize[root1];
            }
            else {
                parent[root2] = root1;
                componentSize[root1] += componentSize[root2];
            }
        }
    }


    /**
     */
    public void recomputeConnectedComponents() {
        // Reset structure
        for (int i = 0; i < size; i++) {
            parent[i] = -1;
            componentSize[i] = 1;
        }

        // Rebuild structure based on current edges
        for (int i = 0; i < size; i++) {
            GraphNode node = nodes[i];
            DLLNode<GraphNode> current = node.adjacentNodes().giveHead();
            while (current != null) {
                int index1 = findIndex(node);
                int index2 = findIndex(current.toData());
                if (index1 != -1 && index2 != -1) {
                    union(index1, index2);
                }
                current = current.getNext(); // Access next node using getter
            }
        }

        // Compute and display connected components
        int numComponents = 0;
        int maxComponentSize = 0;

        // Find the root for each node and compute component sizes
        for (int i = 0; i < size; i++) {
            int root = find(i);
            if (root == i) {
                numComponents++;
                if (componentSize[i] > maxComponentSize) {
                    maxComponentSize = componentSize[i];
                }
            }
        }
        System.out.println("there are " + numComponents
            + " connected components");
        System.out.println("the largest connected component has "
            + maxComponentSize + " elements");
    }


    /**
     * Print the graph
     */
    public void printGraph() {
        for (int i = 0; i < size; i++) {
            System.out.print(nodes[i].toString() + ": ");
            nodes[i].adjacentNodes().printForward();
        }
    }


    /**
     * Size of graph
     * 
     * @return the size of the graph
     */
    public int getSize() {
        return size;
    }
}
