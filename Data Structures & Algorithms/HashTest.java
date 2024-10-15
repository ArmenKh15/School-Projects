import student.TestCase;

/**
 * @author {Armen Khachatryan}
 * @version {09/13/2024}
 */
public class HashTest extends TestCase {

    private HashTableArr hashTable;

    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing Here
        hashTable = new HashTableArr(10);
    }


    /**
     * Check out the sfold method
     *
     * @throws Exception
     *             either a IOException or FileNotFoundException
     */
    public void testSfold() throws Exception {
        assertTrue(Hash.h("a", 10000) == 97);
        assertTrue(Hash.h("b", 10000) == 98);
        assertTrue(Hash.h("aaaa", 10000) == 1873);
        assertTrue(Hash.h("aaab", 10000) == 9089);
        assertTrue(Hash.h("baaa", 10000) == 1874);
        assertTrue(Hash.h("aaaaaaa", 10000) == 3794);
        assertTrue(Hash.h("Long Lonesome Blues", 10000) == 4635);
        assertTrue(Hash.h("Long   Lonesome Blues", 10000) == 4159);
        assertTrue(Hash.h("long Lonesome Blues", 10000) == 4667);
    }


    /**
     * Test single insert
     */
    public void testInsert() {
        hashTable.insert("test0");
        assertTrue(hashTable.containsKey("test0"));
        assertTrue(hashTable.size() == 1);
        assertTrue(hashTable.capacity() == 10);
    }


    /**
     * Test double insert
     */
    public void testInsert2() {
        hashTable.insert("test0");
        hashTable.insert("test1");
        assertTrue(hashTable.containsKey("test0"));
        assertTrue(hashTable.containsKey("test1"));
        assertTrue(hashTable.size() == 2);
        assertTrue(hashTable.capacity() == 10);
    }


    /**
     * Test duplicate insert
     */
    public void testInsertDup() {
        hashTable.insert("test0");
        hashTable.insert("test0");
        assertTrue(hashTable.containsKey("test0"));
        assertTrue(hashTable.size() == 1);
        assertTrue(hashTable.capacity() == 10);
    }


    /**
     * Test probing insert
     */
    public void testInsertProbe0() {
        hashTable.insert("test3");
        hashTable.insert("test91");
        assertEquals(Hash.h("test91", 10), Hash.h("test3", 10));
        assertTrue(hashTable.containsKey("test91"));
        assertTrue(hashTable.containsKey("test3"));
        assertTrue(hashTable.size() == 2);
        assertTrue(hashTable.capacity() == 10);
    }


    /**
     * Test probing remove
     */
    public void testInsertProbe1() {
        hashTable.insert("test3");
        hashTable.insert("test91");
        assertEquals(Hash.h("test91", 10), Hash.h("test3", 10));
        assertTrue(hashTable.containsKey("test91"));
        assertTrue(hashTable.containsKey("test3"));
        hashTable.remove("test91");
        assertFalse(hashTable.containsKey("test91"));
        assertTrue(hashTable.containsKey("test3"));
        assertTrue(hashTable.size() == 1);
        assertTrue(hashTable.capacity() == 10);
    }


    /**
     * Test probing insert resize
     */
    public void testInsertProbe2() {
        for (int i = 0; i < 6; i++) {
            hashTable.insert("test" + i);
        }

        hashTable.insert("test13");
        hashTable.insert("test95");

        assertEquals(Hash.h("test95", 20), Hash.h("test13", 20));
        assertTrue(hashTable.containsKey("test95"));
        assertTrue(hashTable.containsKey("test13"));

        assertTrue(hashTable.size() == 8);
        assertTrue(hashTable.capacity() == 20);
    }


    /**
     * Test single remove
     */
    public void testRemove0() {
        hashTable.insert("test0");
        hashTable.remove("test0");
        assertFalse(hashTable.containsKey("test0"));
        assertTrue(hashTable.size() == 0);
        assertTrue(hashTable.capacity() == 10);
    }


    /**
     * Test single remove on size > 1
     */
    public void testRemove1() {
        hashTable.insert("test0");
        hashTable.insert("test1");
        hashTable.remove("test0");
        assertFalse(hashTable.containsKey("test0"));
        assertFalse(hashTable.containsKey("TOMBSTONE"));
        assertTrue(hashTable.containsKey("test1"));
        assertTrue(hashTable.size() == 1);
        assertTrue(hashTable.capacity() == 10);
    }


    /**
     * Test resize method at 50% capacity (5/10)
     */
    public void testResize0() {
        for (int i = 0; i < 6; i++) {
            hashTable.insert("test" + i);
        }
        assertTrue(hashTable.size() == 6);
        assertTrue(hashTable.capacity() == 20);
    }


    /**
     * Test resize method at 50% capacity (10/20)
     */
    public void testResize1() {
        for (int i = 0; i < 11; i++) {
            hashTable.insert("test" + i);
        }
        assertTrue(hashTable.size() == 11);
        assertTrue(hashTable.capacity() == 40);
    }


    /**
     * Test clear method without resize
     */
    public void testclear0() {
        for (int i = 0; i < 5; i++) {
            hashTable.insert("test" + i);
        }
        assertTrue(hashTable.size() == 5);
        assertTrue(hashTable.capacity() == 10);

        hashTable.clear();

        assertTrue(hashTable.size() == 0);
        assertTrue(hashTable.capacity() == 10);

    }


    /**
     * Test clear method with resize
     */
    public void testclear1() {
        for (int i = 0; i < 6; i++) {
            hashTable.insert("test" + i);
        }
        assertTrue(hashTable.size() == 6);
        assertTrue(hashTable.capacity() == 20);

        hashTable.clear();

        assertTrue(hashTable.size() == 0);
        assertTrue(hashTable.capacity() == 20);

    }


    /**
     * Test print table method
     */

    public void testPrintTable0() {
        hashTable.insert("test0");
        hashTable.printTable();
        assertEquals("6: |test0|\n", systemOut().getHistory());
    }


    /**
     * Test print table method with multiple inserts
     */
    public void testPrintTable1() {
        for (int i = 0; i < 6; i++) {
            hashTable.insert("test" + i);
        }
        hashTable.printTable();
        assertEquals("0: |test4|\n" + "1: |test5|\n" + "16: |test0|\n"
            + "17: |test1|\n" + "18: |test2|\n" + "19: |test3|\n", systemOut()
                .getHistory());
    }


    /**
     * Test print table method with multiple inserts and removal
     */
    public void testPrintTable2() {
        for (int i = 0; i < 6; i++) {
            hashTable.insert("test" + i);
        }
        hashTable.remove("test0");
        hashTable.printTable();
        assertEquals("0: |test4|\n" + "1: |test5|\n" + "16: TOMBSTONE\n"
            + "17: |test1|\n" + "18: |test2|\n" + "19: |test3|\n", systemOut()
                .getHistory());
    }


    /**
     * Test initial capacity < 0
     */
    public void testNegCap() {
        hashTable = new HashTableArr(-10);
        assertEquals("Initial capacity must be positive.\n", systemOut()
            .getHistory());
    }


    //////////////////////////////////////////////
    /**
     * Test findRecordsContaining with no matches
     */
    public void testFindRecordsContainingNoMatch() {
        hashTable.insert("apple");
        hashTable.insert("banana");
        String[] results = hashTable.findRecordsContaining("cherry");
        assertEquals(0, results.length);
    }


    /**
     * Test insertion and retrieval after multiple resizes
     */
    public void testInsertionAfterMultipleResizes() {
        for (int i = 0; i < 25; i++) {
            hashTable.insert("test" + i);
        }
        assertTrue(hashTable.containsKey("test0"));
        assertTrue(hashTable.containsKey("test24"));
        assertEquals(25, hashTable.size());
        assertTrue(hashTable.capacity() >= 40); // Ensuring that resize has
                                                // happened multiple times
    }


    /**
     * Test resizing on exact capacity threshold
     */
    public void testResizeExactThreshold() {
        for (int i = 0; i < 10; i++) {
            hashTable.insert("test" + i);
        }
        hashTable.insert("triggerResize");
        assertTrue(hashTable.containsKey("triggerResize"));
        assertTrue(hashTable.size() == 11);
        assertEquals(40, hashTable.capacity());
    }


    /**
     * Test insertion of long keys
     */
    public void testInsertLongKeys() {
        String longKey = "a".repeat(100); // Key with 100 'a' characters
        hashTable.insert(longKey);
        assertTrue(hashTable.containsKey(longKey));
    }


    /**
     * Test resize after a removal
     */
    public void testResizeAfterRemoval() {
        for (int i = 0; i < 10; i++) {
            hashTable.insert("test" + i);
        }
        hashTable.remove("test5");
        hashTable.insert("testNew"); // Should not affect resize
        assertTrue(hashTable.containsKey("testNew"));
        assertTrue(hashTable.size() == 10); // Ensure size is as expected
    }


    /**
     * Test probing after a remove operation
     */
    public void testProbingAfterRemove() {
        hashTable.insert("test1");
        hashTable.insert("test2");
        hashTable.remove("test1");
        hashTable.insert("test3"); // This should go to the spot of test1 due to
                                   // probing
        assertTrue(hashTable.containsKey("test2"));
        assertTrue(hashTable.containsKey("test3"));
        assertFalse(hashTable.containsKey("test1"));
    }


    /**
     * Test clear on empty table
     */
    public void testClearOnEmptyTable() {
        hashTable.clear();
        assertEquals(0, hashTable.size());
        assertEquals(10, hashTable.capacity());
    }


    /**
     * Test clear after resize
     */
    public void testClearAfterResize() {
        for (int i = 0; i < 15; i++) {
            hashTable.insert("test" + i);
        }
        hashTable.clear();
        assertEquals(0, hashTable.size());
        assertTrue(hashTable.capacity() >= 20); // Capacity should remain as it
                                                // was after resize
    }


    /**
     * Test capacity management with multiple clears
     */
    public void testCapacityManagementWithMultipleClears() {
        for (int i = 0; i < 10; i++) {
            hashTable.insert("test" + i);
        }
        hashTable.clear();
        for (int i = 10; i < 20; i++) {
            hashTable.insert("test" + i);
        }
        assertEquals(10, hashTable.size());
        assertTrue(hashTable.capacity() >= 20); // Ensure capacity has not
                                                // decreased
    }


    /**
     * Test for multiple tombstones in the table
     */
    public void testMultipleTombstones() {
        for (int i = 0; i < 10; i++) {
            hashTable.insert("test" + i);
        }
        hashTable.remove("test0");
        hashTable.remove("test1");
        hashTable.insert("test10");
        assertTrue(hashTable.containsKey("test10"));
        assertFalse(hashTable.containsKey("test0"));
        assertFalse(hashTable.containsKey("test1"));
    }


    /**
     * Test remove non-existent key
     */
    public void testRemoveNonExistentKey() {
        hashTable.remove("nonexistent");
        assertFalse(hashTable.containsKey("nonexistent"));
        assertEquals(0, hashTable.size());
    }


    /**
     * Test resize with multiple collisions
     */
    public void testResizeWithCollisions0() {
        for (int i = 0; i < 10; i++) {
            hashTable.insert("key" + i);
        }
        hashTable.insert("collidingKey");
        assertTrue(hashTable.containsKey("collidingKey"));
        assertTrue(hashTable.size() == 11); // Ensures resize happened
        assertTrue(hashTable.capacity() == 40);
    }


    /**
     * Test tombstone handling
     */
    public void testTombstoneHandling() {
        hashTable.insert("key1");
        hashTable.remove("key1");
        assertFalse(hashTable.containsKey("key1"));
        hashTable.insert("key1");
        assertTrue(hashTable.containsKey("key1"));
    }


    /**
     * Test clear method with resize
     */
    public void testClearWithResize() {
        for (int i = 0; i < 15; i++) {
            hashTable.insert("key" + i);
        }
        hashTable.clear();
        assertEquals(0, hashTable.size());
        assertTrue(hashTable.capacity() == 40);
    }


    /**
     * Test basic resize operation
     */
    public void testResizeBasic() {
        for (int i = 0; i < 5; i++) {
            hashTable.insert("key" + i);
        }
        assertTrue(hashTable.size() == 5);
        assertTrue(hashTable.capacity() == 10); // Ensure capacity hasn't
                                                // changed yet

        hashTable.insert("key5"); // This should trigger a resize
        assertTrue(hashTable.size() == 6);
        assertTrue(hashTable.capacity() == 20); // Capacity should double

        for (int i = 0; i < 5; i++) {
            assertTrue(hashTable.containsKey("key" + i));
        }
    }


    /**
     * Test resize with collisions
     */
    public void testResizeWithCollisions1() {
        for (int i = 0; i < 10; i++) {
            hashTable.insert("key" + i);
        }
        assertTrue(hashTable.size() == 10);
        assertTrue(hashTable.capacity() == 20);

        hashTable.insert("key10"); // This should trigger a resize
        assertTrue(hashTable.size() == 11);
        assertTrue(hashTable.capacity() == 40); // Capacity should double

        for (int i = 0; i < 11; i++) {
            assertTrue(hashTable.containsKey("key" + i));
        }
    }


    /**
     * Test clear and resize
     */
    public void testClearAndResize() {
        assertTrue(hashTable.capacity() == 10);
        for (int i = 0; i < 10; i++) {
            hashTable.insert("key" + i);
        }
        hashTable.clear();
        assertTrue(hashTable.size() == 0);
        assertTrue(hashTable.capacity() == 20);

        // Reinsert to trigger resize
        for (int i = 0; i < 6; i++) {
            hashTable.insert("key" + i);
        }
        assertTrue(hashTable.size() == 6);
        assertTrue(hashTable.capacity() == 20); // Ensure capacity is initially
                                                // correct

        hashTable.insert("key6"); // Trigger resize
        assertTrue(hashTable.size() == 7);
        assertTrue(hashTable.capacity() == 20); // Capacity should double

        for (int i = 0; i < 7; i++) {
            assertTrue(hashTable.containsKey("key" + i));
        }
    }


    /**
     * Test tombstone handling during resize
     */
    public void testResizeWithTombstones() {
        for (int i = 0; i < 6; i++) {
            hashTable.insert("key" + i);
        }
        hashTable.remove("key2"); // Create a tombstone
        hashTable.insert("key5"); // Trigger resize

        assertTrue(hashTable.size() == 5);
        assertTrue(hashTable.capacity() == 20); // Capacity should double

        assertFalse(hashTable.containsKey("key2")); // Tombstone should not be
                                                    // present
        assertTrue(hashTable.containsKey("key0"));
        assertTrue(hashTable.containsKey("key1"));
        assertTrue(hashTable.containsKey("key3"));
        assertTrue(hashTable.containsKey("key4"));
        assertTrue(hashTable.containsKey("key5"));
    }


    /**
     * test probe
     */
    public void testprobe() {

    }

}
