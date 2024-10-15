import student.TestCase;

/**
 * DoubleLL test class
 * 
 * @author {Armen Khachatryan}
 * @version {09/13/2024}
 */
public class DoubleLLTest extends TestCase {

    private DoubleLL<String> doubleLL;

    /**
     * Setup DLL
     */
    public void setUp() {

        doubleLL = new DoubleLL<String>();
    }


    /**
     * test insert
     */
    public void testInsert0() {
        doubleLL.append("test0");
        doubleLL.printForward();

        assertEquals("test0 \n", systemOut().getHistory());
        assertEquals(1, doubleLL.size());
    }


    /**
     * test insert multi
     */
    public void testInsert1() {
        doubleLL.append("test0");
        doubleLL.append("test1");
        doubleLL.append("test2");
        doubleLL.append("test3");
        doubleLL.printForward();

        assertEquals("test0 test1 test2 test3 \n", systemOut().getHistory());
        assertEquals(4, doubleLL.size());

    }


    /**
     * test remove
     */
    public void testRemove0() {
        doubleLL.append("test0");
        doubleLL.remove("test0");
        doubleLL.printForward();

        assertEquals("\n", systemOut().getHistory());
        assertEquals(0, doubleLL.size());
        assertTrue(doubleLL.isEmpty());

    }


    /**
     * test remove multi
     */
    public void testRemove1() {
        doubleLL.append("test0");
        doubleLL.append("test1");
        doubleLL.append("test2");
        doubleLL.append("test3");
        doubleLL.remove("test1");
        doubleLL.printForward();

        assertEquals("test0 test2 test3 \n", systemOut().getHistory());
        assertEquals(3, doubleLL.size());

    }


    /**
     * test remove again
     */
    public void testRemove2() {
        doubleLL.remove("test0");
        doubleLL.printForward();

        assertEquals("\n", systemOut().getHistory());
        assertEquals(0, doubleLL.size());
        assertTrue(doubleLL.isEmpty());

    }


    /**
     * test clear
     */
    public void testClear() {

        doubleLL.append("test0");
        doubleLL.append("test1");
        doubleLL.append("test2");
        doubleLL.append("test3");

        assertEquals(4, doubleLL.size());

        doubleLL.clear();

        assertEquals(0, doubleLL.size());
        assertTrue(doubleLL.isEmpty());
    }


    /**
     * test isEmpty
     */
    public void testEmpty() {

        doubleLL.append("test");

        assertFalse(doubleLL.isEmpty());
    }
}
