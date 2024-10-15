import student.TestCase;

/**
 * GraphTest class
 * 
 * @author {Armen Khachatryan}
 * @version {09/19/2024}
 */
public class GraphTest extends TestCase {

    private Graph graph;

    /**
     * Sets up the tests that follow. In general, used for initialization.
     */
    public void setUp() {
        graph = new Graph(10);
    }


    /**
     * Test different initial size of the graph.
     */
    public void testInitSize0() {
        graph = new Graph(20);
        assertEquals(0, graph.getSize());
    }


    /**
     * Test negative initial size of the graph.
     */
    public void testInitSize1() {
        try {
            graph = new Graph(-5);
        }
        catch (NegativeArraySizeException e) {
            System.out.println("error");
        }
        assertEquals("error\n", systemOut().getHistory());
    }


    /**
     * Test node addition to the graph.
     */
    public void testAddNode() {
        graph.addNode("test0");
        graph.addNode("test1");

        graph.printGraph();

        assertEquals("test0: \n" + "test1: \n", systemOut().getHistory());
    }


    /**
     * Test node removal from the graph.
     */
    public void testRemoveNode() {
        graph.addNode("test0");
        graph.addNode("test1");

        graph.removeNode("test1");
        graph.printGraph();

        assertEquals("test0: \n", systemOut().getHistory());
    }


    /**
     * Test edge addition between nodes.
     */
    public void testAddEdge() {
        graph.addNode("test0");
        graph.addNode("test1");
        graph.addEdge("test0", "test1");

        graph.printGraph();

        assertEquals("test0: test1 \n" + "test1: test0 \n", systemOut()
            .getHistory());
    }


    /**
     * Test edge removal between nodes.
     */
    public void testRemoveEdge() {
        graph.addNode("test0");
        graph.addNode("test1");
        graph.addEdge("test0", "test1");
        graph.removeEdge("test0", "test1");

        graph.printGraph();

        assertEquals("test0: \n" + "test1: \n", systemOut().getHistory());
    }


    /**
     * Test recomputation of connected components.
     */
    public void testRecomputeConnectedComponents() {
        for (int i = 0; i < 5; i++) {
            graph.addNode("Node" + i);
        }
        graph.addEdge("Node0", "Node1");
        graph.addEdge("Node2", "Node3");
        graph.addEdge("Node3", "Node4");

        graph.recomputeConnectedComponents();

        assertEquals("there are 2 connected components\n"
            + "the largest connected component has 3 elements\n", systemOut()
                .getHistory());
    }


    /**
     * Test graph operations with resizing.
     */
    public void testResize() {
        for (int i = 0; i < 15; i++) {
            graph.addNode("Node" + i);
        }

        graph.printGraph();
        assertEquals(15, graph.getSize());
    }


    /**
     * Test removal of node from graph with multiple edges.
     */
    public void testRemoveNodeWithEdges() {
        for (int i = 0; i < 5; i++) {
            graph.addNode("Node" + i);
        }
        graph.addEdge("Node0", "Node1");
        graph.addEdge("Node1", "Node2");
        graph.addEdge("Node2", "Node3");
        graph.addEdge("Node3", "Node4");

        graph.removeNode("Node2");

        graph.printGraph();
        assertEquals("Node0: Node1 \n" + "Node1: Node0 \n" + "Node3: Node4 \n"
            + "Node4: Node3 \n", systemOut().getHistory());
    }


    /**
     * Test recomputation of connected components after node removal.
     */
    public void testRecomputeAfterNodeRemoval() {
        for (int i = 0; i < 5; i++) {
            graph.addNode("Node" + i);
        }
        graph.addEdge("Node0", "Node1");
        graph.addEdge("Node1", "Node2");
        graph.addEdge("Node2", "Node3");
        graph.addEdge("Node3", "Node4");

        graph.removeNode("Node2");
        graph.recomputeConnectedComponents();

        assertEquals("there are 2 connected components\n"
            + "the largest connected component has 2 elements\n", systemOut()
                .getHistory());
    }


    /**
     * Test duplicate edge prevention.
     */
    public void testDuplicateEdge() {
        graph.addNode("A");
        graph.addNode("B");
        graph.addEdge("A", "B");
        graph.addEdge("A", "B"); // Duplicate edge

        graph.printGraph();
        assertEquals("A: B \n" + "B: A \n", systemOut().getHistory());
    }


    /**
     * Test removal of all edges and nodes.
     */
    public void testRemoveAllEdgesAndNodes() {
        graph.addNode("A");
        graph.addNode("B");
        graph.addEdge("A", "B");
        graph.removeEdge("A", "B");
        graph.removeNode("A");
        graph.removeNode("B");

        graph.printGraph();
        assertEquals("", systemOut().getHistory());
    }


    /**
     * Test connected components after adding and removing multiple nodes.
     */
    public void testConnectedComponentsAfterMultipleChanges() {
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addEdge("A", "B");
        graph.addEdge("C", "D");
        graph.removeNode("B");
        graph.addNode("E");
        graph.addEdge("C", "E");

        graph.recomputeConnectedComponents();

        assertEquals("there are 2 connected components\n"
            + "the largest connected component has 3 elements\n", systemOut()
                .getHistory());
    }


    /**
     * Test DSU with disconnected graph.
     */
    public void testDSUDisconnectedGraph() {
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");

        graph.recomputeConnectedComponents();

        assertEquals("there are 4 connected components\n"
            + "the largest connected component has 1 elements\n", systemOut()
                .getHistory());
    }


    /**
     * Test adding a large number of nodes.
     */
    public void testLargeNumberOfNodes() {
        for (int i = 0; i < 100; i++) {
            graph.addNode("Node" + i);
        }

        assertEquals(100, graph.getSize());
    }


    /**
     * Test adding a large number of edges.
     */
    public void testLargeNumberOfEdges() {
        for (int i = 0; i < 50; i++) {
            graph.addNode("Node" + i);
        }
        for (int i = 0; i < 50; i++) {
            for (int j = i + 1; j < 50; j++) {
                graph.addEdge("Node" + i, "Node" + j);
            }
        }

        graph.recomputeConnectedComponents();

        assertEquals("there are 1 connected components\n"
            + "the largest connected component has 50 elements\n", systemOut()
                .getHistory());
    }

    // New Tests


    /**
     * Test adding and removing nodes with the same name.
     */
    public void testAddRemoveSameNode() {
        graph.addNode("NodeA");
        graph.removeNode("NodeA");
        graph.addNode("NodeA");

        graph.printGraph();
        assertEquals("NodeA: \n", systemOut().getHistory());
    }


    /**
     * Test adding and removing edges between the same nodes.
     */
    public void testAddRemoveSameEdge() {
        graph.addNode("NodeA");
        graph.addNode("NodeB");
        graph.addEdge("NodeA", "NodeB");
        graph.removeEdge("NodeA", "NodeB");
        graph.addEdge("NodeA", "NodeB");

        graph.printGraph();
        assertEquals("NodeA: NodeB \n" + "NodeB: NodeA \n", systemOut()
            .getHistory());
    }


    /**
     * Test adding an edge with a non-existent node.
     */
    public void testAddEdgeWithNonExistentNode() {
        graph.addNode("NodeA");
        graph.addEdge("NodeA", "NodeB"); // NodeB does not exist

        graph.printGraph();
        assertEquals("NodeA: \n", systemOut().getHistory());
    }


    /**
     * Test removing a node and then adding an edge involving that node.
     */
    public void testRemoveNodeAndAddEdge() {
        graph.addNode("NodeA");
        graph.addNode("NodeB");
        graph.removeNode("NodeA");
        graph.addEdge("NodeB", "NodeA"); // NodeA does not exist

        graph.printGraph();
        assertEquals("NodeB: \n", systemOut().getHistory());
    }


    /**
     * Test adding edges between newly added nodes.
     */
    public void testAddEdgesNewNodes() {
        graph.addNode("NodeA");
        graph.addNode("NodeB");
        graph.addNode("NodeC");
        graph.addEdge("NodeA", "NodeB");
        graph.addEdge("NodeB", "NodeC");

        graph.printGraph();
        assertEquals("NodeA: NodeB \n" + "NodeB: NodeA NodeC \n"
            + "NodeC: NodeB \n", systemOut().getHistory());
    }


    /**
     * Test removing an edge and then adding a new edge.
     */
    public void testRemoveEdgeAndAddNewEdge() {
        graph.addNode("NodeA");
        graph.addNode("NodeB");
        graph.addNode("NodeC");
        graph.addEdge("NodeA", "NodeB");
        graph.removeEdge("NodeA", "NodeB");
        graph.addEdge("NodeB", "NodeC");

        graph.printGraph();
        assertEquals("NodeA: \n" + "NodeB: NodeC \n" + "NodeC: NodeB \n",
            systemOut().getHistory());
    }


    /**
     * Test removing multiple nodes and then recomputing connected components.
     */
    public void testRemoveMultipleNodesAndRecompute() {
        for (int i = 0; i < 5; i++) {
            graph.addNode("Node" + i);
        }
        graph.addEdge("Node0", "Node1");
        graph.addEdge("Node1", "Node2");
        graph.removeNode("Node1");
        graph.removeNode("Node2");
        graph.recomputeConnectedComponents();

        assertEquals("there are 3 connected components\n"
            + "the largest connected component has 1 elements\n", systemOut()
                .getHistory());
    }


    /**
     * Test adding and removing multiple edges.
     */
    public void testAddRemoveMultipleEdges() {
        graph.addNode("NodeA");
        graph.addNode("NodeB");
        graph.addNode("NodeC");
        graph.addEdge("NodeA", "NodeB");
        graph.addEdge("NodeB", "NodeC");
        graph.removeEdge("NodeA", "NodeB");
        graph.removeEdge("NodeB", "NodeC");

        graph.printGraph();
        assertEquals("NodeA: \n" + "NodeB: \n" + "NodeC: \n", systemOut()
            .getHistory());
    }


    /**
     * Test adding a node and then recomputing connected components.
     */
    public void testAddNodeAndRecompute() {
        graph.addNode("NodeA");
        graph.addNode("NodeB");
        graph.addEdge("NodeA", "NodeB");
        graph.recomputeConnectedComponents();

        assertEquals("there are 1 connected components\n"
            + "the largest connected component has 2 elements\n", systemOut()
                .getHistory());
    }


    /**
     * Test removing edges from a graph with multiple edges.
     */
    public void testRemoveEdgesFromMultiEdgeGraph() {
        graph.addNode("NodeA");
        graph.addNode("NodeB");
        graph.addNode("NodeC");
        graph.addEdge("NodeA", "NodeB");
        graph.addEdge("NodeB", "NodeC");
        graph.removeEdge("NodeA", "NodeB");
        graph.removeEdge("NodeB", "NodeC");

        graph.printGraph();
        assertEquals("NodeA: \n" + "NodeB: \n" + "NodeC: \n", systemOut()
            .getHistory());
    }


    /**
     * Test connected components with a single edge.
     */
    public void testConnectedComponentsWithSingleEdge() {
        graph.addNode("NodeA");
        graph.addNode("NodeB");
        graph.addEdge("NodeA", "NodeB");
        graph.recomputeConnectedComponents();

        assertEquals("there are 1 connected components\n"
            + "the largest connected component has 2 elements\n", systemOut()
                .getHistory());
    }


    /**
     * Test connected components with multiple disconnected nodes.
     */
    public void testDisconnectedNodes() {
        for (int i = 0; i < 3; i++) {
            graph.addNode("Node" + i);
        }
        graph.recomputeConnectedComponents();

        assertEquals("there are 3 connected components\n"
            + "the largest connected component has 1 elements\n", systemOut()
                .getHistory());
    }


    /**
     * Test graph operations with resizing and adding/removing nodes.
     */
    public void testResizeAndModifyGraph() {
        for (int i = 0; i < 20; i++) {
            graph.addNode("Node" + i);
        }
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                graph.removeNode("Node" + i);
            }
        }

        graph.printGraph();
        assertEquals("Node1: \n" + "Node3: \n" + "Node5: \n" + "Node7: \n"
            + "Node9: \n" + "Node11: \n" + "Node13: \n" + "Node15: \n"
            + "Node17: \n" + "Node19: \n", systemOut().getHistory());
    }


    /**
     * Test connected components after edge addition/removal.
     */
    public void testConnectedComponentsAfterEdgeModification() {
        graph.addNode("NodeA");
        graph.addNode("NodeB");
        graph.addNode("NodeC");
        graph.addEdge("NodeA", "NodeB");
        graph.addEdge("NodeB", "NodeC");
        graph.removeEdge("NodeB", "NodeC");
        graph.recomputeConnectedComponents();

        assertEquals("there are 2 connected components\n"
            + "the largest connected component has 2 elements\n", systemOut()
                .getHistory());
    }


    /**
     * Test adding edges and nodes to a graph with different node names.
     */
    public void testAddEdgesDifferentNodes() {
        graph.addNode("Node1");
        graph.addNode("Node2");
        graph.addNode("Node3");
        graph.addEdge("Node1", "Node2");
        graph.addEdge("Node2", "Node3");

        graph.printGraph();
        assertEquals("Node1: Node2 \n" + "Node2: Node1 Node3 \n"
            + "Node3: Node2 \n", systemOut().getHistory());
    }


    /**
     * Test handling of an edge between a node and itself.
     */
    public void testSelfLoop() {
        graph.addNode("NodeA");
        graph.addEdge("NodeA", "NodeA");

        graph.printGraph();
        assertEquals("NodeA: \n", systemOut().getHistory()); // Assume
                                                             // self-loops are
                                                             // not displayed
    }


    /**
     * Test adding nodes, edges, and checking graph size.
     */
    public void testGraphSizeAfterModifications() {
        graph.addNode("NodeA");
        graph.addNode("NodeB");
        graph.addEdge("NodeA", "NodeB");
        graph.addNode("NodeC");
        graph.addEdge("NodeB", "NodeC");

        assertEquals(3, graph.getSize());
    }


    /**
     * Test multiple operations in a single test.
     */
    public void testMultipleOperations() {
        graph.addNode("Node1");
        graph.addNode("Node2");
        graph.addEdge("Node1", "Node2");
        graph.removeEdge("Node1", "Node2");
        graph.addNode("Node3");
        graph.addEdge("Node2", "Node3");
        graph.recomputeConnectedComponents();

        assertEquals("there are 2 connected components\n"
            + "the largest connected component has 2 elements\n", systemOut()
                .getHistory());
    }


    /**
     * Test handling of null or invalid inputs for edges.
     */
    public void testInvalidEdgeInputs() {
        graph.addNode("NodeA");
        try {
            graph.addEdge("NodeA", null); // Invalid input
        }
        catch (IllegalArgumentException e) {
            System.out.println("Invalid edge input");
        }
        assertEquals("Invalid edge input\n", systemOut().getHistory());
    }


    /**
     * Test adding and removing nodes with special characters in their names.
     */
    public void testSpecialCharacterNodeNames() {
        graph.addNode("Node#1");
        graph.addNode("Node$2");
        graph.addEdge("Node#1", "Node$2");
        graph.removeNode("Node#1");

        graph.printGraph();
        assertEquals("Node$2: \n", systemOut().getHistory());
    }


    /**
     * Test connected components in a densely connected graph.
     */
    public void testDenseGraphConnectedComponents() {
        for (int i = 0; i < 4; i++) {
            graph.addNode("Node" + i);
        }
        graph.addEdge("Node0", "Node1");
        graph.addEdge("Node0", "Node2");
        graph.addEdge("Node0", "Node3");
        graph.addEdge("Node1", "Node2");
        graph.addEdge("Node1", "Node3");
        graph.addEdge("Node2", "Node3");

        graph.recomputeConnectedComponents();

        assertEquals("there are 1 connected components\n"
            + "the largest connected component has 4 elements\n", systemOut()
                .getHistory());
    }
}
